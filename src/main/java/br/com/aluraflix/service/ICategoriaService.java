package br.com.aluraflix.service;

import br.com.aluraflix.controller.dto.CategoriaDTO;
import br.com.aluraflix.model.Categoria;
import java.util.List;

public interface ICategoriaService {
    Categoria cadastraCategoria(CategoriaDTO dto);
    List<Categoria> exibirCategoria();
    Categoria buscaCategoriaPorId(Long id);
    void deletaCategoriaPorId(Long id);
}
