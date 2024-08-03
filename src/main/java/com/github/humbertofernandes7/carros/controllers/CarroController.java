package com.github.humbertofernandes7.carros.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
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
	@PostMapping("/admin/cadastrar")
	public CarroOutput cadastraCarro(@RequestBody CarroInput carroInput) {
		CarroEntity carroEntity = carroConvert.inputToEntity(carroInput);
		return carroConvert.entityToOutput(carroService.cadastrarCarro(carroEntity));
	}

	@PutMapping("/admin/atualiza/{id}")
	public CarroOutput atualizaCarro(@PathVariable Long id, @RequestBody CarroInput carroInput) {
		CarroEntity carroEntity = carroConvert.inputToEntity(carroInput);
		return carroConvert.entityToOutput(carroService.atualizaCarro(carroEntity, id));
	}

	@GetMapping("/user/lista-todos")
	public Page<CarroOutput> listaTodosCarros(
			@PageableDefault(page = 0, size = 10, sort = "valor", direction = Direction.DESC) Pageable paginacao) {
		Page<CarroEntity> carrosEncontrados = carroService.listaTodosCarros(paginacao);
		return carroConvert.listEntityToListOutput(carrosEncontrados);
	}

	@GetMapping("/user/busca/{id}")
	public CarroOutput buscaCarroPorId(@PathVariable Long id) {
		CarroEntity carroEncontrado = carroService.buscaCarroPorId(id);
		return carroConvert.entityToOutput(carroEncontrado);
	}

	@DeleteMapping("/admin/deleta/{id}")
	public void deletaCarro(@PathVariable Long id) {
		CarroEntity carroEncontrado = carroService.buscaCarroPorId(id);
		carroService.deletaCarro(carroEncontrado);
	}
}
