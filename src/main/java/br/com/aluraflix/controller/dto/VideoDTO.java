package br.com.aluraflix.controller.dto;

import br.com.aluraflix.model.Video;

public record VideoDTO(
        String titulo,
        String descricao,
        String url
) {
    public Video toModel(){
        return new Video(titulo(), descricao(), url());
    }
}
