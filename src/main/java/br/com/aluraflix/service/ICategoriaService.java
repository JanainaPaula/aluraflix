package br.com.aluraflix.service;

import br.com.aluraflix.controller.dto.CategoriaDTO;
import br.com.aluraflix.controller.dto.UpdateCategoriaDTO;
import br.com.aluraflix.model.Categoria;
import br.com.aluraflix.model.Video;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ICategoriaService {
    Categoria cadastraCategoria(CategoriaDTO dto);
    Page<Categoria> exibirCategoria(Integer page, Integer size);
    Categoria buscaCategoriaPorId(Long id);
    void deletaCategoriaPorId(Long id);
    Categoria atualizaCategoria(Long id, UpdateCategoriaDTO dto);
    List<Video> getVideosPorId(Long id);
}
