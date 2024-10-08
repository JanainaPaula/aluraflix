package br.com.aluraflix.service;

import br.com.aluraflix.controller.dto.UpdateVideoDTO;
import br.com.aluraflix.controller.dto.VideoDTO;
import br.com.aluraflix.model.Video;
import br.com.aluraflix.repository.VideoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoService implements IVideoService{

    private final VideoRepository repository;

    public VideoService(VideoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Video cadastra(VideoDTO dto) {
        return repository.save(dto.toModel());
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
}
