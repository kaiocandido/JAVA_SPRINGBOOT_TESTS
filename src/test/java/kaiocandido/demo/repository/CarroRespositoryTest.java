package kaiocandido.demo.repository;

import kaiocandido.demo.entity.CarroEntity;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("teste")
class CarroRespositoryTest {

    @Autowired
    CarroRespository carroRespository;

    @Test
    @DisplayName("Verificar se ele salva um carro no banco")
    void deveSalvarUmCarro(){
        var entity = new CarroEntity("Uno", 200.00);
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
}

