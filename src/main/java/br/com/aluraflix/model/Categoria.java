package br.com.aluraflix.model;

import br.com.aluraflix.controller.dto.CategoriaDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "categorias")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String titulo;
    private String cor;

    public Categoria() {
    }

    public Categoria(String titulo, String cor) {
        this.id = id;
        this.titulo = titulo;
        this.cor = cor;
    }

    public Long getId() {
        return id;
    }

    public CategoriaDTO toDTO(){
        return new CategoriaDTO(this.id, this.titulo, this.cor);
    }
}
