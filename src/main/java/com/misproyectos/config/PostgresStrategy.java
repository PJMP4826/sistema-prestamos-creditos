package com.misproyectos.config;

import com.misproyectos.enums.DbStrategy;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class PostgresStrategy implements DbStrategy {
    private String url;
    private String user;
    private String password;

    @Override
    public Connection connect(Properties properties) throws SQLException {
        this.url = properties.getProperty("db.url.postgres");
        this.user = properties.getProperty("db.user");
        this.password = properties.getProperty("db.password");

        System.out.println("Conectado a Postgres");
        return DriverManager.getConnection(this.url, this.user, this.password);
    }
}
