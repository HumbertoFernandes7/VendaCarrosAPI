package com.github.humbertofernandes7.carros.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.humbertofernandes7.carros.dtos.inputs.AuthInput;
import com.github.humbertofernandes7.carros.services.AuthService;
import com.github.humbertofernandes7.carros.services.UsuarioService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private AuthService authService;

	@Autowired
	private UsuarioService usuarioService;

	@PostMapping
	public String auth(@RequestBody AuthInput authInput) {
		usuarioService.verificaLoginCadastrado(authInput.getLogin());
		UsernamePasswordAuthenticationToken tokenUsuario = new UsernamePasswordAuthenticationToken(authInput.getLogin(),
				authInput.getSenha());
		authenticationManager.authenticate(tokenUsuario);
		return authService.obtenToken(authInput);
	}
}
