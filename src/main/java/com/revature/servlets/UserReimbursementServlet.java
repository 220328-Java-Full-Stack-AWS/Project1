package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.repositories.ReimbursementDAO;
import com.revature.services.ReimbursementService;
import com.revature.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class UserReimbursementServlet extends HttpServlet {

    private ReimbursementService service;
    private UserService userService;
    private ObjectMapper mapper;

    @Override
    public void init() throws ServletException {
        this.service = new ReimbursementService();
        this.mapper = new ObjectMapper();
        this.userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        User user = userService.getById(Integer.parseInt(req.getHeader("user_id")));
        List<Reimbursement> AllReimbursements = service.getByAuthor(user);
        String json = mapper.writeValueAsString(AllReimbursements);
        resp.getWriter().print(json);
        resp.setStatus(200);
    }

    // create
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Reimbursement reimbursement = mapper.readValue(req.getInputStream(), Reimbursement.class);
        reimbursement = service.create(reimbursement);
        String json = mapper.writeValueAsString(reimbursement);
        resp.setStatus(201); // object successfully persisted
        resp.getWriter().print(json);
    }

    // update
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Reimbursement model = mapper.readValue(req.getInputStream(), Reimbursement.class);
        service.update(model);
        String json = mapper.writeValueAsString(model);
        resp.setStatus(201); //status code 201: created says that we have successfully persisted this object
        resp.getWriter().print(json);
    }

    // delete
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        service.deleteReimbursement(Integer.parseInt(req.getHeader("reimb_id")));
        resp.setStatus(200);
    }
}
