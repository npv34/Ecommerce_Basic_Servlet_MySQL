package org.example.ecommert.database;
import java.sql.* ;  // for standard JDBC programs
import java.math.*;

public class DBConnect {
    static final String DB_URL = "jdbc:mysql://localhost/ecommert";
    static final String USER = "root";
    static final String PASS = "123456@Abc";


    public DBConnect(){}

    public Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connect database successfully");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

}
