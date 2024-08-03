package com.github.humbertofernandes7.carros.configs;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.github.humbertofernandes7.carros.entites.UsuarioEntity;
import com.github.humbertofernandes7.carros.repositories.UsuarioRepository;
import com.github.humbertofernandes7.carros.services.AuthService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

	@Autowired
	private AuthService authService;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = capturaTokenHeader(request);

        if (token != null) {
            String login = authService.validaTokenJwt(token);
            UsuarioEntity usuario = usuarioRepository.findByLogin(login);
            
            var autentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(autentication);
        }

        filterChain.doFilter(request, response);
    }

	
	public String capturaTokenHeader(HttpServletRequest request) {
		String authHeader = request.getHeader("Authorization");
		
		if (authHeader == null) {
			return null;
		}
		if (!authHeader.split(" ")[0].equals("Bearer")) {
			return null;
		}
		return authHeader.split(" ")[1];
	}
}

