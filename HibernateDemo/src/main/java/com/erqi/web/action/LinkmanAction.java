package com.erqi.web.action;

import com.erqi.domain.Linkman;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.convention.annotation.*;

/**
 * 作 者: ErQi
 * 时 间: 2017.4.19.
 * 备 注: 联系人的处理类
 */
@ParentPackage("default")
@Namespace("/")
@Results({@Result(name = "edit", location = "/jsp/linkman/edit.jsp")})
@InterceptorRef("loginStack")
@ExceptionMappings({@ExceptionMapping(exception = "java.lang.RuntimeException", result = "error")})
public class LinkmanAction extends ActionSupport implements ModelDriven<Linkman> {
    private Linkman mLink = new Linkman();

    @Override
    public Linkman getModel() {
        return mLink;
    }

    @Action(value = "link_add", results = {@Result(name = "list",location = "queryList" ,type = "chain")})
    public String add(){
        return null;
    }
}
