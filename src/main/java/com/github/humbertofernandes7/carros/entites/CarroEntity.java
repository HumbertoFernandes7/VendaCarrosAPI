package com.github.humbertofernandes7.carros.entites;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_carros")
public class CarroEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "marca")
	private String marca; 
	
	@Column(name = "modelo")
	private String modelo;
	
	@Column(name = "valor", precision = 10, scale = 3)
	private BigDecimal valor;
	
	@Column(name = "foto", columnDefinition = "TEXT")
	private String foto;
	
	
}
