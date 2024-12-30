package br.com.aluraflix.repository;

import br.com.aluraflix.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VideoRepository extends JpaRepository<Video, Long> {
    Optional<List<Video>> findByTituloContainingIgnoreCase(String titulo);
}
