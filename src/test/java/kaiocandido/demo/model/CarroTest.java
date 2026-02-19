package kaiocandido.demo.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CarroTest {

    @Test
    @DisplayName("Validando se esta calculando o valor correto do aluguel!")
    void deveCalcularValorAluguel(){
        Carro carro = new Carro("Sedan", 100.00);
        double total = carro.calcularValorAluguel(3);
        Assertions.assertEquals(300, total);
    }
}
