package com.erqi.web.servlet;

import com.erqi.domain.Customer;
import com.erqi.domain.Linkman;
import com.erqi.service.LinkManService;
import com.erqi.service.CustomerService;
import com.erqi.service.impl.LinkManServiceImpl;
import com.erqi.service.impl.CustomerServiceImpl;
import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 作 者: ErQi
 * 时 间: 2017.4.15.
 * 备 注: 联系人管理的servlet
 */
@WebServlet(name = "LinkmanServlet", urlPatterns = {"/LinkmanServlet"})
public class LinkmanServlet extends BaseServlet {
    private static final String ADD = "/jsp/linkman/add.jsp;";
    private static final String LIST = "/jsp/linkman/list.jsp;";
    private static final String EDIT = "/jsp/linkman/edit.jsp;";

    /**
     * 查询客户并打开添加联系人页面
     */
    public void add(HttpServletRequest request, HttpServletResponse response) throws Exception {
        CustomerService service = new CustomerServiceImpl();
        List<Customer> list = service.queryList();
        request.setAttribute("list", list);
        request.getRequestDispatcher(ADD).forward(request, response);
    }

    /**
     * 将联系人添加到数据库中,并与客户关联
     */
    public void addsubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Linkman linkman = new Linkman();
        Map<String, String[]> map = request.getParameterMap();
        BeanUtils.populate(linkman, map);

        Long cust_id = Long.parseLong(map.get("cust_id")[0]);
        Customer user = new CustomerServiceImpl().findUser(cust_id);
        linkman.setCustomer(user);
        LinkManService service = new LinkManServiceImpl();
        service.add(linkman);
        list(request, response);
    }

    /**
     * 查询联系人的列表
     */
    public void list(HttpServletRequest request, HttpServletResponse response) throws Exception {
        DetachedCriteria criterion = DetachedCriteria.forClass(Linkman.class);
        List<Linkman> list = new LinkManServiceImpl().find(criterion);
        request.setAttribute("list", list);
        request.getRequestDispatcher(LIST).forward(request, response);
    }

    /**
     * 按照条件查询联系人的列表
     */
    public void filter(HttpServletRequest request, HttpServletResponse response) throws Exception {
        DetachedCriteria criterion = DetachedCriteria.forClass(Linkman.class);
        String name = request.getParameter("lkm_name");
        if (name != null && !name.trim().isEmpty()) {
            criterion.add(Restrictions.like("lkm_name", "%" + name.trim() + "%"));
        }
        List<Linkman> list = new LinkManServiceImpl().find(criterion);
        request.setAttribute("list", list);
        request.getRequestDispatcher(LIST).forward(request, response);
    }

    /**
     * 按照条件修改联系人信息
     */
    public void edit(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Long lkm_id = Long.parseLong(request.getParameter("lkm_id"));
        LinkManService service = new LinkManServiceImpl();

        DetachedCriteria criterion = DetachedCriteria.forClass(Linkman.class);
        Linkman linkman = service.find(criterion.add(Restrictions.eq("lkm_id", lkm_id))).get(0);
        request.setAttribute("linkman", linkman);

        List<Customer> list = new CustomerServiceImpl().queryList();
        request.setAttribute("list", list);

        request.getRequestDispatcher(EDIT).forward(request, response);
    }

    /**
     * 按照条件删除联系人信息
     */
    public void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Long lkm_id = Long.parseLong(request.getParameter("lkm_id"));
        LinkManService service = new LinkManServiceImpl();
        service.delete(DetachedCriteria.forClass(Linkman.class).add(Restrictions.eq("lkm_id", lkm_id)));
        list(request, response);
    }

    /**
     * 按照条件修改联系人信息
     */
    public void editSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, String[]> map = request.getParameterMap();
        Linkman linkman = new Linkman();
        BeanUtils.populate(linkman, map);
        Long cust_id = Long.parseLong(String.valueOf(map.get("cust_id")[0]));
        Customer user = new CustomerServiceImpl().findUser(cust_id);

        linkman.setCustomer(user);
        new LinkManServiceImpl().update(linkman);
        list(request, response);
    }
}
