package com.erqi.web.servlet;


import com.erqi.domain.Customer;
import com.erqi.service.UserService;
import com.erqi.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 作 者: ErQi
 * 时 间: 2017.4.14.
 * 备 注: 用户操作的servlt
 */
@WebServlet(name = "UserServlet", urlPatterns = {"/UserServlet"})
public class UserServlet extends BaseServlet {
    public static final String LIST= "/jsp/customer/list.jsp";

    /**
     * 添加用户
     */
    public void add(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 获取参数
        Map<String, String[]> map = request.getParameterMap();
        // 封装对象
        Customer customer = new Customer();
        try {
            BeanUtils.populate(customer, map);
            // 调用service层处理
            UserService user = new UserServiceImpl();
            user.add(customer);
            request.setAttribute(BaseServlet.MSG, "添加成功");
            request.getRequestDispatcher(BaseServlet.ERROR).forward(request,response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute(BaseServlet.MSG, "添加失败");
            throw e;
        }
    }


    /**
     * 查询用户列表
     */
    public void queryList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.err.println("接受请求");
        UserService service = new UserServiceImpl();
        List<Customer> list = service.queryList();
        request.setAttribute("list",list);
        request.getRequestDispatcher(LIST).forward(request,response);
    }
}
