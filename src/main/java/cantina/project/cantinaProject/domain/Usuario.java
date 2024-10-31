package cantina.project.cantinaProject.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

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
@Table(name = "Usuario")
@Schema(description = "Representa um usuário do sistema")
public class Usuario implements Serializable {

	private static final long serialVersionUID = -6272553933681736152L;

	@Id
	@Schema(description = "O ID único do usuário", example = "1")
	@Column(name = "idUsuario") 
	private Long idUsuario;

    @Size(min = 11, max = 11, message = "O CPF deve conter 11 caracteres.")
    @Schema(description = "O CPF do usuário",  example = "12345678901")
    @Column(name = "cpfUsuario") 
    private String cpfUsuario;
    
    @Schema(description = "O Nome do usuário",  example = "12345678901")
    @Column(name = "nomeUsuario") 
    private String nomeUsuario;

    @Size(min = 11, max = 11, message = "O telefone deve conter 11 caracteres.")
    @Schema(description = "O telefone do usuário",  example = "5511999999999")
    @Column(name = "telefone") 
    private String telefone;
    
    @Schema(description = "A senha do usuário",  example = "12345678901")
    @Column(name = "senhaUsuario") 
    private String senhaUsuario;

    @Email(message = "O e-mail informado é inválido.")
    @Schema(description = "O e-mail do usuário", example = "usuario@exemplo.com")
    @Column(name = "email") 
    private String email;

	@Schema(description = "Tipo de usuário cadastrado", example = "1")
	@Column(name = "tipoUsuario") 
	private Integer tipoUsuario;

	@Schema(description = "A data de criação do usuário", example = "2023-05-20")
	@Column(name = "dataCriacao") 
	private LocalDate dataCriacao;
}
