package kaiocandido.demo.service;

import jakarta.persistence.EntityNotFoundException;
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

import java.util.List;
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

    @Test
    @DisplayName("Validando se atualiza carro")
    void deveAtualizarCarro(){
        Long id = 1L;

        var carroExistente = new CarroEntity("Uno", 20.00, 2025);
        Mockito.when(carroRespository.findById(1L)).thenReturn(Optional.of(carroExistente));

        var carroAtualizado= new CarroEntity("Uno", 20.00, 2025);
        carroAtualizado.setId(1L);

        Mockito.when(carroRespository.save(Mockito.any(CarroEntity.class)))
                .thenReturn(carroAtualizado);

        var carro = new CarroEntity("Sedan", 20.00, 2020);

        var resultado = carroService.atualizar(carro, id);

        assertEquals(resultado.getModelo(), "Uno");
        Mockito.verify(carroRespository, Mockito.times(1)).save(Mockito.any());
    }


    @Test
    @DisplayName("Verificando se gera erro, ao tentar atualizar carro inexistente")
    void deveDarErroAoAtulizarCarroInexistente(){
        Long id = 1L;
        var carro = new CarroEntity("Sedan", 20.00, 2020);

        Mockito.when(carroRespository.findById(Mockito.any())).thenReturn(Optional.empty());

        var erro = catchThrowable(() -> carroService.atualizar(carro, id));
        assertThat(erro).isInstanceOf(EntityNotFoundException.class);

        Mockito.verify(carroRespository, Mockito.never()).save(Mockito.any());
    }

    @Test
    @DisplayName("Verificando se deletamos o carro")
    void deveDeletarOCarro(){
        CarroEntity carro = new CarroEntity("Sedan", 100.00, 2020);
        Long id = 1L;
        carro.setId(id);
        Mockito.when(carroRespository.findById(id)).thenReturn(Optional.of(carro));
        carroService.deleteCarro(id);

        Mockito.verify(carroRespository, Mockito.times(1)).findById(id);
        Mockito.verify(carroRespository, Mockito.times(1)).deleteById(id);
        Mockito.verifyNoMoreInteractions(carroRespository);
    }

    @Test
    @DisplayName("Verificando se gera o erro quando deletamos o carro que não existe")
    void deveDarErroAoDeletarOCarroInexistente(){
        CarroEntity carro = new CarroEntity("Sedan", 100.00, 2020);
        Long id = 1L;
        carro.setId(id);
        Mockito.when(carroRespository.findById(Mockito.any())).thenReturn(Optional.empty());

        var erro = catchThrowable(() -> carroService.deleteCarro(id));
        assertThat(erro).isInstanceOf(EntityNotFoundException.class);
        Mockito.verify(carroRespository, Mockito.never()).deleteById(Mockito.any());
    }

    @Test
    @DisplayName("Validando se lista os carros")
    void deveListarOsCarros(){
        List<CarroEntity> lista = List.of(
                new CarroEntity("Sedan", 100.00, 2020),
                new CarroEntity("Uno", 20.00, 2025)
        );
        Mockito.when(carroRespository.findAll()).thenReturn(lista);
        var resultado = carroService.listar();

        assertEquals(2, resultado.size());
        assertEquals("Sedan", resultado.get(0).getModelo());
        Mockito.verify(carroRespository, Mockito.times(1)).findAll();
        Mockito.verifyNoMoreInteractions(carroRespository);

    }

    @Test
    @DisplayName("Validando se busca por ID")
    void deveBuscarPorId(){
        Long id = 1L;
        var carroExistente = new CarroEntity("Sedan", 100.00, 2020);
        carroExistente.setId(id);
        Mockito.when(carroRespository.findById(id)).thenReturn(Optional.of(carroExistente));
        var resultado = carroService.buscarPorId(id);

        assertEquals("Sedan", resultado.getModelo());
        assertEquals(id, resultado.getId());
        Mockito.verify(carroRespository, Mockito.times(1)).findById(id);
        Mockito.verifyNoMoreInteractions(carroRespository);

    }

    @Test
    @DisplayName("Validando se gera erro ao busca por ID inexistente")
    void deveDarErroAoBuscarPorIdInexistente(){
        var carro = new CarroEntity("Sedan", 100.00, 2020);
        Long id = 1L;
        carro.setId(id);
        Mockito.when(carroRespository.findById(Mockito.any())).thenReturn(Optional.empty());

        var erro = catchThrowable(() -> carroService.buscarPorId(id));
        assertThat(erro).isInstanceOf(EntityNotFoundException.class);
        Mockito.verify(carroRespository, Mockito.never()).deleteById(Mockito.any());

    }

}