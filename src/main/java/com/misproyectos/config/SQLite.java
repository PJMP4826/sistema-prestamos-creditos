package com.misproyectos.config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class SQLite {
    private static SQLite instance;
    private Connection conn;

    private String path;

    private SQLite() {
        try (InputStream input = SQLite.class.getClassLoader().getResourceAsStream("database.properties")) {
            Properties properties = new Properties();

            if (input == null) {
                throw new RuntimeException("No se encontró el archivo database.properties.");
            }

            properties.load(input);

            this.path = properties.getProperty("db.path");

            if (this.path == null || this.path.isEmpty()) {
                throw new RuntimeException("La propiedad db.url no está definida en database.properties.");
            }

            this.conn = DriverManager.getConnection(this.path);

        } catch (IOException | SQLException e) {
            throw new RuntimeException("Error de conexión a la BD: " + e.getMessage());
        }
    }

    public static synchronized SQLite getInstance() {
        if (instance == null) {
            instance = new SQLite();
        }
        return instance;
    }

    public Connection getConnection() {
        return conn;
    }

    public void closeConnection() {
        try {
            if (this.conn != null) {
                this.conn.close();
                System.out.println("Conexión a SQLite cerrada.");
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }
}
