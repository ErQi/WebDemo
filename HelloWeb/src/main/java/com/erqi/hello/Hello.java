package com.erqi.hello;

import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * 作 者: ErQi
 * 时 间: 2017-3-18
 * 备 注: 无
 */
@WebServlet( urlPatterns = { "/hello"})
public class Hello extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService service = new UserService();
        try {
            service.login("123","456");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
