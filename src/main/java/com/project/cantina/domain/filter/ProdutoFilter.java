package com.project.cantina.domain.filter;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoFilter implements Serializable {

	private static final long serialVersionUID = 8149069993758063545L;

	private Integer idProduto;

	private String nomeProduto;

	private Integer idCategoria;
}
