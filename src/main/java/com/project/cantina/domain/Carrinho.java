package com.project.cantina.domain;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import java.io.Serializable;
import java.util.List;

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
@Document(value = "Venda")
@Schema(description = "Representa o carrinho de compras")
public class Carrinho implements Serializable {
    
	private static final long serialVersionUID = -6943391007476786241L;

	@Id
	@Schema(description = "O ID único do carrinho", example = "1")
    private String idCarrinho;
    
    @NotNull
    @Schema(description = "Lista de produtos no carrinho", requiredMode = REQUIRED)
    private List<Integer> idProdutos;
    
    @NotNull
    @Schema(description = "O valor total do carrinho", requiredMode = REQUIRED, example = "150.0")
    private double valorTotal;
    
    @NotNull
    @Schema(description = "ID do vendedor associado ao carrinho", requiredMode = REQUIRED, example = "123")
    private Integer vendedorId;
    
    @NotNull
    @Schema(description = "Indica se o carrinho está ativo", requiredMode = REQUIRED, example = "true")
    private boolean ativo;
}
