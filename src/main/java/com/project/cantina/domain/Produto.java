package com.project.cantina.domain;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "produto")
@Schema(description = "Representa um produto disponível na cantina")
public class Produto implements Serializable {

	private static final long serialVersionUID = 8149069993758063545L;

	@Id
	@Schema(description = "O ID único do produto", example = "1")
	private Integer idProduto;

	@NotNull
	@Size(max = 100)
	@Schema(description = "O nome do produto", requiredMode = REQUIRED, example = "Coca-Cola")
	private String nomeProduto;

	@NotNull
	@Size(max = 500)
	@Schema(description = "A descrição do produto", requiredMode = REQUIRED, example = "Refrigerante sabor cola")
	private String descricaoProduto;

	@NotNull
	@Schema(description = "O ID da categoria do produto", requiredMode = REQUIRED, example = "2")
	private Integer idCategoria;

	@NotNull
	@Schema(description = "A quantidade em estoque", requiredMode = REQUIRED, example = "100")
	private int quantidadeEstoque;

	@NotNull
	@Schema(description = "O preço de venda do produto", requiredMode = REQUIRED, example = "5.99")
	private double precoVenda;

	@JsonIgnore
	@Schema(description = "O preço de custo do produto, não será exibido na documentação")
	private double precoCusto;

	@NotNull
	@Schema(description = "A data de criação do produto", requiredMode = REQUIRED, example = "2023-05-20")
	private LocalDate dataCriacao;
}
