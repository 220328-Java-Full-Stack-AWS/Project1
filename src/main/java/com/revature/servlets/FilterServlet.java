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
import java.util.List;

public class FilterServlet extends HttpServlet {

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
        String status = req.getHeader("status");
        if(status.equals("pending")){
            List<Reimbursement> reimbursementsByStatus = service.getReimbursementsByStatus(Status.PENDING);
            String json = mapper.writeValueAsString(reimbursementsByStatus);
            resp.getWriter().print(json);
            resp.setStatus(200);
        }else if(status.equals("approved")){
            List<Reimbursement> reimbursementsByStatus = service.getReimbursementsByStatus(Status.APPROVED);
            String json = mapper.writeValueAsString(reimbursementsByStatus);
            resp.getWriter().print(json);
            resp.setStatus(200);
        }else{
            List<Reimbursement> reimbursementsByStatus = service.getReimbursementsByStatus(Status.DENIED);
            String json = mapper.writeValueAsString(reimbursementsByStatus);
            resp.getWriter().print(json);
            resp.setStatus(200);
        }
    }
}
