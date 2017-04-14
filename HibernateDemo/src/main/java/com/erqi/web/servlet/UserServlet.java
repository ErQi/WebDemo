package com.erqi.web.servlet;


import com.erqi.domain.Customer;
import com.erqi.service.UserService;
import com.erqi.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 作 者: ErQi
 * 时 间: 2017.4.14.
 * 备 注: TODO
 */
@WebServlet(name = "UserServlet",urlPatterns = {"/UserServlet"})
public class UserServlet extends BaseServlet {

    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取参数
        Map<String, String[]> map = request.getParameterMap();
        // 封装对象
        Customer customer = new Customer();
        try {
            BeanUtils.populate(customer, map);
            // 调用service层处理
            UserService user = new UserServiceImpl();
            user.add(customer);
            System.err.println("添加成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
