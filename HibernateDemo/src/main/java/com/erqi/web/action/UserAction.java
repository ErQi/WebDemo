package com.erqi.web.action;

import com.erqi.domain.User;
import com.erqi.service.UserService;
import com.erqi.service.impl.UserServiceImpl;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 作 者: ErQi
 * 时 间: 2017.4.17.
 * 备 注: 处理User的操作
 */
@ParentPackage("struts-default")
@Namespace("/")
@Results({@Result(name = "success", location = "/index.htm"),
        @Result(name = "error", location = "/jsp/error.jsp")})
@ExceptionMappings({@ExceptionMapping(exception = "java.lange.RuntimeException", result = "error")})
public class UserAction extends ActionSupport {

    @Action(value = "login")
    public String login() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        BeanUtils.populate(user, map);

        UserService service = new UserServiceImpl();
        User login = service.login(user);

        return null == login ? ERROR : SUCCESS;
    }

}
