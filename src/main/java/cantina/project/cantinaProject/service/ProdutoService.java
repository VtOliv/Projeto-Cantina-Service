package cantina.project.cantinaProject.service;

import static org.springframework.data.jpa.domain.Specification.where;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cantina.project.cantinaProject.domain.Produto;
import cantina.project.cantinaProject.domain.filter.ProdutoFilter;
import cantina.project.cantinaProject.repository.ProdutoRepository;
import cantina.project.cantinaProject.util.Conditions;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProdutoService extends Conditions<Produto>{

    private final ProdutoRepository produtoRepository;

    public Page<Produto> listarTodos(Pageable pageable) {
        log.info("Listando todos os produtos");
        return produtoRepository.findAll(pageable);
    }
    
    public Page<Produto> listaFiltrada(ProdutoFilter filter, Pageable pageable) {
    	return produtoRepository.findAll(
    			where(equals("idProduto", filter.getIdProduto()))
    			.and(like("nomeProduto", filter.getNomeProduto()))
    			.and(equals("idCategoria", filter.getIdCategoria())), pageable);
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
        return produtoRepository.findById(id)
                .map(produto -> {
                    produto.setNomeProduto(verificarCampos(produto.getNomeProduto(), produtoAtualizado.getNomeProduto()));
                    produto.setDescricaoProduto(verificarCampos(produto.getDescricaoProduto(), produtoAtualizado.getDescricaoProduto()));
                    produto.setIdCategoria(produtoAtualizado.getIdCategoria() != null ? produtoAtualizado.getIdCategoria() : produto.getIdCategoria());
                    produto.setQuantidadeEstoque(produtoAtualizado.getQuantidadeEstoque() != 0 ? produtoAtualizado.getQuantidadeEstoque() : produto.getQuantidadeEstoque());
                    produto.setPrecoVenda(produtoAtualizado.getPrecoVenda() != 0.0 ? produtoAtualizado.getPrecoVenda() : produto.getPrecoVenda());
                    produto.setPrecoCusto(produtoAtualizado.getPrecoCusto() != 0.0 ? produtoAtualizado.getPrecoCusto() : produto.getPrecoCusto());
                    produto.setImagem(verificarCampos(produto.getImagem(), produtoAtualizado.getImagem()));
                    produto.setDataCriacao(produtoAtualizado.getDataCriacao() != null ? produtoAtualizado.getDataCriacao() : produto.getDataCriacao());
                    return produtoRepository.save(produto);
                })
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
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
