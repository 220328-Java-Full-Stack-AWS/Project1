package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.User;
import com.revature.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserServlet extends HttpServlet {
    private ObjectMapper mapper;
    private UserService userService;

    @Override
    public void init() throws ServletException {
        this.mapper = new ObjectMapper();
        this.userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        String id = req.getHeader("id");
        User user = userService.getById(Integer.parseInt(id));
        String json = mapper.writeValueAsString(user);
        resp.getWriter().print(json);
        resp.setStatus(200);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User model = mapper.readValue(req.getInputStream(), User.class);
        userService.update(model);
        String json = mapper.writeValueAsString(model);
        resp.setStatus(201); //status code 201: created says that we have successfully persisted this object
        resp.getWriter().print(json);
    }

    // delete
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userService.deleteUser(Integer.parseInt(req.getHeader(("id"))));
        resp.setStatus(200);
    }
}
