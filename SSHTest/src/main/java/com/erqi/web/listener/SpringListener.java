package com.erqi.web.listener;

import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.annotation.WebInitParam;

/**
 * 作 者: ErQi
 * 时 间: 2017.4.20.
 * 备 注: spring的web监听器配置
 */
@WebInitParam(name = "contextConfigLocation",value = "Classpath:applicationContext.xml")
public class SpringListener extends ContextLoaderListener {

}