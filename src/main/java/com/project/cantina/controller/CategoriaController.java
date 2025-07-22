package com.project.cantina.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.cantina.domain.Categoria;
import com.project.cantina.service.CategoriaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/categorias")
@Tag(name = "Categorias", description = "Endpoints para gerenciamento de categorias")
@Slf4j
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    @Operation(summary = "Listar todas as categorias", description = "Retorna uma lista com todas as categorias")
    public List<Categoria> getAllCategorias() {
        log.info("Requisição para listar todas as categorias");
        return categoriaService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar categoria por ID", description = "Retorna uma categoria específica pelo seu ID")
    public ResponseEntity<Categoria> getCategoriaById(
            @Parameter(description = "ID da categoria a ser buscada", example = "1") @PathVariable Integer id) {
        log.info("Requisição para buscar categoria com ID: {}", id);
        return categoriaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Criar uma nova categoria", description = "Cria uma nova categoria com os dados fornecidos")
    public Categoria createCategoria(@RequestBody Categoria categoria) {
        log.info("Requisição para criar uma nova categoria");
        return categoriaService.save(categoria);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar uma categoria", description = "Atualiza uma categoria existente com os novos dados fornecidos")
    public ResponseEntity<Categoria> updateCategoria(
            @Parameter(description = "ID da categoria a ser atualizada", example = "1") @PathVariable Integer id,
            @RequestBody Categoria categoriaDetails) {
        log.info("Requisição para atualizar a categoria com ID: {}", id);
        Categoria updatedCategoria = categoriaService.atualizar(id, categoriaDetails);
        return ResponseEntity.ok(updatedCategoria);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir uma categoria", description = "Exclui uma categoria pelo seu ID")
    public ResponseEntity<Void> deleteCategoria(
            @Parameter(description = "ID da categoria a ser excluída", example = "1") @PathVariable Integer id) {
        log.info("Requisição para excluir categoria com ID: {}", id);
        categoriaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
