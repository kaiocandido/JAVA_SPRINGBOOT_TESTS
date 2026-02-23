package kaiocandido.demo.controller;

import jakarta.persistence.EntityNotFoundException;
import kaiocandido.demo.entity.CarroEntity;
import kaiocandido.demo.service.CarroService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CarroController.class)
class CarroControllerTest {

    @Autowired
    MockMvc mvc;

    @MockitoBean
    CarroService carroService;

    @Test
    @DisplayName("Validando se esta salvando o carro")
    void deveSalvarOCarro() throws  Exception{
        //Cenario
        CarroEntity carro = new CarroEntity("Uno", 200.00, 2020);
        carro.setId(1l);
        when(carroService.salvar(Mockito.any())).thenReturn(carro);

        String json = """
                {
                    "modelo": "Uno",
                    "valorDiaria": 200.00,
                    "carroAno": 2020
                }
                """;
    //Execução
        ResultActions resultActions = mvc.perform(
                post("/carros")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        );

    //Validação
        resultActions.andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.modelo").value("Uno"));

    }

    @Test
    @DisplayName("Validando se obtemos detalhes do carro")
    void deveObterDetalhes() throws Exception {
        // cenario
        var carro = new CarroEntity("uno", 100.00, 2020);
        carro.setId(1L);
        when(carroService.buscarPorId(Mockito.anyLong())).thenReturn(carro);

        // execução
        mvc.perform(
                MockMvcRequestBuilders.get("/carros/1")
        ).andExpect(status().isOk()).andExpect(jsonPath("$.modelo").value("uno"))
                .andExpect(jsonPath("$.valorDiaria").value(100.00))
                .andExpect(jsonPath("$.carroAno").value(2020));
    }

    @Test
    @DisplayName("Validando se obtemos erro ao consultar detalhes do carro")
    void deveRetornarNotFoundAoObterDetalhes() throws Exception {
        // cenario
        when(carroService.buscarPorId(Mockito.anyLong())).thenThrow(EntityNotFoundException.class);

        // execução
        mvc.perform(
                        MockMvcRequestBuilders.get("/carros/1")
                ).andExpect(status().isNotFound());
    }


















}