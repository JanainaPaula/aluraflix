package br.com.aluraflix.controller;

import br.com.aluraflix.controller.dto.VideoDTO;
import br.com.aluraflix.model.Video;
import br.com.aluraflix.service.IVideoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/videos")
public class VideoController {

    private final IVideoService service;

    public VideoController(IVideoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<VideoDTO> cadastrar(@RequestBody @Valid VideoDTO dto){
        Video videoCadastrado = service.cadastra(dto);
        return ResponseEntity.created(URI.create(String.format("/videos/%s", videoCadastrado.getId())))
                .body(videoCadastrado.toDTO());
    }
}
