package org.szachniewicz.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.szachniewicz.model.Role;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {


    public JWTAuthorizationFilter(AuthenticationManager authManager) {
        super(authManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer")) {
            chain.doFilter(req, res);
            return;
        }
        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String userName = getUsernameFromToken(request);
        return getUsernamePasswordAuthenticationToken(userName);
    }

    private UsernamePasswordAuthenticationToken getUsernamePasswordAuthenticationToken(String userName) {
        return  new UsernamePasswordAuthenticationToken("test", null, new HashSet<SimpleGrantedAuthority>());
    }

    private Set<SimpleGrantedAuthority> getAuthorities(Role role) {
        HashSet<SimpleGrantedAuthority> authorities = new HashSet<>();
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_" + role.getName().toUpperCase());
        authorities.add(simpleGrantedAuthority);
        return authorities;
    }


    private String getUsernameFromToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null) {
            return JWT.require(Algorithm.HMAC512("TEST".getBytes()))
                    .build()
                    .verify(token.replace("Bearer", ""))
                    .getSubject();

        }
        return "";
    }
}