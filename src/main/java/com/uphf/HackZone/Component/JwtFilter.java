package com.uphf.HackZone.Component;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        String token = null;


        System.out.println("--- Filtre JWT : Vérification de la requête " + request.getRequestURI());

        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("Authorization".equals(cookie.getName())) {
                    token = cookie.getValue();
                    System.out.println("--- Filtre JWT : Cookie 'Authorization' trouvé !");
                }
            }
        } else {
            System.out.println("--- Filtre JWT : Aucun cookie trouvé.");
        }

        if (token != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            try {

                String userMail = jwtUtil.extractUserMail(token);
                System.out.println("--- Filtre JWT : Mail extrait du token -> " + userMail);

                if (jwtUtil.validateToken(token, userMail)) {
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(userMail, null, Collections.emptyList());
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                    System.out.println("--- Filtre JWT : Authentification réussie pour " + userMail);
                } else {
                    System.out.println("--- Filtre JWT : Token invalide !");
                }
            } catch (Exception e) {

                System.out.println("--- ERREUR FATALE DANS LE FILTRE : " + e.getMessage());
                e.printStackTrace();
            }
        }
        chain.doFilter(request, response);
    }
}