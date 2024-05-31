package com.contacts.manage.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

public class CheckingAccessFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        var httpServletRequest = (HttpServletRequest) request;
        var urlPath = httpServletRequest.getRequestURL();

        System.out.println("Url Path : " + urlPath);

        chain.doFilter(request, response);
    }


}
