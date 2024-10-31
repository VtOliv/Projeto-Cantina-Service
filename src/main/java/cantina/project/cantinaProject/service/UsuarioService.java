package cantina.project.cantinaProject.service;

import static cantina.project.cantinaProject.domain.TipoUsuario.COLABORADOR;
import static org.springframework.data.jpa.domain.Specification.where;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cantina.project.cantinaProject.domain.TipoUsuario;
import cantina.project.cantinaProject.domain.Usuario;
import cantina.project.cantinaProject.domain.filter.UsuarioFilter;
import cantina.project.cantinaProject.domain.form.AlterarUsuarioForm;
import cantina.project.cantinaProject.domain.form.UsuarioForm;
import cantina.project.cantinaProject.domain.form.UsuarioLoginForm;
import cantina.project.cantinaProject.domain.form.UsuarioLoginResponse;
import cantina.project.cantinaProject.repository.UsuarioRepository;
import cantina.project.cantinaProject.util.Conditions;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService extends Conditions<Usuario> {

	private final UsuarioRepository repository;

	public Usuario criarUsuario(UsuarioForm form) {

		var type = TipoUsuario.getTipoPorCodigo(form.getTipoUsuario()).orElse(COLABORADOR);

		var user = Usuario.builder()
				.nomeUsuario(form.getNomeUsuario())
				.senhaUsuario(form.getSenhaUsuario())
				.cpfUsuario(form.getCpfUsuario())
				.email(form.getEmail())
				.telefone(form.getTelefone())
				.tipoUsuario(type.getCode())
				.dataCriacao(LocalDate.now())
				.build();

		user.setIdUsuario(repository.count() + 1l);

		return repository.save(user);
	}
	
	public Usuario alterarUsuario(AlterarUsuarioForm form, Long id) {
		
		var user = repository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

		user.setEmail(verificarCampos(user.getEmail(), form.getEmail()));
		user.setNomeUsuario(verificarCampos(user.getNomeUsuario(), form.getNomeUsuario()));
		user.setTelefone(verificarCampos(user.getTelefone(), form.getTelefone()));
		user.setSenhaUsuario(verificarCampos(user.getSenhaUsuario(), form.getSenhaUsuario()));
		
		var tipo = TipoUsuario.getTipoPorCodigo(form.getTipoUsuario()).orElse(COLABORADOR);
		user.setTipoUsuario(tipo.getCode());

		return repository.save(user);
	}

	public Page<Usuario> buscarUsuarios(UsuarioFilter filter, Pageable pageable) {
		return repository.findAll(
				where(equals("idUsuario", filter.getIdUsuario()))
				.and(like("email", filter.getEmail()))
				.and(equals("cpfUsuario", filter.getCpfUsuario())), pageable);
	}

	public void deletarUsuarioPorId(Long id) {
		repository.deleteById(id);
	}
	
	public UsuarioLoginResponse efetuarLogin(UsuarioLoginForm form) {
		var usuario = repository.findByCpfUsuarioAndSenhaUsuario(form.getCpfUsuario(), form.getSenhaUsuario()).orElse(null);
		
		return UsuarioLoginResponse.builder()
				.nomeUsuario(usuario != null? usuario.getNomeUsuario() : "Login inválido")
				.loginValido(usuario != null)
				.build();
	}
}
