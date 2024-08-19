package br.com.aluraflix.controller.dto;

public record UpdateVideoDTO(
        String titulo,
        String descricao,
        String url
) {}
