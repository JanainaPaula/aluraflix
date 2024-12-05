package br.com.aluraflix.service;

import br.com.aluraflix.controller.dto.UpdateVideoDTO;
import br.com.aluraflix.controller.dto.VideoDTO;
import br.com.aluraflix.model.Categoria;
import br.com.aluraflix.model.Video;
import br.com.aluraflix.repository.VideoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class VideoService implements IVideoService{

    private final VideoRepository repository;
    private final CategoriaService categoriaService;

    public VideoService(VideoRepository repository, CategoriaService categoriaService) {
        this.repository = repository;
        this.categoriaService = categoriaService;
    }

    @Override
    public Video cadastra(VideoDTO dto) {
        Categoria categoria = buscaCategoria(dto.categoriaId());
        return repository.save(dto.toModel(categoria));
    }

    @Override
    public List<Video> exibir() {
        return repository.findAll();
    }

    @Override
    public Video buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Vídeo não encontrado!"));
    }

    @Override
    public void deletaPorId(Long id){
        var video = repository.findById(id);
        if (video.isPresent()){
            repository.deleteById(id);
        }
        else {
            throw new RuntimeException("Vídeo não encontrado!");
        }
    }

    @Override
    public Video atualizaPorId(UpdateVideoDTO dto, Long id) {
        var video = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Video não encontrado!"));
        return repository.save(video.update(dto));
    }

    private Categoria buscaCategoria(Long categoriaId) {
        return categoriaService.buscaCategoriaPorId(Objects.requireNonNullElse(categoriaId, 52L));
    }

    public List<Video> buscaVideos(String search){
        return repository.findByTituloContainingIgnoreCase(search).orElseThrow(() -> new RuntimeException("Nenhum vídeo foi encontrado!"));
    }
}
