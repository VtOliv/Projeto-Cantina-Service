package com.project.cantina.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.project.cantina.domain.Usuario;

public interface UsuarioRepository extends MongoRepository<Usuario, Long>{

	Optional<Usuario> findByCpfUsuarioAndSenhaUsuario(String cpfUsuario, String senhaUsuario);
}
