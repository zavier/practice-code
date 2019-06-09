package com.zavier.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class TestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String name = req.getParameter("name");
        System.out.println(name);
        resp.setContentType("application/json");
        final PrintWriter writer = resp.getWriter();
        String s = "{'success':true, 'code':5, 'message':'operator success'}";
        writer.write(s);
    }
}
