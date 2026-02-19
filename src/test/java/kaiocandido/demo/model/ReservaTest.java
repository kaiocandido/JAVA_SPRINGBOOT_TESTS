package kaiocandido.demo.model;

import kaiocandido.demo.Exceptions.ReservaInvalidaException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ReservaTest {

    Carro carro;
    Client client;

    @BeforeEach
    void setUp(){
         client = new Client("kaio");
         carro = new Carro("Gol G5", 100.00);
    }

    @Test
    @DisplayName("Validando se conseguimos usar o total")
    void calculandoTotalDaReserva(){
        int qntdDias = 3;
        Reserva reserva = new Reserva(qntdDias, carro, client);
        double total = reserva.totalDaReserva(reserva.getQntdDias());

        var nome = client.getName();

        assertThat(nome).isEqualTo("kaio");
        Assertions.assertEquals(300, total);
    }

    @Test
    @DisplayName("Criando classe")
    void criandoReserva(){
        // 1. cenario
        int qntdDias = 3;
        // 2. execução
        Reserva reserva = new Reserva(qntdDias, carro, client);
        // 3. verificação
        assertThat(reserva).isNotNull();
    }

    @Test
    @DisplayName("Numero Negativo gerando erro")
    void deveDarErroNumeroNegativos(){
        // 1. cenario
        int qntdDias = 0;
        // 2. execução
        Reserva reserva = new Reserva(qntdDias, carro, client);
        // 3. verificação
        var dias = reserva.getQntdDias();


        //JUnit
        assertThrows(ReservaInvalidaException.class, () -> new Reserva(0, carro, client));
        assertDoesNotThrow(() -> new Reserva(1, carro, client) );

        //AssertJ
        //var erro = Assertions.catchThrowable(() -> new Reserva(0, carro, client));
        //Assertions.assertThat(erro).isInstanceOf(ReservaInvalidaException.class).hasMessage("Reserva invalida! Dias tem que ser maior que 0");
    }


}
