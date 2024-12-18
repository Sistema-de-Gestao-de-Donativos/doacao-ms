package com.pes.doacao_ms.configs.security;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import java.io.IOException;
import java.util.Set;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ResponseStatusException;

import com.auth0.jwt.interfaces.DecodedJWT;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenValidator jwtTokenValidator;
    private final JwtUtil jwtUtil;

    private final Pattern doadorPattern = Pattern.compile("/v1/doador(/.*)?");
    private final Pattern doacaoPattern = Pattern.compile("/v1/doacao(/.*)?");
    private final Set<String> validRoles = Set.of("superadmin","adminAbrigo", "adminCD", "voluntario");
    private final Set<String> validRolesGetDoador = Set.of("superadmin", "adminCD", "voluntario");
    private final Set<String> validRolesPostDoador = Set.of("superadmin", "voluntario");
    private final Set<String> validRolesGetDoacao = Set.of("superadmin", "adminCD", "voluntario");
    private final Set<String> validRolesPostDoacao = Set.of("superadmin", "voluntario");

    @Autowired
    public JwtAuthenticationFilter(JwtTokenValidator jwtTokenValidator, JwtUtil jwtUtil) {
        this.jwtTokenValidator = jwtTokenValidator;
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String token = getTokenFromRequest(request);

        if (token != null) {
            try {
                // Valida o token JWT
                DecodedJWT decodedJWT = jwtTokenValidator.validateToken(token);

                // Extraindo informações do token
                String userId = decodedJWT.getSubject();
                String email = decodedJWT.getClaim("email").asString();
                String role = decodedJWT.getClaim("role").asString();
                System.out.println("role: "+role);
                if (validRoles.contains(role)) {
                    System.out.println("role valido");
                } else {
                    System.out.println("role invalido");
                    throw new ResponseStatusException(FORBIDDEN);
                }
                
                // Restrição de acesso baseada no método HTTP e endpoint 
                String requestURI = request.getRequestURI(); String method = request.getMethod();
                
                if (doadorPattern.matcher(requestURI).matches() && (method.equals("GET"))) {
                    if (!validRolesGetDoador.contains(role)) {
                        throw new ResponseStatusException(FORBIDDEN);
                    }
                }
                if (doadorPattern.matcher(requestURI).matches() && (method.equals("POST"))) {
                    if (!validRolesPostDoador.contains(role)) {
                        throw new ResponseStatusException(FORBIDDEN);
                    }
                }

                if (doacaoPattern.matcher(requestURI).matches() && (method.equals("GET"))) {
                    if (!validRolesGetDoacao.contains(role)) {
                        throw new ResponseStatusException(FORBIDDEN);
                    }
                }
                if (doacaoPattern.matcher(requestURI).matches() && (method.equals("POST"))) {
                    if (!validRolesPostDoacao.contains(role)) {
                        throw new ResponseStatusException(FORBIDDEN);
                    }
                }

                // Configura o JwtUtil com os detalhes
                jwtUtil.setJwtDetails(token, userId, email, role);

                // Marca o usuário como autenticado no contexto de segurança
                JwtAuthenticatedUser authenticatedUser = new JwtAuthenticatedUser(email, role);
                SecurityContextHolder.getContext().setAuthentication(authenticatedUser);

            } catch (Exception e) {
                // Token inválido ou erro na validação
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }

        filterChain.doFilter(request, response); // Continua o fluxo
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7); // Remove "Bearer " do token
        }
        return null;
    }
}

