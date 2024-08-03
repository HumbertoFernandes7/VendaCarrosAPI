package com.github.humbertofernandes7.carros.services;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.github.humbertofernandes7.carros.entites.CarroEntity;
import com.github.humbertofernandes7.carros.repositories.CarroRepository;

import jakarta.transaction.Transactional;

@Service
public class CarroService {

	@Autowired
	private CarroRepository carroRepository;
	

	@Transactional
	public CarroEntity cadastrarCarro(CarroEntity carroConvertido) {
		return carroRepository.save(carroConvertido);
	}

	public Page<CarroEntity> listaTodosCarros(Pageable paginacao) {
		return carroRepository.findAll(paginacao);
	}

	public CarroEntity buscaCarroPorId(Long id) {
		return carroRepository.findById(id).orElseThrow(() -> new RuntimeErrorException(null, "Carro não encontrado pelo ID: " + id));
	}

	@Transactional
	public CarroEntity atualizaCarro(CarroEntity carroEntity, Long id) {
		carroEntity.setId(id);
		return carroRepository.save(carroEntity);
	}

	@Transactional
	public void deletaCarro(CarroEntity carroEncontrado) {
		carroRepository.delete(carroEncontrado);
	}

}
