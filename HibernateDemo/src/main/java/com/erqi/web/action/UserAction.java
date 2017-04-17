package com.erqi.web.action;

import com.erqi.domain.User;
import com.erqi.service.UserService;
import com.erqi.service.impl.UserServiceImpl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.convention.annotation.*;

/**
 * 作 者: ErQi
 * 时 间: 2017.4.17.
 * 备 注: 处理User的操作
 */
@ParentPackage("default")
@Namespace("/")
@Results({@Result(name = "success", location = "/index.htm"),
        @Result(name = "error", location = "/error.htm")})
@ExceptionMappings({@ExceptionMapping(exception = "java.lange.RuntimeException", result = "error")})
public class UserAction extends ActionSupport implements ModelDriven<User> {

    private User mUser = new User();

    @Override
    public User getModel() {
        return mUser;
    }

    @Action(value = "login")
    public String login() throws Exception {
        UserService service = new UserServiceImpl();
        User login = service.login(mUser);
        ActionContext.getContext().getSession().put("user",login);
        return null == login ? ERROR : SUCCESS;
    }
}
