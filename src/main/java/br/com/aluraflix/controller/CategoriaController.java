package br.com.aluraflix.controller;

import br.com.aluraflix.controller.dto.CategoriaDTO;
import br.com.aluraflix.controller.dto.UpdateCategoriaDTO;
import br.com.aluraflix.controller.dto.VideoDTO;
import br.com.aluraflix.model.Categoria;
import br.com.aluraflix.model.Video;
import br.com.aluraflix.service.ICategoriaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.net.URI;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
    private final ICategoriaService categoriaService;

    public CategoriaController(ICategoriaService categoriaService){
        this.categoriaService = categoriaService;
    }

    @PostMapping
    public ResponseEntity<CategoriaDTO> cadastraCategoria(@RequestBody @Valid CategoriaDTO dto){
        Categoria categoriaCadastrada = categoriaService.cadastraCategoria(dto);
        return ResponseEntity.created(URI.create(String.format("/categoria/%s", categoriaCadastrada.getId())))
                .body(categoriaCadastrada.toDTO());
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> exibirCategoria(){
        List<Categoria> categorias = categoriaService.exibirCategoria();
        List<CategoriaDTO> dtos = categorias.stream().map(categoria -> categoria.toDTO()).toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> buscaCategoriaPorId(@PathVariable Long id){
        Categoria categoria = categoriaService.buscaCategoriaPorId(id);
        return ResponseEntity.ok(categoria.toDTO());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletaCategoriaPorId(@PathVariable Long id){
        categoriaService.deletaCategoriaPorId(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> atualizaCategoria(@PathVariable Long id, @RequestBody UpdateCategoriaDTO dto){
        Categoria categoriaAtualizada = categoriaService.atualizaCategoria(id, dto);
        return ResponseEntity.ok(categoriaAtualizada.toDTO());
    }

    @GetMapping("/{id}/videos")
    public ResponseEntity<List<VideoDTO>> getVideosPorCategoriaId(@PathVariable Long id) {
        List<VideoDTO> videos = categoriaService.getVideosPorId(id).stream().map(Video::toDTO).toList();
        return ResponseEntity.ok(videos);
    }
}
