package com.project.cantina.controller;

import static org.springframework.data.domain.Sort.Direction.ASC;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

import com.project.cantina.domain.Produto;
import com.project.cantina.domain.filter.ProdutoFilter;
import com.project.cantina.service.ProdutoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/produtos")
@Tag(name = "Produto", description = "API para gerenciamento de produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    @Operation(summary = "Listar todos os produtos")
    @GetMapping
    public ResponseEntity<Page<Produto>> listarTodos(@PageableDefault(sort = "idProduto", direction = ASC, size = 20) Pageable pageable) {
        log.info("Requisição para listar todos os produtos");
        return ResponseEntity.ok(produtoService.listarTodos(pageable));
    }
    
    @Operation(summary = "Listar todos os produtos")
    @GetMapping("/search")
    public ResponseEntity<Page<Produto>> buscarProdutos(ProdutoFilter filter,
    		@PageableDefault(sort = "idProduto", direction = ASC, size = 20) Pageable pageable) {
        log.info("Requisição para listar todos os produtos por um filtro");
        return ResponseEntity.ok(produtoService.listaFiltrada(filter, pageable));
    }
    

    @Operation(summary = "Buscar produto por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable Integer id) {
        log.info("Requisição para buscar produto com ID: {}", id);
        return produtoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Salvar um novo produto")
    @PostMapping
    public ResponseEntity<Produto> salvar(@RequestBody Produto produto) {
        log.info("Requisição para salvar um novo produto: {}", produto);
        Produto produtoSalvo = produtoService.salvar(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoSalvo);
    }
    
    @Operation(summary = "Salvar uma lista de novos produtos")
    @PostMapping("/salvar-todos")
    public ResponseEntity<List<Produto>> salvarTodos(@RequestBody List<Produto> produtos) {
        log.info("Requisição para salvar uma lista de produtos");
        List<Produto> produtosSalvos = produtoService.salvarTodos(produtos);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtosSalvos);
    }

    @Operation(summary = "Atualizar um produto existente")
    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizar(@PathVariable Integer id, @RequestBody Produto produto) {
        log.info("Requisição para atualizar produto com ID: {}", id);
        try {
            Produto produtoAtualizado = produtoService.atualizar(id, produto);
            return ResponseEntity.ok(produtoAtualizado);
        } catch (RuntimeException e) {
            log.error("Erro ao atualizar produto: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Excluir um produto")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        log.info("Requisição para excluir produto com ID: {}", id);
        produtoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
