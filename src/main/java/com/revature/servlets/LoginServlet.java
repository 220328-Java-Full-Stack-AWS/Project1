package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.User;
import com.revature.services.AuthService;
import com.revature.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {

    private AuthService service;
    private UserService userService;
    private ObjectMapper mapper;

    @Override
    public void init() throws ServletException {
        this.service = new AuthService();
        this.mapper = new ObjectMapper();
        this.userService = new UserService();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getHeader("email");
        String password = req.getHeader("password");
        User user = userService.getByEmail(email);
        if(user.getId() == 0){
            resp.getWriter().print("Email not found");
            System.out.println("Email not found");
            resp.setStatus(400);
        }else if( !(user.getPassword().equals(password))){
            resp.getWriter().print("Wrong password");
            System.out.println("Wrong password");
            resp.setStatus(400);
        }else{
            String json = mapper.writeValueAsString(user);
            resp.setContentType("application/json");
            resp.getWriter().print(json);
            resp.setStatus(200);
        }
    }
}
