package com.project.cantina.domain;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(value = "categoria")
@Schema(description = "Representa a categoria de um produto")
public class Categoria implements Serializable {

	private static final long serialVersionUID = 6942164964810354538L;

	@Id
	@Schema(description = "O ID único da categoria", example = "1")
	private Integer idCategoria;

	@NotNull
	@Schema(description = "O nome da categoria", requiredMode = REQUIRED, example = "Bebidas")
	private String nomeCategoria;

	@NotNull
	@Schema(description = "A descrição da categoria", requiredMode = REQUIRED, example = "Produtos líquidos e refrigerantes")
	private String descricaoCategoria;

	@NotNull
	@Schema(description = "URL da imagem da categoria", requiredMode = REQUIRED, example = "http://example.com/imagem.jpg")
	private String imagemCategoria;

	@NotNull
	@Schema(description = "A data de criação da categoria", requiredMode = REQUIRED, example = "2023-05-20")
	private LocalDate dataCriacao;
}
