package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Reimbursement;
import com.revature.repositories.ReimbursementDAO;
import com.revature.services.ReimbursementService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SortServlet extends HttpServlet {

    private ReimbursementService service;
    private ReimbursementDAO dao;
    private ObjectMapper mapper;

    @Override
    public void init() throws ServletException {
        this.service = new ReimbursementService();
        this.mapper = new ObjectMapper();
        this.dao = new ReimbursementDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        String sortBy = req.getHeader("sortBy");
        if(sortBy.equals("asc-id")){
            List<Reimbursement> AllReimbursements = service.getAllReimbursements();
            String json = mapper.writeValueAsString(AllReimbursements);
            resp.getWriter().print(json);
            resp.setStatus(200);
        }else if(sortBy.equals("desc-id")){
            List<Reimbursement> AllReimbursements = dao.getAllReimbursementDESCId();
            String json = mapper.writeValueAsString(AllReimbursements);
            resp.getWriter().print(json);
            resp.setStatus(200);
        }else if (sortBy.equals("asc-amount")){
            List<Reimbursement> AllReimbursements = dao.getAllReimbursementASCAmount();
            String json = mapper.writeValueAsString(AllReimbursements);
            resp.getWriter().print(json);
            resp.setStatus(200);
        }else if (sortBy.equals("desc-amount")){
            List<Reimbursement> AllReimbursements = dao.getAllReimbursementDESCAmount();
            String json = mapper.writeValueAsString(AllReimbursements);
            resp.getWriter().print(json);
            resp.setStatus(200);
        }else if (sortBy.equals("asc-date")){
            List<Reimbursement> AllReimbursements = dao.getAllReimbursementASCDate();
            String json = mapper.writeValueAsString(AllReimbursements);
            resp.getWriter().print(json);
            resp.setStatus(200);
        }else if (sortBy.equals("desc-date")){
            List<Reimbursement> AllReimbursements = dao.getAllReimbursementDESCDate();
            String json = mapper.writeValueAsString(AllReimbursements);
            resp.getWriter().print(json);
            resp.setStatus(200);
        }else{
            resp.setStatus(400);
        }
    }
}
