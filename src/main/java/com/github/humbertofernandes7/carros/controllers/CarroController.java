package com.github.humbertofernandes7.carros.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.humbertofernandes7.carros.converts.CarroConvert;
import com.github.humbertofernandes7.carros.dtos.inputs.CarroInput;
import com.github.humbertofernandes7.carros.dtos.outputs.CarroOutput;
import com.github.humbertofernandes7.carros.entites.CarroEntity;
import com.github.humbertofernandes7.carros.services.CarroService;




@RestController
@RequestMapping("/carros")
public class CarroController {
	
	@Autowired
	private CarroService carroService;
	
	@Autowired
	private CarroConvert carroConvert;

	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("/cadastrar")
	public CarroOutput cadastraCarro(@RequestBody CarroInput carroInput) {
		CarroEntity carroEntity  = carroConvert.inputToEntity(carroInput);
		return carroConvert.entityToOutput(carroService.cadastrarCarro(carroEntity));
	}
	
	@GetMapping("/lista-todos")
	public List<CarroOutput> listaTodosCarros() {
		List<CarroEntity> carrosEncontrados = carroService.listaTodosCarros();
		return carroConvert.listEntityToListOutput(carrosEncontrados);
	}
	
	@GetMapping("busca/{id}")
	public CarroOutput buscaCarroPorId(@PathVariable Long id) {
		CarroEntity carroEncontrado = carroService.buscaCarroPorId(id);
		return carroConvert.entityToOutput(carroEncontrado);
	}
	
	@PutMapping("atualiza/{id}")
	public CarroOutput atualizaCarro(@PathVariable Long id, @RequestBody CarroInput carroInput) {
		CarroEntity carroEntity = carroConvert.inputToEntity(carroInput);
		return carroConvert.entityToOutput(carroService.atualizaCarro(carroEntity, id));
	}
	
	@DeleteMapping("/deleta/{id}")
	public void deletaCarro(@PathVariable Long id) {
		CarroEntity carroEncontrado = carroService.buscaCarroPorId(id);
		carroService.deletaCarro(carroEncontrado);
	}
}
	