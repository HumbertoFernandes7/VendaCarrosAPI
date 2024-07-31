package com.github.humbertofernandes7.carros.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

	public List<CarroEntity> listaTodosCarros() {
		return carroRepository.findAll();
	}

	public CarroEntity buscaCarroPorId(Long id) {
		return carroRepository.findById(id).orElseThrow(() -> new RuntimeException("erro"));
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
