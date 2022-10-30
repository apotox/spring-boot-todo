package com.safi.myfirst.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

@Slf4j
public class CustomHeaderAuth extends OncePerRequestFilter
{

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
    {
        if(request.getServletPath().equals("/login")){
          filterChain.doFilter(request, response);
        }else{

            String authToken = request.getHeader("authorization");
            Algorithm alg = Algorithm.HMAC256("mysecret".getBytes());

           JWTVerifier verifier = JWT.require(alg).build();

            DecodedJWT decodedJWT = verifier.verify(authToken);

            String role = decodedJWT.getClaim("authorities").asString();

            Collection<SimpleGrantedAuthority> authorities = new ArrayList();



            log.info(" ------------ decoded token {} {}",role, decodedJWT.getClaim("authorities"));


            authorities.add(new SimpleGrantedAuthority(role));

            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                decodedJWT.getSubject(),
                null,
                authorities
            );

            SecurityContextHolder.getContext().setAuthentication(token);


            filterChain.doFilter(request, response);


        }
    }
}
