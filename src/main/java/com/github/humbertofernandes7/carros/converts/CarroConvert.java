package com.github.humbertofernandes7.carros.converts;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.humbertofernandes7.carros.dtos.inputs.CarroInput;
import com.github.humbertofernandes7.carros.dtos.outputs.CarroOutput;
import com.github.humbertofernandes7.carros.entites.CarroEntity;

@Component
public class CarroConvert {

	@Autowired
	private ModelMapper modelMapper;

	public CarroEntity inputToEntity(CarroInput carroInput) {
		return modelMapper.map(carroInput, CarroEntity.class);
	}

	public CarroOutput entityToOutput(CarroEntity carroEntity) {
		return modelMapper.map(carroEntity, CarroOutput.class);
	}

	public List<CarroOutput> listEntityToListOutput(List<CarroEntity> carrosEncontrados) {
		return carrosEncontrados.stream().map(carros -> this.entityToOutput(carros)).collect(Collectors.toList());
	}

}
