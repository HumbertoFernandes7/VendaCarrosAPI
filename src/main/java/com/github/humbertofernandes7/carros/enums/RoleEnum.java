package com.github.humbertofernandes7.carros.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum RoleEnum {

	ADMIN("admin"),
	USER("user");
	
	private String role;
}
