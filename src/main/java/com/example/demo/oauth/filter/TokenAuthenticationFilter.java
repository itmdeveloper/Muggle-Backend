package com.example.demo.oauth.filter;


import com.example.demo.api.entity.user.User;
import com.example.demo.api.service.UserService;
import com.example.demo.oauth.token.AuthToken;
import com.example.demo.oauth.token.AuthTokenProvider;
import com.example.demo.utils.HeaderUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.demo.utils.realIP.getClientIP;

@Slf4j
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private final AuthTokenProvider tokenProvider;

    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(TokenAuthenticationFilter.class);

    public TokenAuthenticationFilter(AuthTokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String url=request.getRequestURI();
        logger.info(url);
        String tokenStr = HeaderUtil.getAccessToken(request);
        try {
            if (url.startsWith("/api/") ) {
                logger.info("IP:[" + getClientIP(request) + "]\tRequest:[" + request.getRequestURI() + "]");

                filterChain.doFilter(request, response);
            } else {
                AuthToken token = tokenProvider.convertAuthToken(tokenStr);

                if (token.validate()) {
                    Authentication authentication = tokenProvider.getAuthentication(token);
                    SecurityContextHolder.getContext().setAuthentication(authentication);

                    org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                    User user = userService.getUser(principal.getUsername());
                    logger.info("IP:[" + getClientIP(request) + "]\tRequest:[" + request.getRequestURI() + "]\tUser:[" + user.getProfileImageUrl() + "]");

                }
                filterChain.doFilter(request, response);

            }
        }catch (Exception e){
            logger.info(e.getMessage());
        }
    }
}
