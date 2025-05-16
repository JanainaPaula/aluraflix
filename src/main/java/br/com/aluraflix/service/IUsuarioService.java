package br.com.aluraflix.service;

import br.com.aluraflix.controller.dto.UsuarioDTO;
import br.com.aluraflix.controller.dto.UsuarioResponseDTO;
import br.com.aluraflix.model.Usuario;

public interface IUsuarioService {
    UsuarioResponseDTO cadastraUsuario(UsuarioDTO dto);
}
