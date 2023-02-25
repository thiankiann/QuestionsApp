package com.stormit.demo.springDataManipulation.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

@Component
@Order(1)
@Slf4j
public class RequestTimeLoggingFilter implements Filter {

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        Instant start = Instant.now();
        try {
            chain.doFilter(request, response);
        } finally {
            Instant end = Instant.now();
            long time = Duration.between(start, end).toMillis();
            log.info("{}: {} ms ", ((HttpServletRequest) request).getRequestURI(), time);
        }
    }
}