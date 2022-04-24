package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.services.ReimbursementService;
import com.revature.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ReimbursementServlet extends HttpServlet {

    private ReimbursementService service;
    private ObjectMapper mapper;

    @Override
    public void init() throws ServletException {
        this.service = new ReimbursementService();
        this.mapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        Reimbursement reimbursement = service.getById(Integer.parseInt(req.getHeader("reimbursement_id")));
        String json = mapper.writeValueAsString(reimbursement);
        resp.getWriter().print(json);
        resp.setStatus(200);
    }
}
