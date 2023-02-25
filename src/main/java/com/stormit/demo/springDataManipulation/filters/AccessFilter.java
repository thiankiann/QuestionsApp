package com.stormit.demo.springDataManipulation.filters;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(100)
@Slf4j
public class AccessFilter implements Filter {

    private final String USER = "admin";
    private final String PASSWORD = "root";

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String user = req.getParameter("user");
        String password = req.getParameter("password");

        if (user != null || password != null) {
            if (user == null || password == null) {
                res.setStatus(HttpStatus.BAD_REQUEST.value());
                res.getWriter().write("Bad request!!");
            } else if (USER.equals(user) && PASSWORD.equals(password)) {
                res.setStatus(HttpStatus.I_AM_A_TEAPOT.value());
                res.getWriter().write("Secret page =)");
            } else {
                res.setStatus(HttpStatus.FORBIDDEN.value());
                res.getWriter().write("Forbidden!!");
            }

        } else {
            chain.doFilter(request, response);
        }
    }
}