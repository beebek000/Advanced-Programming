package com.learninglog.learninglogproject.user.controller;

import com.learninglog.learninglogproject.user.model.dao.UserDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "registerPage", value = "/register")
public class RegisterServlet extends HttpServlet {
    // for Handle the Get Request
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("pages/register.jsp");
        requestDispatcher.forward(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String  fullName = req.getParameter("fullname");
        String email = req.getParameter("email");
        String  password = req.getParameter("password");

        if(fullName.isEmpty() || email.isEmpty() || password.isEmpty()){
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("pages/register.jsp");

            String message = "Please fill the all fields";
            req.setAttribute("error",message);

            requestDispatcher.forward(req,resp);
        }
        try{
            UserDAO userDao=new UserDAO();
            boolean userInserted = userDao.insertUser(fullName, email, password);
            if(userInserted==true){
                req.getRequestDispatcher("pages/login.jsp").forward(req,resp);

            }else{
                req.setAttribute("error", "Something went wrong ! Please try again ");
                req.getRequestDispatcher("pages/register.jsp").forward(req,resp);
            }
        }
        catch (Exception e){
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("pages/register.jsp").forward(req, resp);

        }
    }
}