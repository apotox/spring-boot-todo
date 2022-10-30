package com.safi.myfirst.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
public class CustomAuthFilter extends UsernamePasswordAuthenticationFilter
{

    private final AuthenticationManager authenticationManager;


    public CustomAuthFilter(AuthenticationManager authenticationManager)
    {
        this.authenticationManager = authenticationManager;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException
    {

        LoginForm loginForm;
        try
        {
            ObjectMapper mapper = new ObjectMapper();
            String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

            loginForm = mapper.readValue(body, LoginForm.class);

        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
            loginForm.getUsername(),
            loginForm.getPassword()
        );

        return  authenticationManager.authenticate(token);

    }


    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult)
        throws IOException, ServletException
    {
        User u = (User) authResult.getPrincipal();
        Algorithm alg = Algorithm.HMAC256("mysecret".getBytes());



        String access_token = JWT.create()
            .withSubject(u.getUsername())
            .withClaim("authorities", u.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining()))
            .withExpiresAt(new Date(System.currentTimeMillis() + 24*60*1000))
            .sign(alg);


        HashMap<String,String> tokens = new HashMap();
        tokens.put("access_token", access_token);

        response.setContentType(APPLICATION_JSON_VALUE);

         new ObjectMapper().writeValue(response.getOutputStream(), tokens);
    }
}
@Data
class LoginForm {
    private String username;
    private String password;
}
