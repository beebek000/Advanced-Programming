package com.learninglog.learninglogproject.user.model.dao;

import com.learninglog.learninglogproject.user.model.User;
import com.learninglog.learninglogproject.utils.DBConnection;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public User loginuser(String email, String pasword) throws SQLException{
        String query = "SELECT * FROM user WHERE email = ?";
        try(Connection conn = DBConnection.getConnection();
        PreparedStatement st = conn.prepareStatement(query)){
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                String storeHashedPw = rs.getString("password");
                if(BCrypt.checkpw(pasword, storeHashedPw));
                    int id = rs.getInt("id");
                    String name = rs.getString("name");

                    User userObj = new User(id,name,email,storeHashedPw);
                    return userObj;
            }
        }
        return null;
    }
}