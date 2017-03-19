package com.erqi.hello;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 作 者: ErQi
 * 时 间: 2017-3-20.
 * 备 注: 测试tomcat的编码问题
 */
@WebServlet(name = "EncodingServlet", urlPatterns = {"/encoding"})
public class EncodingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        System.err.println("POST请求编码格式:" + request.getCharacterEncoding() + "\t请求内容" + request.getParameter("name"));
        System.err.println("POST返回编码格式:" + response.getCharacterEncoding());
    }

    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        System.err.println("GET请求编码格式:" + request.getCharacterEncoding() + "\t请求内容" + request.getParameter("name"));
        System.err.println("GET返回编码格式:" + response.getCharacterEncoding());
        // TODO: 2017-3-20 经过测试.tomcat8.5未设置request编码,但是有默认的编码.如get默认不乱码.post默认乱码一样,应对方式:直接先设置request编码为utf-8即可.
        // TODO: 2017-3-20 response的默认编码为:IOS-8859-1需要修改设置,天亮记笔记时补充配置直接修改方式 
    }
}
