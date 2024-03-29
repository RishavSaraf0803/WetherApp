package com.stackroute.userservice.jwtfilter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.stackroute.userservice.services.UserDetailsServiceImpl;


public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtProvider tokenProvider;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, 
    								HttpServletResponse response, 
    								FilterChain filterChain) 
    										throws ServletException, IOException {
        try {
        	
            String jwt = getJwt(request);
            if (jwt!=null && tokenProvider.validateJwtToken(jwt)) {
                String username = tokenProvider.getUserNameFromJwtToken(jwt);

                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authentication 
                		= new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            logger.error("Can NOT set user authentication -> Message: {}", e);
        }

        filterChain.doFilter(request, response);
    }

    private String getJwt(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        	
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
        	return authHeader.replace("Bearer ","");
        }

        return null;
    }
}

//import org.springframework.web.filter.GenericFilterBean;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//
//import java.io.IOException;
//
//public class JwtFilter extends GenericFilterBean {
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//
//    	HttpServletRequest req = (HttpServletRequest) request;
//		
//		String header = req.getHeader("Authorization");
//
//		if(header == null || !header.startsWith("Bearer"))
//			throw new ServletException("AuthHeader invalid / Bearer token missing");
//		
//		String remJWT = header.substring(7); 		//header after "bearer"
//		
//		try {
//			Claims claims = Jwts.parser().setSigningKey("secret").parseClaimsJwt(remJWT).getBody();
//			request.setAttribute("claims", claims);
//		} 
//		catch (Exception e) {
//			throw new ServletException(e.getMessage());
//		}
//		
//		chain.doFilter(request, response);
//		
//    }
//
//}
