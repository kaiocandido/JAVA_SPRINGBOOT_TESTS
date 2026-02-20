package kaiocandido.demo.repository;

import kaiocandido.demo.entity.CarroEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

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
}