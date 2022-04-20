package com.revature.servlets;

import com.revature.models.Reimbursement;
import com.revature.services.ReimbursementService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

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
        //System.out.println("query param: " + req.getParameter("reimb_id"));
        Reimbursement reimbursement = service.getById(Integer.parseInt(req.getHeader("reimb_id")));
        String json = mapper.writeValueAsString(reimbursement);
        resp.setContentType("application/json");
        resp.getWriter().print(json);
        resp.setStatus(200);
    }
}
