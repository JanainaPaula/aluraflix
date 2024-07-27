package br.com.aluraflix.controller.dto;

import br.com.aluraflix.model.Video;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

public record VideoDTO(
        @NotBlank(message = "O titulo do video não pode estar vazio.")
        String titulo,

        @NotBlank(message = "A descricão não pode estar vazia.")
        String descricao,

        @NotBlank(message = "A url precisa ser preenchida")
        @URL(message = "Url inválida")
        String url
) {
    public Video toModel(){
        return new Video(titulo(), descricao(), url());
    }
}
