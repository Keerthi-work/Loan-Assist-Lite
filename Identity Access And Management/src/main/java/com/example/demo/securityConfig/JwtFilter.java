package com.example.demo.securityConfig;
 
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
 
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.Collections;
 
@Component
public class JwtFilter extends OncePerRequestFilter {
 
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
 
        String auth = request.getHeader("Authorization");
 
        if (auth != null && auth.startsWith("Bearer ")) {
 
            String token = auth.substring(7);
 
            JwtUtil util = new JwtUtil();
 
            String username = util.extractUsername(token);
            String role = util.extractRole(token);
 
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            username, 
                            null,    
                            Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role))
                    );
 
            
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
 
        
        filterChain.doFilter(request, response);
    }
}