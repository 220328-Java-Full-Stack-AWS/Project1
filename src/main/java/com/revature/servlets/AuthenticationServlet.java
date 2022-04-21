package com.revature.servlets;

import com.revature.models.User;
import com.revature.services.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationServlet extends HttpServlet {

    private AuthService service;
    private UserService userService;
    private ObjectMapper mapper;

    @Override
    public void init() throws ServletException {
        this.service = new AuthService();
        this.userService = new UserService();
        this.mapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = service.login(req.getHeader("email"), req.getHeader("pswd"));
        String json = mapper.writeValueAsString(user);
        resp.setContentType("application/json");
        resp.getWriter().print(json);
        resp.setStatus(200);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = mapper.readValue(req.getReader().toString(), User.class);
        user = service.register(user);
        String json = mapper.writeValueAsString(user);
        resp.setStatus(201);
        resp.getWriter().print(json);
    }



    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = mapper.readValue(req.getReader().toString(), User.class);
        userService.update(user);
        String json = mapper.writeValueAsString(user);
        resp.setStatus(201);
        resp.getWriter().print(json);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userService.deleteUser(Integer.parseInt(req.getHeader("ers_users_id")));
        resp.setStatus(200);
    }
}