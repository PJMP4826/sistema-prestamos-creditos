package com.misproyectos.enums;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public interface DbStrategy {
    Connection connect(Properties properties) throws SQLException;
}
