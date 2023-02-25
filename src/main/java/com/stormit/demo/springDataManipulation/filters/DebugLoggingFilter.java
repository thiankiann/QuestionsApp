package com.stormit.demo.springDataManipulation.filters;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class DebugLoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        Enumeration<String> headerNames = req.getHeaderNames();
        Map<String, String> headers = new HashMap<>();

        while (headerNames.hasMoreElements()){
            String headerName = headerNames.nextElement();
            headers.put(headerName, req.getHeader(headerName));
        }
        log.info("Request {} {} {}", req.getRequestURI(), req.getCharacterEncoding(), headers);

        chain.doFilter(request,response);
    }
}
