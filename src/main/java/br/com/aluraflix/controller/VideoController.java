package br.com.aluraflix.controller;

import br.com.aluraflix.controller.dto.UpdateVideoDTO;
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
    public ResponseEntity<List<VideoDTO>> handleBusca(@RequestParam(required = false) String search){
        if (search != null){
            return buscaVideos(search);
        }
        return exibir();
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletaPorId(@PathVariable Long id){
        service.deletaPorId(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<VideoDTO> atualiza(@RequestBody UpdateVideoDTO dto, @PathVariable Long id){
        return ResponseEntity.ok(service.atualizaPorId(dto, id).toDTO());
    }

    private ResponseEntity<List<VideoDTO>> exibir(){
        List<Video> videos = service.exibir();
        List<VideoDTO> dtos = videos.stream().map(Video::toDTO).toList();
        return ResponseEntity.ok(dtos);
    }

    private ResponseEntity<List<VideoDTO>> buscaVideos(String search){
        return ResponseEntity.ok(service.buscaVideos(search).stream().map(Video::toDTO).toList());
    }
}