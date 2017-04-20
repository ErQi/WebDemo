package com.erqi.web.action;

import com.erqi.domain.Customer;
import com.erqi.domain.Linkman;
import com.erqi.service.CustomerService;
import com.erqi.service.LinkManService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.convention.annotation.*;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * 作 者: ErQi
 * 时 间: 2017.4.19.
 * 备 注: 联系人的处理类
 */
@ParentPackage("default")
@Namespace("/")
@InterceptorRef("loginStack")
@ExceptionMappings({@ExceptionMapping(exception = "java.lang.RuntimeException", result = "error")})
public class LinkmanAction extends ActionSupport implements ModelDriven<Linkman> {
    private static final String ADD = "/jsp/linkman/add.jsp";
    private static final String LIST = "/jsp/linkman/list.jsp";
    private static final String EDIT = "/jsp/linkman/edit.jsp";

    private CustomerService customerService;
    private LinkManService linkManService;
    private Linkman mLink = new Linkman();
    private Long mCid;
    private String mLinkName;

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public void setLinkManService(LinkManService linkManService) {
        this.linkManService = linkManService;
    }

    @Override
    public Linkman getModel() {
        return mLink;
    }

    public void setCid(Long cid) {
        this.mCid = cid;
    }

    public void setLink_name(String link_name) {
        this.mLinkName = link_name;
    }

    /**
     * 查询客户,并打开添加联系人
     */
    @Action(value = "link_add", results = {@Result(name = "add", location = ADD)})
    public String add() {
        List<Customer> list = null;
        try {
            list = customerService.queryList();
            ActionContext.getContext().put("list", list);
            return "add";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ERROR;
    }

    /**
     * 添加联系人,并打开联系人列表
     */
    @Action(value = "link_add_submit", results = {@Result(name = "list", location = "link_list", type = "chain")})
    public String addSubmit() {
        Customer customer = null;
        try {
            customer = customerService.findUser(mCid);
            mLink.setCustomer(customer);
            linkManService.add(mLink);
            return "list";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ERROR;
    }

    /**
     * 查询所有联系人
     */
    @Action(value = "link_list", results = {@Result(name = "list", location = LIST)})
    public String queryList() {
        try {
            DetachedCriteria criteria = DetachedCriteria.forClass(Linkman.class);
            List<Linkman> list = null;
            list = linkManService.find(criteria);
            ActionContext.getContext().put("list", list);
            return "list";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ERROR;
    }

    /**
     * 按条件查询联系人
     */
    @Action(value = "link_filter", results = @Result(name = "list", location = LIST))
    public String filter() {
        try {
            DetachedCriteria criterion = DetachedCriteria.forClass(Linkman.class);
            if (mLinkName != null && !mLinkName.trim().isEmpty()) {
                criterion.add(Restrictions.like("lkm_name", "%" + mLinkName.trim() + "%"));
            }
            List<Linkman> list = null;
            list = linkManService.find(criterion);
            ActionContext.getContext().put("list", list);
            return "list";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ERROR;
    }

    /**
     * 编辑联系人信息
     */
    @Action(value = "link_edit", results = @Result(name = "edit", location = EDIT))
    public String edit() {
        try {
            ActionContext context = ActionContext.getContext();
            DetachedCriteria criterion = DetachedCriteria.forClass(Linkman.class);
            Linkman linkman = null;
            linkman = linkManService.find(criterion.add(Restrictions.eq("lkm_id", mLink.getLkm_id()))).get(0);
            context.put("linkman", linkman);
            List<Customer> list = customerService.queryList();
            context.put("list", list);
            return "edit";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ERROR;
    }

    /**
     * 删除指定联系人
     */
    @Action(value = "link_delete", results = @Result(name = "list", location = "link_list", type = "chain"))
    public String delete() {
        try {
            linkManService.delete(DetachedCriteria.forClass(Linkman.class).add(Restrictions.eq("lkm_id", mLink.getLkm_id())));
            return "list";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ERROR;
    }

    /**
     * 保存修改后的联系人信息
     */
    @Action(value = "link_edit_submit", results = @Result(name = "list", location = "link_list", type = "chain"))
    public String editSubmit() {
        try {
            Customer customer = customerService.findUser(mCid);
            mLink.setCustomer(customer);
            linkManService.update(mLink);
            return "list";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ERROR;
    }
}
