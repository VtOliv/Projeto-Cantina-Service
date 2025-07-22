package com.project.cantina.service;

import static com.project.cantina.util.Utils.verificarCampos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.project.cantina.domain.Produto;
import com.project.cantina.domain.filter.ProdutoFilter;
import com.project.cantina.repository.ProdutoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProdutoService {

	private final ProdutoRepository produtoRepository;
	private final MongoTemplate mongoTemplate;

	public Page<Produto> listaFiltrada(ProdutoFilter filter, Pageable pageable) {
		Query query = new Query();

		if (filter.getIdProduto() != null) {
			query.addCriteria(Criteria.where("idProduto").is(filter.getIdProduto()));
		}

		if (filter.getNomeProduto() != null && !filter.getNomeProduto().isEmpty()) {
			query.addCriteria(Criteria.where("nomeProduto").regex(".*" + filter.getNomeProduto() + ".*", "i"));
		}

		if (filter.getIdCategoria() != null) {
			query.addCriteria(Criteria.where("idCategoria").is(filter.getIdCategoria()));
		}

		long total = mongoTemplate.count(query, Produto.class);

		query.with(pageable);

		List<Produto> produtos = mongoTemplate.find(query, Produto.class);

		return new PageImpl<>(produtos, pageable, total);
	}

	public Page<Produto> listarTodos(Pageable pageable) {
		log.info("Listando todos os produtos");
		return produtoRepository.findAll(pageable);
	}

	public Optional<Produto> buscarPorId(Integer id) {
		log.info("Buscando produto com ID: {}", id);
		return produtoRepository.findById(id);
	}

	public Produto salvar(Produto produto) {
		log.info("Salvando produto: {}", produto);
		return produtoRepository.save(produto);
	}

	public Produto atualizar(Integer id, Produto produtoAtualizado) {
		log.info("Atualizando produto com ID: {}", id);
		return produtoRepository.findById(id).map(produto -> {
			produto.setNomeProduto(verificarCampos(produto.getNomeProduto(), produtoAtualizado.getNomeProduto()));
			produto.setDescricaoProduto(
					verificarCampos(produto.getDescricaoProduto(), produtoAtualizado.getDescricaoProduto()));
			produto.setIdCategoria(produtoAtualizado.getIdCategoria() != null ? produtoAtualizado.getIdCategoria()
					: produto.getIdCategoria());
			produto.setQuantidadeEstoque(
					produtoAtualizado.getQuantidadeEstoque() != 0 ? produtoAtualizado.getQuantidadeEstoque()
							: produto.getQuantidadeEstoque());
			produto.setPrecoVenda(produtoAtualizado.getPrecoVenda() != 0.0 ? produtoAtualizado.getPrecoVenda()
					: produto.getPrecoVenda());
			produto.setPrecoCusto(produtoAtualizado.getPrecoCusto() != 0.0 ? produtoAtualizado.getPrecoCusto()
					: produto.getPrecoCusto());
			produto.setDataCriacao(produtoAtualizado.getDataCriacao() != null ? produtoAtualizado.getDataCriacao()
					: produto.getDataCriacao());
			return produtoRepository.save(produto);
		}).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
	}

	public void excluir(Integer id) {
		log.info("Excluindo produto com ID: {}", id);
		produtoRepository.deleteById(id);
	}

	public List<Produto> salvarTodos(List<Produto> produtos) {
		log.info("Salvando uma lista de produtos: {} produtos", produtos.size());
		return produtoRepository.saveAll(produtos);
	}
}
