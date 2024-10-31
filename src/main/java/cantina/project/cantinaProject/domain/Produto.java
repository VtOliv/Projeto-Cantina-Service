package cantina.project.cantinaProject.domain;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "Produto")
@Schema(description = "Representa um produto disponível na cantina")
public class Produto implements Serializable {

	private static final long serialVersionUID = 8149069993758063545L;

	@Id
	@Schema(description = "O ID único do produto", example = "1")
	@Column(name = "idProduto")
	private Integer idProduto;

	@NotNull
	@Size(max = 100)
	@Schema(description = "O nome do produto", requiredMode = REQUIRED, example = "Coca-Cola")
	@Column(name = "nomeProduto")
	private String nomeProduto;

	@NotNull
	@Size(max = 500)
	@Schema(description = "A descrição do produto", requiredMode = REQUIRED, example = "Refrigerante sabor cola")
	@Column(name = "descricaoProduto")
	private String descricaoProduto;

	@NotNull
	@Schema(description = "O ID da categoria do produto", requiredMode = REQUIRED, example = "2")
	@Column(name = "categoriaProduto")
	private Integer idCategoria;

	@NotNull
	@Schema(description = "A quantidade em estoque", requiredMode = REQUIRED, example = "100")
	@Column(name = "quantidadeEstoque")
	private int quantidadeEstoque;

	@NotNull
	@Schema(description = "O preço de venda do produto", requiredMode = REQUIRED, example = "5.99")
	@Column(name = "precoVenda")
	private double precoVenda;

	@JsonIgnore
	@Schema(description = "O preço de custo do produto, não será exibido na documentação")
	@Column(name = "precoCusto")
	private double precoCusto;

	@NotNull
	@Schema(description = "URL da imagem do produto", requiredMode = REQUIRED, example = "http://example.com/imagem.jpg")
	@Column(name = "imagem")
	private String imagem;

	@NotNull
	@Schema(description = "A data de criação do produto", requiredMode = REQUIRED, example = "2023-05-20")
	@Column(name = "dataCriacao")
	private LocalDate dataCriacao;
}
