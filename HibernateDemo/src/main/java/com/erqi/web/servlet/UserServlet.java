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
    public static final String LIST = "/jsp/customer/list.jsp";
    public static final String EDIT = "/jsp/customer/edit.jsp";

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
            request.getRequestDispatcher(BaseServlet.ERROR).forward(request, response);
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
        UserService service = new UserServiceImpl();
        List<Customer> list = service.queryList();
        request.setAttribute("list", list);
        request.getRequestDispatcher(LIST).forward(request, response);
    }

    /**
     * 修改指定用户
     */
    public void edit(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 先查询指定用户信息(应该使用缓存数据)
        Long cust_id = Long.parseLong(request.getParameter("cust_id"));
        UserService service = new UserServiceImpl();
        Customer customer = service.findUser(cust_id);
        request.setAttribute("customer", customer);
        request.getRequestDispatcher(EDIT).forward(request, response);
    }

    /**
     * 保存用户的修改信息
     */
    public void editsubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, String[]> parameterMap = request.getParameterMap();
        Customer customer = new Customer();
        try {
            BeanUtils.populate(customer, parameterMap);
            UserService service = new UserServiceImpl();
            System.err.println(customer);
            service.update(customer);
            queryList(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute(MSG, "修改失败");
            throw e;
        }
    }

    /**
     * 删除指定用户
     */
    public void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Long cid = Long.parseLong(request.getParameter("cust_id"));
        UserService service = new UserServiceImpl();
        service.delete(cid);
        queryList(request, response);
    }
}
