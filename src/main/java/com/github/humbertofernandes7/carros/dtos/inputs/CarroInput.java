package com.github.humbertofernandes7.carros.dtos.inputs;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarroInput {

	private String nome;

	private String marca;

	private String modelo;

	private BigDecimal valor;

	private String foto;
}
