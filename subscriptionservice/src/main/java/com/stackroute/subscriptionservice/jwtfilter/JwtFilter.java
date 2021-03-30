package com.stackroute.subscriptionservice.jwtfilter;

import java.io.IOException;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.GenericFilterBean;


public class JwtFilter extends GenericFilterBean{

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

    	HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		final String authHeader = req.getHeader("authorization");
		if ("OPTIONS".equals(req.getMethod())) {
			res.setStatus(HttpServletResponse.SC_OK);
			chain.doFilter(req, res);
		} 
		else {
            try {
                String token = authHeader.split(" ")[1];
                System.out.println(authHeader);
                System.out.println(token);
                Claims claims = Jwts.parser().setSigningKey("jwtWeatherApp.comSecretKey").parseClaimsJws(token).getBody();
                request.setAttribute("claims",claims);
                chain.doFilter(req,res);
            }
            catch (Exception e){
                System.out.println("Access denied");
                throw new ServletException("Access denied or Invalid user");

            }


    }
}
