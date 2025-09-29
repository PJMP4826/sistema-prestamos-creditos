package com.misproyectos.config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Database {
    private static Database instance;
    private Connection conn;

    private String url;
    private String user;
    private String password;

    private Database() {
        try (InputStream input = Database.class.getClassLoader().getResourceAsStream("database.properties")) {
            Properties properties = new Properties();

            if (input == null) {
                throw new RuntimeException("No se encontraron las credenciales de la bd");
            }

            properties.load(input);

            url = properties.getProperty("db.url");
            user = properties.getProperty("db.user");
            password = properties.getProperty("db.password");

            //conexion
            conn = DriverManager.getConnection(url, user, password);
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

}


