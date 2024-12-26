package br.com.aluraflix.service;

import br.com.aluraflix.controller.dto.UpdateVideoDTO;
import br.com.aluraflix.controller.dto.VideoDTO;
import br.com.aluraflix.model.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IVideoService {
    Video cadastra(VideoDTO dto);
//    List<Video> exibir();
    Page<Video> exibir(Integer page, Integer size);
    Video buscarPorId(Long id);
    void deletaPorId(Long id);
    Video atualizaPorId(UpdateVideoDTO dto, Long id);
    List<Video> buscaVideos(String search);
}
