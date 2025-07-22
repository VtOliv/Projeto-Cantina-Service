package com.project.cantina.domain;

import java.util.Arrays;
import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoUsuario {
	ADMINISTRADOR("Admin", 1), COLABORADOR("Colaborador", 2);

	private final String tipo;
	private final int code;

	public static Optional<TipoUsuario> getTipoPorSigla(String value) {
		return Arrays.stream(TipoUsuario.values()).filter(accStatus -> accStatus.tipo.equalsIgnoreCase(value))
				.findFirst();
	}

	public static Optional<TipoUsuario> getTipoPorCodigo(int value) {
		return Arrays.stream(TipoUsuario.values()).filter(accStatus -> accStatus.code == value).findFirst();
	}
}