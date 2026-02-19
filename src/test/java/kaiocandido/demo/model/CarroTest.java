package kaiocandido.demo.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CarroTest {

    @Test
    @DisplayName("Validando se esta calculando o valor correto do aluguel!")
    void deveCalcularValorAluguel(){
        // 1. Cenário
        Carro carro = new Carro("Sedan", 100.00);
        // 2. Execução
        double total = carro.calcularValorAluguel(3);
        // 3. Verificação
        Assertions.assertEquals(300, total);
    }

    @Test
    @DisplayName("Validando se esta calculando o valor do aluguel com o desconto")
    void deveCalcularValorAluguelComDesconto(){
        // 1. Cenario
        Carro carro = new Carro("Ferrari", 200.00);
        int diasDeAluguel = 10;
        // 2. Execução
        double total = carro.calcularValorAluguel(diasDeAluguel);
        // 3. Verificação
        Assertions.assertEquals(1950, total);
    }
}
