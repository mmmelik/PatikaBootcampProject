package dev.melik.patikabootcampproject.config.security;

import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header=request.getHeader(SecurityConstant.HEADER_STRING);

        if (header==null || !header.startsWith(SecurityConstant.TOKEN_PREFIX)){
            chain.doFilter(request,response);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(header);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String header) {
        String user = Jwts.parser()
                .setSigningKey(SecurityConstant.SECRET.getBytes())
                .parseClaimsJws(header.replace(SecurityConstant.TOKEN_PREFIX, ""))
                .getBody().getSubject();

        if (user == null) {
            throw new RuntimeException("Unable to parse user from token.");
        }

        return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());

    }
}
