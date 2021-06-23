package com.example.termwork;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLConnections {
    private String driver = "";
    private String dbURL = "";
    private String user = "";
    private String password = "";
    private static MySQLConnections connection = null;
    private MySQLConnections() throws Exception {
        driver = "com.mysql.jdbc.Driver";
        dbURL = "jdbc:mysql://rm-2vc2ld1wvz0zz50b1eo.mysql.cn-chengdu.rds.aliyuncs.com:3306/android";
        user = "sql_1";
        password = "Sql123456";
       // System.out.println("dbURL:" + dbURL);
    }
    public static Connection getConnection() {
        Connection conn = null;
        if (connection == null) {
            try {
                connection = new MySQLConnections();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        try {
            Class.forName(connection.driver);
            conn = DriverManager.getConnection(connection.dbURL,
                    connection.user, connection.password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}

//引用--忘了出处了，来源CSDN

