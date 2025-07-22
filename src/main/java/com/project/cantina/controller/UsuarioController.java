package com.project.cantina.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.cantina.domain.Usuario;
import com.project.cantina.domain.filter.UsuarioFilter;
import com.project.cantina.domain.form.AlterarUsuarioForm;
import com.project.cantina.domain.form.UsuarioForm;
import com.project.cantina.domain.form.UsuarioLoginForm;
import com.project.cantina.domain.form.UsuarioLoginResponse;
import com.project.cantina.service.UsuarioService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/api/usuarios")
@Tag(name = "Usuários", description = "Endpoints para gerenciamento dos usuários")
@RestController
@RequiredArgsConstructor
public class UsuarioController {
	
	private final UsuarioService service;

	@GetMapping
	public ResponseEntity<Page<Usuario>> getUser(UsuarioFilter filter, Pageable pageable) {
		
		log.info("Buscando Usuários");
		
		var response = service.buscarUsuarios(filter, pageable);
		
		log.info("Encontrados {} usuarios", response.getTotalElements());
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@PostMapping
	public ResponseEntity<Usuario> newUser(@RequestBody UsuarioForm form){
		
		var newUser = service.criarUsuario(form);
		
		log.info("Usuario {} cadatrado com sucesso, Id: {}", newUser.getEmail(), newUser.getIdUsuario());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
	}
	
	@DeleteMapping
	public ResponseEntity<Void> deleteUser(Long id) {
		
		service.deletarUsuarioPorId(id);
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Usuario> updateUser(@RequestBody AlterarUsuarioForm form, @PathVariable Long id){
		
		var updatedUser = service.alterarUsuario(form, id);
		
		log.info("Usuario {} atualizado com sucesso, Id: {}", updatedUser.getEmail(), updatedUser.getIdUsuario());
		
		return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
	}
	
	@PostMapping("/login")
	public ResponseEntity<UsuarioLoginResponse> login(@RequestBody UsuarioLoginForm form){
		
		var logged = service.efetuarLogin(form);
		
		var status = logged.getLoginValido()? "Sucesso" : "Erro";
		
		log.info("Usuario {} logado com sucesso, Status: {}", logged.getNomeUsuario(), status);
		
		return ResponseEntity.status(HttpStatus.OK).body(logged);
	}
	
}
