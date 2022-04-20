package com.revature.servlets;

import com.revature.services.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationServlet extends HttpServlet {

    private AuthService service;
    private ObjectMapper mapper;

    @Override
    public void init() throws ServletException {
        this.service = new AuthService();
        this.mapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("query param: " + req.getParameter("reimb_id"));
    }
}