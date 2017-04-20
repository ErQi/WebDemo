package com.erqi.web.filter;

import org.apache.struts2.dispatcher.filter.StrutsPrepareAndExecuteFilter;

import javax.servlet.annotation.WebFilter;

/**
 * 作 者: ErQi
 * 时 间: 2017.4.20.
 * 备 注: 继承struts2的过滤器,用于取代配置文件
 */
@WebFilter(filterName = "struts2", urlPatterns = "/*")
public class StrutsFilter extends StrutsPrepareAndExecuteFilter {
}
