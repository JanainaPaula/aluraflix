package br.com.aluraflix.model;

import br.com.aluraflix.controller.dto.UpdateVideoDTO;
import br.com.aluraflix.controller.dto.VideoDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.apache.logging.log4j.util.Strings;

@Entity
@Table(name = "videos")
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String titulo;

    private String descricao;

    private String url;

    public Video() {
    }

    public Video(String titulo, String descricao, String url) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public VideoDTO toDTO(){
        return new VideoDTO(this.id, this.titulo, this.descricao, this.url);
    }

    public Video update(UpdateVideoDTO dto){
        if (!Strings.isBlank(dto.titulo())){
            this.titulo = dto.titulo();
        }
        if (!Strings.isBlank(dto.descricao())){
            this.descricao = dto.descricao();
        }
        if (!Strings.isBlank(dto.url())){
            this.url = dto.url();
        }
        return this;
    }
}
