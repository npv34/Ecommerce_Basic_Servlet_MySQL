package org.example.ecommert.models;

import org.example.ecommert.database.DBConnect;
import org.example.ecommert.enties.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserModel {
    private Connection conn;

    public UserModel() {
        this.conn = new DBConnect().getConnection();
    }

    public User findUser(String username, String password) throws SQLException {
        String SQL_FIND_USER = "SELECT * FROM users WHERE username = ? AND password = ?";
        PreparedStatement stmt = conn.prepareStatement(SQL_FIND_USER);
        stmt.setString(1, username);
        stmt.setString(2, password);
        ResultSet result = stmt.executeQuery();

        User user = null;

        while (result.next()) {
            int id = result.getInt("id");
            String uName = result.getString("username");
            String uPass = result.getString("password");
            String uRole = result.getString("role");
            user = new User(uName, uPass, uRole);
            user.setId(id);
        }

        return user;
    }
}
