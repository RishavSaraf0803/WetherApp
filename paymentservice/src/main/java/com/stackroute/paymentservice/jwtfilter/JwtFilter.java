package com.stackroute.paymentservice.jwtfilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



/* This class implements the custom filter by extending org.springframework.web.filter.GenericFilterBean.
 * Override the doFilter method with ServletRequest, ServletResponse and FilterChain.
 * This is used to authorize the API access for the application.
 */


public class JwtFilter extends GenericFilterBean {

    /*
     * Override the doFilter method of GenericFilterBean.
     * Retrieve the "authorization" header from the HttpServletRequest object.
     * Retrieve the "Bearer" token from "authorization" header.
     * If authorization header is invalid, throw Exception with message.
     * Parse the JWT token and get claims from the token using the secret key
     * Set the request attribute with the retrieved claims
     * Call FilterChain object's doFilter() method */

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        final String authHeader = req.getHeader("authorization");
        if("OPTIONS".equals(req.getMethod())){
            res.setStatus(HttpServletResponse.SC_OK);
            chain.doFilter(req,res);
        }
        else{
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
}
