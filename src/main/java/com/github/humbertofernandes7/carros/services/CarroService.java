package com.github.humbertofernandes7.carros.services;

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

	
}
