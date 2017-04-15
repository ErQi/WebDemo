package com.erqi.web.servlet;

import com.erqi.domain.Customer;
import com.erqi.service.UserService;
import com.erqi.service.impl.UserServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 作 者: ErQi
 * 时 间: 2017.4.15.
 * 备 注: 联系人管理的servlet
 */
@WebServlet(name = "LinkmanServlet", urlPatterns = {"/LinkmanServlet"})
public class LinkmanServlet extends BaseServlet {
    public static final String ADD = "/jsp/linkman/add.jsp;";

    public void add(HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserService service = new UserServiceImpl();
        List<Customer> list = service.queryList();
        request.setAttribute("list", list);
        request.getRequestDispatcher(ADD).forward(request, response);
    }
}
