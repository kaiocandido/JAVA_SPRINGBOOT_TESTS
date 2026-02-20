package kaiocandido.demo.service;

import kaiocandido.demo.entity.CarroEntity;
import kaiocandido.demo.repository.CarroRespository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CarroServiceTest {

    @InjectMocks
    CarroService carroService;

    @Mock
    CarroRespository carroRespository;

    @Test
    @DisplayName("Testando salvar carro")
    void deveSalvarUmCarro(){
        Mockito.when(carroRespository.findById(1L)).thenReturn(Optional.of(new CarroEntity("Teste", 10.0, 2020)));

        Optional<CarroEntity> carroEncontrado = carroRespository.findById(1L);
        System.out.println(carroEncontrado.get().getModelo());
    }
}