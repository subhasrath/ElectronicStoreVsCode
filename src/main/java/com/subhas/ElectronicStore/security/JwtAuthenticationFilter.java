package com.subhas.ElectronicStore.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtHelper jwtHelper;

    Logger logger = LoggerFactory.getLogger(OncePerRequestFilter.class);
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        AUthorization
        String requestHeader = request.getHeader("Authorization");
        logger.info("Header : {}", requestHeader);

        String username = null;
        String token = null;

        if(requestHeader!=null && requestHeader.startsWith("Bearer")){
//            looking Good
            token = requestHeader.substring(7);
            try {
                username = jwtHelper.getUserNameFromToken(token);
            }catch (IllegalArgumentException e){
                logger.info("Illegal Argument while fetching the username !!");
                e.printStackTrace();
            }catch (MalformedJwtException e){
                logger.info("Some changes have done in token, Invalid Token !!");
                e.printStackTrace();
            }catch (ExpiredJwtException e){
                logger.info("Jwt token is expired !!");
                e.printStackTrace();
            }catch (Exception e){
                logger.info("Some exception Occurred !!");
                e.printStackTrace();
            }
        }
        else {
            logger.info("Invalid Header Value !!!");
        }

    }
}
