package com.learninglog.learninglogproject.user.model.dao;

import com.learninglog.learninglogproject.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDAO {
    public boolean insertUser(String name, String email, String password) throws SQLException {
        String query = "INSERT INTO user (name, email, password) VALUES (?,?,?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement st = conn.prepareStatement(query);
        ) {
            st.setString(1, name);
            st.setString(2, email);
            st.setString(3, password);

            int rowsInserted = st.executeUpdate();
            if (rowsInserted > 0) {
                return true;
            } else {
                return false;
            }

        }
    }
}