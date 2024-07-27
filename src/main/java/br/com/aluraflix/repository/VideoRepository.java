package br.com.aluraflix.repository;

import br.com.aluraflix.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Long> {
}
