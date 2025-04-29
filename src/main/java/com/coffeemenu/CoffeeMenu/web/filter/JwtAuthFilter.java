package com.coffeemenu.CoffeeMenu.web.filter;

import com.coffeemenu.CoffeeMenu.utils.JwtTokenUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        /*
        <pre>
        * Extract the "Authorization" header(e.g. "Bearer <token>")
        </pre>
         */
        String authHeader = request.getHeader("Authorization");

        String token = null;
        String username = null;

        /// Validate header format
        if(authHeader != null && authHeader.startsWith("Bearer ")){
            token = authHeader.substring(7);
            /// Extract username from token
            username = jwtTokenUtil.extractUsername(token);
        }

        /// If we have a username and no authentication uet
        if(username!= null && SecurityContextHolder.getContext().getAuthentication() == null){
            /// Load user details
            var userDetails = userDetailsService.loadUserByUsername(username);

            /// Validate token
        if(jwtTokenUtil.validToken(token,userDetails)){
            /// Create authentication  and set it in the security context
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
        }

        ///  Continue the filter chain
        filterChain.doFilter(request,response);


    }
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return request.getRequestURI().equals("/api/v1/login") && "POST".equalsIgnoreCase(request.getMethod());
    }
}
