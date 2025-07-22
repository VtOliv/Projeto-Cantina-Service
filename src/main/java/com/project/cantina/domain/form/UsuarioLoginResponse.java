package com.project.cantina.domain.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioLoginResponse {
	
	private String nomeUsuario;
	
	private Boolean loginValido;
}