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
import java.util.Optional;

public class GetByResolverServlet extends HttpServlet {

    private ReimbursementService service;
    private ObjectMapper mapper;
    private UserService userService;

    @Override
    public void init() throws ServletException {
        this.service = new ReimbursementService();
        this.mapper = new ObjectMapper();
        this.userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        String resolverEmail = req.getHeader("email");
        User resolver = userService.getByEmail(resolverEmail);
        List<Reimbursement> AllReimbursements = service.getByResolver2(resolver);
        String json = mapper.writeValueAsString(AllReimbursements);
        resp.getWriter().print(json);
        resp.setStatus(200);
    }
}
