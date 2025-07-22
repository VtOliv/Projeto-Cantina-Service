package com.project.cantina.domain.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioLoginForm {
	
	private String cpfUsuario;

	private String senhaUsuario;
}