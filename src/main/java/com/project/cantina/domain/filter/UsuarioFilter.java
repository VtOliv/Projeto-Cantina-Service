package com.project.cantina.domain.filter;

import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioFilter {
	
	@Size(min = 11, max = 11, message = "O CPF deve conter 11 caracteres.")
	private String cpfUsuario;
	
	private String email;
	
	private Long idUsuario;
}
