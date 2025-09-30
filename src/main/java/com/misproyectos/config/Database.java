package com.misproyectos.config;

import com.misproyectos.enums.DbStrategy;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;

public class Database {
    private static Database instance;
    private Connection conn;
    private DbStrategy strategy;
    private String flag;

    private Database() {
        try (InputStream input = Database.class.getClassLoader().getResourceAsStream("database.properties")) {
            Properties properties = new Properties();

            properties.load(input);

            if (input == null) {
                throw new RuntimeException("No se encontraron las credenciales de la bd");
            }

            this.flag = properties.getProperty("db.motor");

            switch (this.flag.toLowerCase()) {
                case "postgres":
                    this.strategy = new PostgresStrategy();
                    break;
                case "sqlite":
                    this.strategy = new SQLiteStrategy();
                    break;
                default:
                    throw new RuntimeException("Motor de Base de datos no soportado");
            }

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


