package com.erqi.web.filter;

import org.apache.struts2.dispatcher.filter.StrutsPrepareAndExecuteFilter;

import javax.servlet.annotation.WebFilter;


/**
 * 作 者: ErQi
 * 时 间: 2017.4.17.
 * 备 注: 没有web.xml通过注解配置信息
 */
@WebFilter(filterName = "Struts2",urlPatterns = {"/*"})
public class StrutsFilter extends StrutsPrepareAndExecuteFilter {

}
