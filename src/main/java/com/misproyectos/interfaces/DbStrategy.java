package com.misproyectos.interfaces;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public interface DbStrategy {
    Connection connect(Properties properties) throws SQLException;
}
