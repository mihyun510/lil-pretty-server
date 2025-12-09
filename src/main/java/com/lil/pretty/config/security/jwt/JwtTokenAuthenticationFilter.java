package com.lil.pretty.config.security.jwt;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.lil.pretty.config.security.auth.AuthDetailService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {

    private final AuthDetailService authDetailService;
    private final JwtTokenProvider jwtAuthenticationService;

    public JwtTokenAuthenticationFilter(AuthDetailService authDetailService, JwtTokenProvider jwtAuthenticationService) {
        this.authDetailService = authDetailService;
        this.jwtAuthenticationService = jwtAuthenticationService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

    	 // 요청 URI 가져오기
        String requestURI = request.getRequestURI();

        // 인증 요청에 대해서만 필터를 패스
        if (requestURI.startsWith("/auth/")) {
            filterChain.doFilter(request, response);
            return;
        }
        
    	//헤더 인증값 가져오기
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String usId;

        //헤더에 token값이 없으면 패스한다.
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        //jwt 추출한다.
        jwt = authHeader.substring(7);  // "Bearer " 이후의 JWT 추출
        usId = jwtAuthenticationService.extractUserId(jwt); //username 꺼내오
        String role = jwtAuthenticationService.extractRole(jwt); // role 꺼내오기

        //String role = claims.get("role", String.class);
        log.info("Extracted Role: " + role);
        log.info("Extracted usId: " + usId);
        log.info("Authorization Header: " + authHeader);

        //username 꺼내와서 jwt와 user token 비교
        if (usId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = authDetailService.loadUserByUsername(usId);
            if (jwtAuthenticationService.validateToken(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken); //인증객체 생
            }
        }
        filterChain.doFilter(request, response);
//        if (!"OPTIONS".equalsIgnoreCase(request.getMethod())) {
//            filterChain.doFilter(request, response);
//            return;
//        }
    }


}