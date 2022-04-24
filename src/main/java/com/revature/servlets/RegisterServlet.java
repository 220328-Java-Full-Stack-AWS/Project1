package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Reimbursement;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.repositories.UserDAO;
import com.revature.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RegisterServlet extends HttpServlet {

    private UserService userService;
    private ObjectMapper mapper;
    private UserDAO userDao;

    @Override
    public void init() throws ServletException {
        this.mapper = new ObjectMapper();
        this.userService = new UserService();
        this.userDao = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> Usernames = userDao.getAllUsernames();
        String json = mapper.writeValueAsString(Usernames);
        resp.getWriter().print(json);
        resp.setStatus(200);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = mapper.readValue(req.getInputStream(), User.class);
        user = userService.create(user);
        System.out.println(user);
        String json = mapper.writeValueAsString(user);
        resp.setStatus(201); // object successfully persisted
        resp.getWriter().print(json);
    }
}
