package com.project.cantina.service;

import static com.project.cantina.domain.TipoUsuario.COLABORADOR;
import static com.project.cantina.util.Utils.verificarCampos;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.project.cantina.domain.TipoUsuario;
import com.project.cantina.domain.Usuario;
import com.project.cantina.domain.filter.UsuarioFilter;
import com.project.cantina.domain.form.AlterarUsuarioForm;
import com.project.cantina.domain.form.UsuarioForm;
import com.project.cantina.domain.form.UsuarioLoginForm;
import com.project.cantina.domain.form.UsuarioLoginResponse;
import com.project.cantina.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {

	private final UsuarioRepository repository;
	private final MongoTemplate mongoTemplate;

	public Page<Usuario> buscarUsuarios(UsuarioFilter filter, Pageable pageable) {
	    Query query = new Query();

	    if (filter.getIdUsuario() != null) {
	        query.addCriteria(Criteria.where("idUsuario").is(filter.getIdUsuario()));
	    }

	    if (filter.getEmail() != null && !filter.getEmail().isEmpty()) {
	        query.addCriteria(Criteria.where("email").regex(".*" + filter.getEmail() + ".*", "i"));
	    }

	    if (filter.getCpfUsuario() != null && !filter.getCpfUsuario().isEmpty()) {
	        query.addCriteria(Criteria.where("cpfUsuario").is(filter.getCpfUsuario()));
	    }

	    long total = mongoTemplate.count(query, Usuario.class);

	    query.with(pageable);

	    List<Usuario> usuarios = mongoTemplate.find(query, Usuario.class);

	    return new PageImpl<>(usuarios, pageable, total);
	}

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
