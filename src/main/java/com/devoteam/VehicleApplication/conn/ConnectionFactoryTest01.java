package com.devoteam.VehicleApplication.conn;

import lombok.extern.log4j.Log4j2;

import java.sql.SQLException;

@Log4j2

public class ConnectionFactoryTest01 {
    public static void main(String[] args) throws SQLException {
        log.info(ConnectionFactory.getConnection());
    }
}
