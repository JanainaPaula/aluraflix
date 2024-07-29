package br.com.aluraflix.service;

import br.com.aluraflix.controller.dto.VideoDTO;
import br.com.aluraflix.model.Video;

import java.util.List;

public interface IVideoService {
    Video cadastra(VideoDTO dto);
    List<Video> exibir();
}
