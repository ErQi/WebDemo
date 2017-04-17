package com.erqi.web.action;

import com.erqi.domain.User;
import com.erqi.service.UserService;
import com.erqi.service.impl.UserServiceImpl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.convention.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 作 者: ErQi
 * 时 间: 2017.4.17.
 * 备 注: 处理User的操作
 */
@ParentPackage("struts-default")
@Namespace("/")
@Results({@Result(name = "success", location = "/index.htm"),
        @Result(name = "error", location = "/login.htm")})
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
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("user", login);
        ActionContext.getContext().setSession(userMap);
        return null == login ? ERROR : SUCCESS;
    }
}
