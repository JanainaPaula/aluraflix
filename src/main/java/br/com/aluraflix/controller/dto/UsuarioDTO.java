package br.com.aluraflix.controller.dto;

import jakarta.validation.constraints.NotBlank;

public record UsuarioDTO(
        @NotBlank(message = "O campo é obrigatório.")
        String login,

        @NotBlank(message = "O campo é obrigatório.")
        String senha
) {
}
