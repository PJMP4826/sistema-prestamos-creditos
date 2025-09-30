package com.misproyectos.config;

import com.misproyectos.interfaces.DbStrategy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class SQLiteStrategy implements DbStrategy {
    private String url;

    @Override
    public Connection connect(Properties properties) throws SQLException {
        this.url = properties.getProperty("db.url.sqlite");

        System.out.println("Conectado a SQLite");
        return DriverManager.getConnection(this.url);
    }
}
