package br.com.aluraflix.controller.dto;

import br.com.aluraflix.model.Categoria;
import jakarta.validation.constraints.NotBlank;

public record CategoriaDTO(

        Long id,

        @NotBlank(message = "O campo é obrigatório.")
        String titulo,

        @NotBlank(message = "O campo é obrigatório.")
        String cor
) {
    public Categoria toModel(){
        return new Categoria(titulo(),cor());
    }
}
