package com.erqi.web.action;

import com.erqi.domain.Customer;
import com.erqi.service.impl.CustomerServiceImpl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.convention.annotation.*;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * 作 者: ErQi
 * 时 间: 2017.4.17.
 * 备 注: 客户操作对象
 */
@ParentPackage("default")
@Namespace("/")
@Results({@Result(name = "edit", location = "/jsp/customer/edit.jsp")})
@InterceptorRef("loginStack")
@ExceptionMappings({@ExceptionMapping(exception = "java.lange.RuntimeException", result = "error")})
public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {
    private Customer mCustomer = new Customer();
    private String cName;

    @Override
    public Customer getModel() {
        return mCustomer;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    /**
     * 添加客户到数据库中
     */
    @Action(value = "add", results = {@Result(name = "list", location = "queryList", type = "chain")})
    public String add() throws Exception {
        new CustomerServiceImpl().add(mCustomer);
        return "list";
    }

    /**
     * 查看客户列表
     */
    @Action(value = "list", results = {@Result(name = "list", location = "/jsp/customer/list.jsp")})
    public String queryList() throws Exception {
        List<Customer> list = new CustomerServiceImpl().queryList();
        ActionContext.getContext().put("list", list);
        return "list";
    }

    /**
     * 查找指定用户进行修改
     */
    @Action(value = "edit", results = {@Result(name = "edit", location = "/jsp/customer/edit.jsp")})
    public String find() throws Exception {
        Customer user = new CustomerServiceImpl().findUser(mCustomer.getCust_id());
        ActionContext.getContext().put("customer", user);
        return "edit";
    }

    /**
     * 修改指定用户信息
     */
    @Action(value = "edit_submit", results = {@Result(name = "list", location = "queryList", type = "chain")})
    public String update() throws Exception {
        new CustomerServiceImpl().update(mCustomer);
        return "list";
    }

    /**
     * 删除指定用户
     */
    @Action(value = "delete", results = {@Result(name = "list", location = "queryList", type = "chain")})
    public String delete() throws Exception {
        new CustomerServiceImpl().delete(mCustomer.getCust_id());
        return "list";
    }

    /**
     * 查找指定用户名的客户
     */
    @Action(value = "filter", results = {@Result(name = "list", location = "/jsp/customer/list.jsp")})
    public String filter() throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
        if (cName != null && !cName.trim().isEmpty()) {
            criteria = criteria.add(Restrictions.like("cust_name", "%" + cName.trim() + "%"));
        }
        List<Customer> list = new CustomerServiceImpl().filterFind(criteria);
        ActionContext.getContext().put("list", list);
        return "list";
    }
}
