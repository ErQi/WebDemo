package com.erqi.web.action;

import com.erqi.domain.User;
import com.erqi.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 作 者: ErQi
 * 时 间: 2017.4.22.
 * 备 注: 用户网络层
 */
@ParentPackage("demo")
@Namespace("/")
@ExceptionMapping(exception = "java.lang.RuntimeException", result = "error")
public class UserAction extends ActionSupport implements ModelDriven<User> {
    private User user = new User();
    private UserService userService;
    private String user_acct;

    @Override
    public User getModel() {
        return user;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setUser_acct(String user_acct) {
        this.user_acct = user_acct;
    }

    @Action("user_check_acct")
    public String onCheckAcct() {
        User user = userService.find(user_acct);
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=UTF-8");
        try {
            // 获取输出流
            PrintWriter writer = response.getWriter();
            // 进行判断
            if (user == null) {
                // 说明：登录名查询到用户了，说明登录已经存在了，不能注册
                writer.print("ok");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return NONE;
    }

    @Action(value = "user_register", results = {@Result(name = "login", location = "/login.jsp")})
    public String onRegister() {
        userService.onSave(user);
        return "login";
    }

    @Action(value = "user_login", results = {@Result(name = "index", location = "/index.jsp")})
    public String onLogin() {
        User login = userService.onLogin(user);
        if (login == null) {
            ActionContext.getContext().put("msg", "账号或密码错误");
            return ERROR;
        } else {
            ActionContext.getContext().getSession().put("user", login);
            return "index";
        }
    }
}

