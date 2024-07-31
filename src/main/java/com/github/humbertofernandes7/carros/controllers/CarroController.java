package com.github.humbertofernandes7.carros.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carros")
public class CarroController {

	@GetMapping("/teste")
	public String getMethodName() {
		return new String("teste");
	}
	
}
