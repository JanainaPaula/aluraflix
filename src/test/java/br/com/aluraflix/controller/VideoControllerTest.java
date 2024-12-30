package br.com.aluraflix.controller;

import br.com.aluraflix.controller.dto.VideoDTO;
import br.com.aluraflix.model.Categoria;
import br.com.aluraflix.model.Video;
import br.com.aluraflix.service.VideoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class VideoControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private VideoService service;

    @Autowired
    private JacksonTester<VideoDTO> jsonDto;

    @Test
    void deveriaDevolverStatus201QuandoCadastrarVideoComSucesso() throws Exception {
        VideoDTO dto = new VideoDTO(1L,2L, "Golang", "Aprenda golang de uma forma divertida", "https://www.google.com");

        given(service.cadastra(dto)).willReturn(new Video(dto.titulo(), dto.descricao(), dto.url(), new Categoria()));

        MockHttpServletResponse response = mvc.perform(
                post("/videos")
                        .content(jsonDto.write(dto).getJson())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertEquals(201, response.getStatus());
    }

    @Test
    void deveriaDevolverStatus400QuandoTentarCadastrarVideoComTituloEmBranco() throws Exception {
        VideoDTO dto = new VideoDTO(1L,2L,"", "Aprenda golang de uma forma divertida", "https://www.google.com");

        MockHttpServletResponse response = mvc.perform(
                post("/videos")
                        .content(jsonDto.write(dto).getJson())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertEquals(400, response.getStatus());
    }

    @Test
    void deveriaDevolverStatus400QuandoTentarCadastrarVideoComDescricaoEmBranco() throws Exception {
        VideoDTO dto = new VideoDTO(1L,2L,"Golang", "", "https://www.google.com");

        MockHttpServletResponse response = mvc.perform(
                post("/videos")
                        .content(jsonDto.write(dto).getJson())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertEquals(400, response.getStatus());
    }

    @Test
    void deveriaDevolverStatus400QuandoTentarCadastrarVideoComUmaUrlInvalida() throws Exception {
        VideoDTO dto = new VideoDTO(1L,2L,"Golang", "Aprenda golang de uma forma divertida", "url-do-video");

        MockHttpServletResponse response = mvc.perform(
                post("/videos")
                        .content(jsonDto.write(dto).getJson())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertEquals(400, response.getStatus());
    }

    @Test
    void deveriaDevolverStatus200QuandoChamarOMetodoExibir() throws Exception {
        given(service.exibir(0, 10)).willReturn(new PageImpl<>(List.of(new Video("Curso Java", "Curso rápido java", "http://www.google.com", new Categoria()))));

        MockHttpServletResponse response = mvc.perform(
                get("/videos").param("page", "0").param("size", "10")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertEquals(200, response.getStatus());
    }

    @Test
    void deveriaDevolverStatus200QuandoEncontrado() throws Exception {
        given(service.buscarPorId(Mockito.any())).willReturn(new Video("Curso Java", "Curso rápido java", "http://www.google.com", new Categoria()));

        MockHttpServletResponse response = mvc.perform(
                get("/videos/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertEquals(200, response.getStatus());
    }

    @Test
    void deveriaDevolverStatus204QuandoDeletado() throws Exception{
        Mockito.doNothing().when(service).deletaPorId(Mockito.any());

        MockHttpServletResponse response = mvc.perform(
                delete("/videos/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertEquals(204, response.getStatus());
    }
}