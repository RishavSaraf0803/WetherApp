package com.stackroute.userservice.jwtfilter;

import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;

public class JwtFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

    	HttpServletRequest req = (HttpServletRequest) request;
		
		String header = req.getHeader("Authorization");

		if(header == null || !header.startsWith("Bearer"))
			throw new ServletException("AuthHeader invalid / Bearer token missing");
		
		String remJWT = header.substring(7); 		//header after "bearer"
		
		try {
			Claims claims = Jwts.parser().setSigningKey("secret").parseClaimsJwt(remJWT).getBody();
			request.setAttribute("claims", claims);
		} 
		catch (Exception e) {
			throw new ServletException(e.getMessage());
		}
		
		chain.doFilter(request, response);
		
    }

}
