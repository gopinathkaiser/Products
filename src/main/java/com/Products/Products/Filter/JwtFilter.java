package com.Products.Products.Filter;

import com.Products.Products.Config.CustomUserDetailsService;
import com.Products.Products.Config.JwtAuthenticationEntryPoint;
import com.Products.Products.Util.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Autowired
    JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String token = null,userName=null;

        if(authHeader != null && authHeader.startsWith("Bearer")){
            token = authHeader.substring(7);
            userName = jwtUtils.extractUsername(token);
        }

        if(userName != null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(userName);
            if(jwtUtils.validateToken(token,userDetails)){
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request,response);
    }
}
