package com.erqi.web.Interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * 作 者: ErQi
 * 时 间: 2017.4.17.
 * 备 注: 登陆拦截器,在未登录的情况下进行拦截
 */
public class LoginInterceptor extends MethodFilterInterceptor {

    @Override
    protected String doIntercept(ActionInvocation invocation) throws Exception {
        Object user = ActionContext.getContext().getSession().get("user");
        if(null!=user){
            return invocation.invoke();
        }
        return "login";
    }
}
