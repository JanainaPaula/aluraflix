package br.com.aluraflix.service;

import br.com.aluraflix.controller.dto.VideoDTO;
import br.com.aluraflix.model.Video;

public interface IVideoService {
    Video cadastra(VideoDTO dto);
}
