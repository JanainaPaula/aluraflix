package br.com.aluraflix.model;

import br.com.aluraflix.controller.dto.CategoriaDTO;
import br.com.aluraflix.controller.dto.UpdateCategoriaDTO;
import jakarta.persistence.*;
import org.apache.logging.log4j.util.Strings;

import java.util.List;

@Entity(name = "categorias")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String titulo;
    private String cor;

    @OneToMany(mappedBy = "categoria")
    private List<Video> videos;

    public Categoria() {
    }

    public Categoria(String titulo, String cor) {
        this.id = id;
        this.titulo = titulo;
        this.cor = cor;
    }

    public Categoria(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public CategoriaDTO toDTO(){
        return new CategoriaDTO(this.id, this.titulo, this.cor);
    }

    public Categoria update(UpdateCategoriaDTO dto){
        if (!Strings.isBlank(dto.titulo())){
            this.titulo = dto.titulo();
        }
        if (!Strings.isBlank(dto.cor())){
            this.cor = dto.cor();
        }
        return this;
    }
}
