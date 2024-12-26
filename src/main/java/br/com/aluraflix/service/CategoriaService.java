package br.com.aluraflix.service;

import br.com.aluraflix.controller.dto.CategoriaDTO;
import br.com.aluraflix.controller.dto.UpdateCategoriaDTO;
import br.com.aluraflix.model.Categoria;
import br.com.aluraflix.model.Video;
import br.com.aluraflix.repository.CategoriaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public Page<Categoria> exibirCategoria(Integer page, Integer size){
        var pageble = PageRequest.of(page,size);
        return categoriaRepository.findAll(pageble);
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

    @Override
    public Categoria atualizaCategoria(Long id, UpdateCategoriaDTO dto) {
        var categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada!"));
        return categoriaRepository.save(categoria.update(dto));
    }

    @Override
    public List<Video> getVideosPorId(Long id) {
        var categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada!"));
        return categoria.getVideos();
    }

}
