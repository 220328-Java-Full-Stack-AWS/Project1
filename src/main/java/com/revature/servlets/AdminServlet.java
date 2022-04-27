package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.repositories.UserDAO;
import com.revature.services.ReimbursementService;
import com.revature.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AdminServlet extends HttpServlet {

    private ObjectMapper mapper;
    private UserService service;
    private UserDAO dao;

    @Override
    public void init() throws ServletException {
        this.service = new UserService();
        this.mapper = new ObjectMapper();
        this.dao = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        String mode = req.getHeader("mode");

        if(mode.equals("asc-userId")){
            List<User> allUsers = service.getAllUsers();
            String json = mapper.writeValueAsString(allUsers);
            resp.getWriter().print(json);
            resp.setStatus(200);
        }else if( mode.equals("desc-userId")){
            List<User> allUsers = dao.getAllUsersIdDESC();
            String json = mapper.writeValueAsString(allUsers);
            resp.getWriter().print(json);
            resp.setStatus(200);
        }else if(mode.equals("asc-firstname")){
            List<User> allUsers = dao.getAllUsersFirstnameASC();
            String json = mapper.writeValueAsString(allUsers);
            resp.getWriter().print(json);
            resp.setStatus(200);
        }else if(mode.equals("desc-firstname")){
            List<User> allUsers = dao.getAllUsersFirstnameDESC();
            String json = mapper.writeValueAsString(allUsers);
            resp.getWriter().print(json);
            resp.setStatus(200);
        }else if(mode.equals("asc-lastname")){
            List<User> allUsers = dao.getAllUsersLastnameASC();
            String json = mapper.writeValueAsString(allUsers);
            resp.getWriter().print(json);
            resp.setStatus(200);
        }else if(mode.equals("desc-lastname")) {
            List<User> allUsers = dao.getAllUsersLastnameDESC();
            String json = mapper.writeValueAsString(allUsers);
            resp.getWriter().print(json);
            resp.setStatus(200);
        }else if(mode.equals("filter-employee")){
            List<User> allUsers = dao.getAllEmployees();
            String json = mapper.writeValueAsString(allUsers);
            resp.getWriter().print(json);
            resp.setStatus(200);
        }else if(mode.equals("filter-financeManager")){
            List<User> allUsers = dao.getAllFinanceManagers();
            String json = mapper.writeValueAsString(allUsers);
            resp.getWriter().print(json);
            resp.setStatus(200);
        }
        else{
            resp.setStatus(400);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getHeader("id"));
        int role = Integer.parseInt(req.getHeader("roleId"));
        service.updateUserRole(id, role);
        resp.setStatus(201); //status code 201: created says that we have successfully persisted this object
    }
}
