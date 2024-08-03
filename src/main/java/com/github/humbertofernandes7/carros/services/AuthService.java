package com.github.humbertofernandes7.carros.services;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.github.humbertofernandes7.carros.dtos.inputs.AuthInput;
import com.github.humbertofernandes7.carros.entites.UsuarioEntity;
import com.github.humbertofernandes7.carros.repositories.UsuarioRepository;

@Service
public class AuthService implements UserDetailsService {

	@Value("${carros.api.jwt.secret}")
	private String SECRET_KEY;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		return usuarioRepository.findByLogin(login);

	}

	public String obtenToken(AuthInput authInput) {
		UsuarioEntity usuarioEntity = usuarioRepository.findByLogin(authInput.getLogin());
		return geraTokenJwt(usuarioEntity);
	}

	public String geraTokenJwt(UsuarioEntity usuarioEntity) {
		try {

			Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

			String token = JWT.create().withIssuer("carros-api").withClaim("id", usuarioEntity.getId())
					.withSubject(usuarioEntity.getLogin()).withClaim("cargo", usuarioEntity.getRole().toString())
					.withExpiresAt(geraDataExpiracaoToken()).sign(algorithm);

			return token;

		} catch (JWTCreationException exception) {
			throw new RuntimeErrorException(null, "Ocorreu um erro ao gerar o Token, tente novamente");
		}

	}

	public String validaTokenJwt(String token) {

		try {
			Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

			return JWT.require(algorithm).withIssuer("carros-api").build().verify(token).getSubject();

		} catch (JWTVerificationException jwtVerificationException) {
			throw new RuntimeErrorException(null, "Ocorreu um erro ao validar o Token, tente novamente");
		}
	}

	private Instant geraDataExpiracaoToken() {
		return LocalDateTime.now().plusHours(8).toInstant(ZoneOffset.of("-03:00"));
	}
}
