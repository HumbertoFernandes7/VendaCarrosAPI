package com.github.humbertofernandes7.carros.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import com.github.humbertofernandes7.carros.entites.UsuarioEntity;
import com.github.humbertofernandes7.carros.enums.RoleEnum;
import com.github.humbertofernandes7.carros.services.UsuarioService;

@Configuration
public class ExecutaAposInicioDaAplicacao implements ApplicationRunner {

	@Autowired
	private UsuarioService usuarioService;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		if(usuarioService.listaTodosUsuarios().size() < 1) {
			UsuarioEntity usuarioAdmin = new UsuarioEntity();
			usuarioAdmin.setId(1L);
			usuarioAdmin.setLogin("admin");
			usuarioAdmin.setNome("Administrador");
			usuarioAdmin.setRole(RoleEnum.ADMIN);
			usuarioAdmin.setSenha("admin");
			usuarioService.cadastrarUsuario(usuarioAdmin);
		}
	}		

}