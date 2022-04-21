package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Reimbursement;
import com.revature.services.ReimbursementService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
        List<Reimbursement> AllReimbursements = service.getAllReimbursements();
        for(Reimbursement r : AllReimbursements){
            String json = mapper.writeValueAsString(r);
            resp.getWriter().print(json);
        }
        resp.setContentType("application/json");
        resp.setStatus(200);
    }

    // update for finance manager
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Reimbursement reimbursement = mapper.readValue(req.getReader().toString(), Reimbursement.class);
        service.update(reimbursement);
        String json = mapper.writeValueAsString(reimbursement);
        resp.setStatus(201); // Object successfully persisted
        resp.getWriter().print(json);
    }
}
