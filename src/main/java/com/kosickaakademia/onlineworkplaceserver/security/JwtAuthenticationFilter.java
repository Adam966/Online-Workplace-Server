package com.kosickaakademia.onlineworkplaceserver.security;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.kosickaakademia.onlineworkplaceserver.entities.UserEntity;
import com.kosickaakademia.onlineworkplaceserver.dto.UserDTO;
import com.kosickaakademia.onlineworkplaceserver.repositories.UserRepository;
import lombok.val;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static com.kosickaakademia.onlineworkplaceserver.security.SecurityConstants.*;

///////////////////////////////////////// LOGIN USER /////////////////////////////////////////
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            UserEntity user = new ObjectMapper()
                    .readValue(request.getInputStream(), UserEntity.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getEmail(),
                            user.getPassword(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException {
        val user = userRepository.findUserEntityByEmail(((User) authResult.getPrincipal()).getUsername());
        val userResponseEntity = new UserDTO(
                user.getId(),
                user.getUserName(),
                user.getUserName(),
                user.getEmail(),
                user.getPhoto()
        );

        String token = JWT.create()
                .withSubject(user.getId().toString())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(HMAC512(SECRET.getBytes()));

        val gson = new Gson();
        val json = gson.toJson(userResponseEntity);

        response.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
        response.setContentType("application/json");
        response.getWriter().write(json);
    }
}
