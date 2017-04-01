package com.spring.yu.config;

import com.spring.yu.comm.JwtUtils;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yunan on 2017/4/1.
 */
public class JwtFilter extends GenericFilterBean
{

    final private Logger logger = LoggerFactory.getLogger(JwtFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException
    {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        final String authHeader = request.getHeader("authorization");
        final String BEAREA = "Bearer ";

        if ("OPTIONS".equals(request.getMethod()))
        {
            response.setStatus(HttpServletResponse.SC_OK);
        } else
        {
            if (authHeader == null || !authHeader.startsWith(BEAREA)) {

                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                //throw new ServletException("Missing or invalid Authorization header");
            }else{
                try
                {
                    final Claims claims = JwtUtils.parseJWT(authHeader.substring(BEAREA.length()));
                } catch (Exception e)
                {
                    logger.error(e.getMessage());
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                }
            }

        }
        filterChain.doFilter(request, response);

    }
}
