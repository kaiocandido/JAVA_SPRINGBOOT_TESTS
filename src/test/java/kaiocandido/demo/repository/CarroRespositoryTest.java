package kaiocandido.demo.repository;

import kaiocandido.demo.entity.CarroEntity;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("teste")
class CarroRespositoryTest {

    @Autowired
    CarroRespository carroRespository;
    CarroEntity carro;
    @BeforeEach
    void setup(){
        carro = new CarroEntity("civic", 200, 2020);
    }

    @Test
    @DisplayName("Verificar se ele salva um carro no banco")
    void deveSalvarUmCarro(){
        var entity = new CarroEntity("Uno", 200.00, 2001);
        carroRespository.save(entity);

        //verificando
        assertNotNull(entity.getId());
    }

    @Test
    @Sql("/sql/popular-carros.sql")
    void deveBuscarCarroPorModelo() {
        List<CarroEntity> lista = carroRespository.findByModelo("SUV");

        var carro = lista.stream().findFirst().get();

        assertEquals(1, lista.size());

        assertThat(carro.getValorDiaria()).isEqualTo(150);
        assertThat(carro.getModelo()).isEqualTo("SUV");

    }

    @Test
    @DisplayName("Validando se conseguimos buscar por ID")
    void deveBuscarCarroPorId(){
        var carroCriado = carroRespository.save(carro);
        Optional<CarroEntity> carroEncontrado = carroRespository.findById(carroCriado.getId());

        assertThat(carroEncontrado).isPresent();
        assertThat(carroEncontrado.get().getModelo()).isEqualTo("civic");
    }

    @Test
    @DisplayName("Validando se conseguimos atualizar")
    void deveAtualizarCarro(){
        var carroCriado = carroRespository.save(carro);
        carroCriado.setCarroAno(2028);
        var carroAtualizado = carroRespository.save(carroCriado);

        assertThat(carroAtualizado.getCarroAno()).isEqualTo(2028);
    }

    @Test
    @DisplayName("Validando se carro é deletado")
    void deveDeletarCarro(){
        var carroCriado = carroRespository.save(carro);
        carroRespository.deleteById(carroCriado.getId());

        var carroEncontrado = carroRespository.findById(carroCriado.getId());
        assertThat(carroEncontrado).isEmpty();

    }
}

