package br.com.aluraflix.controller;

import br.com.aluraflix.controller.dto.VideoDTO;
import br.com.aluraflix.model.Video;
import br.com.aluraflix.service.IVideoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/videos")
public class VideoController {

    private final IVideoService service;

    public VideoController(IVideoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<VideoDTO>> exibir(){
        List<Video> videos = service.exibir();
        List<VideoDTO> dtos = videos.stream().map(video -> video.toDTO()).toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VideoDTO> buscarPorId(@PathVariable Long id){
        Video video = service.buscarPorId(id);
        return ResponseEntity.ok(video.toDTO());
    }

    @PostMapping
    public ResponseEntity<VideoDTO> cadastrar(@RequestBody @Valid VideoDTO dto){
        Video videoCadastrado = service.cadastra(dto);
        return ResponseEntity.created(URI.create(String.format("/videos/%s", videoCadastrado.getId())))
                .body(videoCadastrado.toDTO());
    }
}