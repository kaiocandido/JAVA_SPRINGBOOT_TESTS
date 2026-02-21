package kaiocandido.demo.service;

import kaiocandido.demo.entity.CarroEntity;
import kaiocandido.demo.repository.CarroRespository;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import java.util.Optional;


@ExtendWith(MockitoExtension.class)
class CarroServiceTest {

    @InjectMocks
    CarroService carroService;

    @Mock
    CarroRespository carroRespository;

    @Test
    @DisplayName("Testando salvar carro")
    void deveSalvarUmCarro(){
        CarroEntity carro = new CarroEntity("Sedan", 100.00, 2020);
        carro.setId(1L);
        Mockito.when(carroRespository.save(Mockito.any())).thenReturn(carro);
        var carroSalvo = carroService.salvar(carro);

        assertNotNull(carroSalvo);
        assertEquals("Sedan", carroSalvo.getModelo());
        Mockito.verify(carroRespository).save(Mockito.any());
    }

    @Test
    @DisplayName("Validando se gera o erro com numero negativos")
    void deveDarErroAoTentarDiariaNegativa(){
        CarroEntity carro = new CarroEntity("Sedan", 0, 2020);


        var erro = catchThrowable( () ->carroService.salvar(carro));
        assertThat(erro).isInstanceOf(IllegalArgumentException.class);
        Mockito.verify(carroRespository, Mockito.never()).save(Mockito.any());
    }
















}