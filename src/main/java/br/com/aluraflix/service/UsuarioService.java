package br.com.aluraflix.service;

import br.com.aluraflix.controller.dto.UsuarioDTO;
import br.com.aluraflix.controller.dto.UsuarioResponseDTO;
import br.com.aluraflix.model.Usuario;
import br.com.aluraflix.repository.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UsuarioService implements IUsuarioService{

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Override
    public UsuarioResponseDTO cadastraUsuario(UsuarioDTO dto) {
        var usuario = Usuario.of(dto.login(), bCryptPasswordEncoder.encode(dto.senha()));
        return UsuarioResponseDTO.from(usuarioRepository.save(usuario));
    }

}
