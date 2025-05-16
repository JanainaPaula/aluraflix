package br.com.aluraflix.controller.dto;

import br.com.aluraflix.model.Usuario;

public record UsuarioResponseDTO(
        Long id,
        String login
) {
    public static UsuarioResponseDTO from(Usuario usuario){
        return new UsuarioResponseDTO(usuario.getId(), usuario.getUsername());
    }
}
