package com.misproyectos.config;

import com.misproyectos.interfaces.DbStrategy;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class Database {
    private static Database instance;
    private final Connection conn;

    private Database() {
        try (InputStream input = Database.class.getClassLoader().getResourceAsStream("database.properties")) {
            Properties properties = new Properties();

            properties.load(input);

            if (input == null) {
                throw new RuntimeException("No se encontraron las credenciales de la bd");
            }

            String flag = properties.getProperty("db.motor");

            DbStrategy strategy = switch (flag.toLowerCase()){
                case "postgres" -> new PostgresStrategy();
                case "sqlite" -> new SQLiteStrategy();
                default -> throw new RuntimeException("Motor de Base de datos no soportado");
            };

            this.conn = strategy.connect(properties);
        } catch (IOException | SQLException e) {
            throw new RuntimeException("Error de connection a la BD", e);
        }
    }

    public static Database getInstance() {
        if (instance == null) {
            synchronized (Database.class) {
                if (instance == null) {
                    instance = new Database();
                }
            }
        }

        return instance;
    }

    public Connection getConnection() {
        return conn;
    }

    public void closeConnection() {
        try {
            if (conn != null) {
                conn.close();
                System.out.println("Connection a la BD cerrada");
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar la connection: " + e.getMessage());
        }
    }

}


