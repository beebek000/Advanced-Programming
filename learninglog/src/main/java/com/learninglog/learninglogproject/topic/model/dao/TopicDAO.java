package com.learninglog.learninglogproject.topic.model.dao;

import com.learninglog.learninglogproject.topic.model.Topic;
import com.learninglog.learninglogproject.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TopicDAO {
    public static boolean insertTopic(Topic topicObj) throws SQLException {
        String query = "INSERT INTO topic(name, user_Id, createdDate) VALUES(?,?,?)";
        try(Connection conn = DBConnection.getConnection();
        PreparedStatement st = conn.prepareStatement(query)){
            st.setString(1,topicObj.getName());
            st.setInt(2,topicObj.getUser_Id());
            st.setTimestamp(3,topicObj.getCreated_Date());

            int insertedRows = st.executeUpdate();
            if(insertedRows == 0){
                return false;
            }else{
                return true;
            }
        }
    }

    public static List<Topic> fetchTopics() throws SQLException{
        String query = "SELECT * FROM topic";
        try(Connection Conn = DBConnection.getConnection();
        PreparedStatement st = Conn.prepareStatement(query)){
            ResultSet rs = st.executeQuery();
            List<Topic> topicList = new ArrayList<>();

            while(rs.next()){
                int id = rs.getInt(1);
                String topicName = rs.getString(2);
                int userId = rs.getInt(3);
                Timestamp createdDate = rs.getTimestamp(4);

                Topic t = new Topic(id,topicName,userId,createdDate);
                topicList.add(t);
            }
            return topicList;
        }
    }
    public static Topic fetchTopicById(int id) throws SQLException{
        String query = "SELECT * FROM topic WHERE id = ?";
        try(Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(query)){
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                String topicName = rs.getString("name");
                int userId = rs.getInt("user_Id");
                Timestamp createdDate = rs.getTimestamp("created_Date");
                Topic topic = new Topic(id, topicName, userId, createdDate);
                return topic;
            }
            return null;
        }
    }
}
