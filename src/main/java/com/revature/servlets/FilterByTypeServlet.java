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

public class FilterByTypeServlet extends HttpServlet {

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
        String type = req.getHeader("type");
        if (type.equals("Lodging")) {
            List<Reimbursement> reimbursementsByType = service.getReimbursementsByType(type);
            String json = mapper.writeValueAsString(reimbursementsByType);
            resp.getWriter().print(json);
            resp.setStatus(200);
        } else if (type.equals("Food")) {
            List<Reimbursement> reimbursementsByType = service.getReimbursementsByType(type);
            String json = mapper.writeValueAsString(reimbursementsByType);
            resp.getWriter().print(json);
            resp.setStatus(200);
        } else if (type.equals("Travel")) {
            List<Reimbursement> reimbursementsByType = service.getReimbursementsByType(type);
            String json = mapper.writeValueAsString(reimbursementsByType);
            resp.getWriter().print(json);
            resp.setStatus(200);
        }else if (type.equals("Entertainment")) {
            List<Reimbursement> reimbursementsByType = service.getReimbursementsByType(type);
            String json = mapper.writeValueAsString(reimbursementsByType);
            resp.getWriter().print(json);
            resp.setStatus(200);
        }else if (type.equals("Shopping")) {
            List<Reimbursement> reimbursementsByType = service.getReimbursementsByType(type);
            String json = mapper.writeValueAsString(reimbursementsByType);
            resp.getWriter().print(json);
            resp.setStatus(200);
        }else if (type.equals("Other")) {
            List<Reimbursement> reimbursementsByType = service.getReimbursementsByType(type);
            String json = mapper.writeValueAsString(reimbursementsByType);
            resp.getWriter().print(json);
            resp.setStatus(200);
        } else{
            resp.setStatus(401);
        }
    }
}
