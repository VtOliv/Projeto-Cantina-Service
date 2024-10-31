package cantina.project.cantinaProject.domain;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Venda")
@Schema(description = "Representa o carrinho de compras")
public class Carrinho implements Serializable {
    
	private static final long serialVersionUID = -6943391007476786241L;

	@Id
	@Column(name = "idCarrinho")
	@Schema(description = "O ID único do carrinho", example = "1")
    private String idCarrinho;
    
    @NotNull
    @Schema(description = "Lista de produtos no carrinho", requiredMode = REQUIRED)
    @Column(name = "produtos")
    private List<Integer> idProdutos;
    
    @NotNull
    @Schema(description = "O valor total do carrinho", requiredMode = REQUIRED, example = "150.0")
    @Column(name = "valorTotal")
    private double valorTotal;
    
    @NotNull
    @Schema(description = "ID do vendedor associado ao carrinho", requiredMode = REQUIRED, example = "123")
    @Column(name = "vendedor")
    private Integer vendedorId;
    
    @NotNull
    @Schema(description = "Indica se o carrinho está ativo", requiredMode = REQUIRED, example = "true")
    @Column(name = "ativo")
    private boolean ativo;
}
