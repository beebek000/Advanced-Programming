package com.learninglog.learninglogproject.topic.controller;

import com.learninglog.learninglogproject.topic.model.Topic;
import com.learninglog.learninglogproject.topic.model.dao.TopicDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

@WebServlet("/topic")
public class TopicServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("page");

        if("list".equals(action)){
            try{
                List<Topic> topicList = TopicDAO.fetchTopics();
                req.setAttribute("topics", topicList);
            }
            catch(Exception e){
                req.setAttribute("error", "Unable to fetch the topics" + e.getMessage());
            }
            req.getRequestDispatcher(("pages/topicList.jsp")).forward(req,resp);
        }
        if("edit".equals(action)){
            try{
                int id = Integer.parseInt(req.getParameter("id"));
                Topic topicData = TopicDAO.fetchTopicById(id);
                req.setAttribute("topicData", topicData);
            }catch (Exception e){
                req.setAttribute("error", "Something went wrong" + e.getMessage());
            }
            req.getRequestDispatcher("pages/editTopic.jsp").forward(req,resp);
        }

        req.getRequestDispatcher("pages/addTopic.jsp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action.equals("add"));
            int user_Id = Integer.parseInt(req.getParameter("user_Id"));
            String topicName = req.getParameter("topicName");
            Timestamp createdDate = new Timestamp(System.currentTimeMillis());

            Topic topicObj = new Topic();
            topicObj.setUser_Id(user_Id);
            topicObj.setName(topicName);
            topicObj.setCreated_Date(createdDate);

            try{
                boolean result = TopicDAO.insertTopic(topicObj);
                if(result==true){
                    resp.sendRedirect("/topic?page=list");
                }else{

                }
            }catch (Exception e){

            }
    }
}
