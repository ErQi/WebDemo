package com.erqi.web.action;

import com.erqi.domain.Customer;
import com.erqi.service.CustomerService;
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
@InterceptorRef("loginStack")
@Action(className = "customerAction") // 这里对应spring中的id值,将对象交由spring来进行创建
@ExceptionMappings({@ExceptionMapping(exception = "java.lang.RuntimeException", result = "error")})
public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {
    public static final String LIST = "/jsp/customer/list.jsp";
    public static final String EDIT = "/jsp/customer/edit.jsp";

    private CustomerService customerService;
    private Customer mCustomer = new Customer();
    private String cName;

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

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
    @Action(value = "cust_add", results = {@Result(name = "list", location = "cust_list", type = "chain")})
    public String add() {
        try {
            new CustomerServiceImpl().add(mCustomer);
            return "list";
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }

    /**
     * 查看客户列表
     */
    @Action(value = "cust_list", results = {@Result(name = "list", location = LIST)})
    public String queryList() throws Exception {
        List<Customer> list = new CustomerServiceImpl().queryList();
        ActionContext.getContext().put("list", list);
        return "list";
    }

    /**
     * 查找指定用户进行修改
     */
    @Action(value = "cust_edit", results = {@Result(name = "edit", location = EDIT)})
    public String find() throws Exception {
        Customer user = customerService.findUser(mCustomer.getCust_id());
        ActionContext.getContext().put("customer", user);
        return "edit";
    }

    /**
     * 修改指定用户信息
     */
    @Action(value = "cust_edit_submit", results = {@Result(name = "list", location = "cust_list", type = "chain")})
    public String update() throws Exception {
        customerService.update(mCustomer);
        return "list";
    }

    /**
     * 删除指定用户
     */
    @Action(value = "cust_delete", results = {@Result(name = "list", location = "cust_list", type = "chain")})
    public String delete() throws Exception {
        customerService.delete(mCustomer.getCust_id());
        return "list";
    }

    /**
     * 查找指定用户名的客户
     */
    @Action(value = "cust_filter", results = {@Result(name = "list", location = LIST)})
    public String filter() throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
        if (cName != null && !cName.trim().isEmpty()) {
            criteria = criteria.add(Restrictions.like("cust_name", "%" + cName.trim() + "%"));
        }
        List<Customer> list = customerService.filterFind(criteria);
        ActionContext.getContext().put("list", list);
        return "list";
    }
}
