package com.github.humbertofernandes7.carros.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.humbertofernandes7.carros.entites.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

	UsuarioEntity findByLogin(String login);
}
