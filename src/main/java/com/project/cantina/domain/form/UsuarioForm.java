package com.project.cantina.domain.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioForm {
	
	private String nomeUsuario;
	
	private String senhaUsuario;

	@Size(min = 11, max = 11, message = "O CPF deve conter 11 caracteres.")
	private String cpfUsuario;

	@Size(min = 11, max = 11, message = "O telefone deve conter 11 caracteres.")
	private String telefone;

	@Email(message = "O e-mail informado é inválido.")
	private String email;

	private Integer tipoUsuario;
}