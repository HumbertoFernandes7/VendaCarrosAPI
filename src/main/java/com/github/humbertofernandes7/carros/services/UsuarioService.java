package com.github.humbertofernandes7.carros.services;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.humbertofernandes7.carros.entites.UsuarioEntity;
import com.github.humbertofernandes7.carros.repositories.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Transactional
	public UsuarioEntity cadastrarUsuario(UsuarioEntity usuarioEntity) {
		UsuarioEntity loginExistente = usuarioRepository.findByLogin(usuarioEntity.getLogin());
		
		if(loginExistente != null) {
			throw new RuntimeErrorException(null, "Login já cadastrado no sistema!");
		}
		
		String senhaCriptografada = passwordEncoder.encode(usuarioEntity.getSenha());
		usuarioEntity.setSenha(senhaCriptografada);
		
		return usuarioRepository.save(usuarioEntity);
	}

	public void verificaLoginCadastrado(String login) {
		UsuarioEntity loginEncontrado = usuarioRepository.findByLogin(login);
		if(loginEncontrado != null) {
			return;
		}
		throw new RuntimeErrorException(null, "Login não cadastrado no sistema");
	}

	public List<UsuarioEntity> listaTodosUsuarios() {
		return usuarioRepository.findAll();
	}
}
