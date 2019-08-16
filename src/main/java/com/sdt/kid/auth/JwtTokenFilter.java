package com.sdt.kid.auth;

import com.sdt.kid.service.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

@Component
public class JwtTokenFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private JwtService jwtService;

    public JwtTokenFilter(@Autowired JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest servletRequest = (HttpServletRequest) request;
//        String authorization = servletRequest.getHeader("Authorization");
//        if (authorization != null && authorization.startsWith("Bearer ")) {
//            jwtService.parse(authorization.replaceAll("Bearer ", ""))
//                    .ifPresent(jwtAuthentication -> SecurityContextHolder
//                            .getContext()
//                            .setAuthentication(jwtAuthentication));
//        }
//        logger.error("doFilter...\n"+authorization);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
