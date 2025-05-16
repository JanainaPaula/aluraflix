package br.com.aluraflix.controller;

import br.com.aluraflix.controller.dto.UsuarioDTO;
import br.com.aluraflix.controller.dto.UsuarioResponseDTO;
import br.com.aluraflix.service.IUsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final IUsuarioService service;

    public UsuarioController(IUsuarioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> cadastraUsuario(@RequestBody @Valid UsuarioDTO usuarioDTO){
        var usuario = service.cadastraUsuario(usuarioDTO);
        return ResponseEntity.created(URI.create(String.format("/usuario/%s", usuario.id()))).body(usuario);
    }
}
