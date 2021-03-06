package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Reimbursement;
import com.revature.models.Status;
import com.revature.services.ReimbursementService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class FinanceReimbursementServlet extends HttpServlet {
    private ReimbursementService service;
    private ObjectMapper mapper;

    @Override
    public void init() throws ServletException {
        this.service = new ReimbursementService();
        this.mapper = new ObjectMapper();
    }

    // get all reimbursements
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        List<Reimbursement> AllReimbursements = service.getAllReimbursements();
        String json = mapper.writeValueAsString(AllReimbursements);
        resp.getWriter().print(json);
        resp.setStatus(200);
    }

    // update for finance manager
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Reimbursement model = mapper.readValue(req.getInputStream(), Reimbursement.class);
        service.process(model, model.getStatus(), model.getResolver());
        String json = mapper.writeValueAsString(model);
        resp.setStatus(201); //status code 201: created says that we have successfully persisted this object
        resp.getWriter().print(json);
    }
}
