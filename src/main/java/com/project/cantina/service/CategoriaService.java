package com.project.cantina.service;
import static com.project.cantina.util.Utils.verificarCampos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.cantina.domain.Categoria;
import com.project.cantina.repository.CategoriaRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> findAll() {
        log.info("Buscando todas as categorias");
        return categoriaRepository.findAll();
    }

    public Optional<Categoria> findById(Integer id) {
        log.info("Buscando categoria com ID: {}", id);
        return categoriaRepository.findById(id);
    }

    @Transactional
    public Categoria save(Categoria categoria) {
        log.info("Salvando nova categoria: {}", categoria);
        return categoriaRepository.save(categoria);
    }

    public Categoria atualizar(Integer id, Categoria categoriaAtualizada) {
        log.info("Atualizando categoria com ID: {}", id);
        return categoriaRepository.findById(id)
                .map(categoria -> {
                    categoria.setNomeCategoria(verificarCampos(categoria.getNomeCategoria(), categoriaAtualizada.getNomeCategoria()));
                    categoria.setDescricaoCategoria(verificarCampos(categoria.getDescricaoCategoria(), categoriaAtualizada.getDescricaoCategoria()));
                    categoria.setImagemCategoria(verificarCampos(categoria.getImagemCategoria(), categoriaAtualizada.getImagemCategoria()));
                    categoria.setDataCriacao(categoriaAtualizada.getDataCriacao() != null ? categoriaAtualizada.getDataCriacao() : categoria.getDataCriacao());
                    return categoriaRepository.save(categoria);
                })
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
    }

    @Transactional
    public void deleteById(Integer id) {
        log.info("Deletando categoria com ID: {}", id);
        categoriaRepository.deleteById(id);
    }
}
