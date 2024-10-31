package cantina.project.cantinaProject.domain;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import java.io.Serializable;
import java.time.LocalDate;

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
@Table(name = "Categoria")
@Schema(description = "Representa a categoria de um produto")
public class Categoria implements Serializable {

	private static final long serialVersionUID = 6942164964810354538L;

	@Id
	@Schema(description = "O ID único da categoria", example = "1")
	@Column(name = "idCategoria")
	private Integer idCategoria;

	@NotNull
	@Schema(description = "O nome da categoria", requiredMode = REQUIRED, example = "Bebidas")
	@Column(name = "nomeCategoria")
	private String nomeCategoria;

	@NotNull
	@Schema(description = "A descrição da categoria", requiredMode = REQUIRED, example = "Produtos líquidos e refrigerantes")
	@Column(name = "descricaoCategoria")
	private String descricaoCategoria;

	@NotNull
	@Schema(description = "URL da imagem da categoria", requiredMode = REQUIRED, example = "http://example.com/imagem.jpg")
	@Column(name = "imagemCategoria")
	private String imagemCategoria;

	@NotNull
	@Schema(description = "A data de criação da categoria", requiredMode = REQUIRED, example = "2023-05-20")
	@Column(name = "dataCriacao")
	private LocalDate dataCriacao;
}
