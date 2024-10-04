package br.com.aluraflix.controller;

import br.com.aluraflix.controller.dto.CategoriaDTO;
import br.com.aluraflix.model.Categoria;
import br.com.aluraflix.service.CategoriaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class CategoriaControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CategoriaService categoriaService;

    @Autowired
    private JacksonTester<CategoriaDTO> jsonDto;

    @Test
    void deveriaDevolverStatus201QuandoCadastrarCategoriaComSucesso() throws Exception {
        CategoriaDTO dto = new CategoriaDTO(2L,"Curso", "Azul");

        given(categoriaService.cadastraCategoria(dto)).willReturn(new Categoria(dto.titulo(), dto.cor()));

        MockHttpServletResponse response = mvc.perform(
                post("/categoria")
                        .content(jsonDto.write(dto).getJson())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertEquals(201, response.getStatus());
    }

    @Test
    void deveriaDevolverStatus400QuandoTentarCadastrarCategoriaComTituloEmBranco() throws Exception {
        CategoriaDTO dto = new CategoriaDTO(2L,"", "Azul");

        MockHttpServletResponse response = mvc.perform(
                post("/categoria")
                        .content(jsonDto.write(dto).getJson())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertEquals(400, response.getStatus());
    }

    @Test
    void deveriaDevolverStatus400QuandoTentarCadastrarCategoriaComCorEmBranco() throws Exception {
        CategoriaDTO dto = new CategoriaDTO(2L,"Curso", "");

        MockHttpServletResponse response = mvc.perform(
                post("/categoria")
                        .content(jsonDto.write(dto).getJson())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertEquals(400, response.getStatus());
    }
}
