package br.com.aluraflix.service;

import br.com.aluraflix.controller.dto.CategoriaDTO;
import br.com.aluraflix.model.Categoria;
import br.com.aluraflix.repository.CategoriaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoriaService implements ICategoriaService{
    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository){
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public Categoria cadastraCategoria(CategoriaDTO dto) {
        return categoriaRepository.save(dto.toModel());
    }

    @Override
    public List<Categoria> exibirCategoria(){
        return categoriaRepository.findAll();
    }

    @Override
    public Categoria buscaCategoriaPorId(Long id){
        return categoriaRepository.findById(id).orElseThrow(() -> new RuntimeException("Categoria não encontrada!"));
    }


    @Override
    public void deletaCategoriaPorId(Long id){
        var categoria = categoriaRepository.findById(id);
        if (categoria.isPresent()){
            categoriaRepository.deleteById(id);
        } else {
            throw new RuntimeException("Categoria não encontrada!");
        }
    }

}
