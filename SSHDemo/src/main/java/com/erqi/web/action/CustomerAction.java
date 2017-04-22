package com.erqi.web.action;

import com.erqi.domain.Customer;
import com.erqi.domain.PageBean;
import com.erqi.service.CustomerService;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.hibernate.criterion.DetachedCriteria;


/**
 * 作 者: ErQi
 * 时 间: 2017.4.22.
 * 备 注: 接受客户相关请求
 */
public class CustomerAction {
    private static final String LIST = "/jsp/customer/list.jsp";
    private static final String EDIT = "/jsp/customer/edit.jsp";
    /** 查询页数,从1开始 */
    private Integer pageCode = 1;
    /** 每页个数 */
    private Integer pageSize = 2;
    private CustomerService customerService;

    public void setPageCode(Integer pageCode) {
        this.pageCode = pageCode;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Action(value = "customer_find_page", results = {@Result(name = "list", location = LIST)})
    public String onFindByPage() {
        DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
        PageBean<Customer> bean = customerService.onFindByPage(pageCode, pageSize, criteria);
        ActionContext.getContext().put("bean", bean);
        return "list";
    }
}
