package kaiocandido.demo.model;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ClientTest {

    @Test
    @DisplayName("Validando se o cliente esta sendo criado com nome")
    void deveCriarClienteComNome(){
        // 1. Cenario
        var client = new Client("maria");
        // 2. Execução
        String name = client.getName();
        // 3. Verificação
        assertThat(name).isEqualTo("maria");
        assertThat(name).isLessThan("maria5");
        assertThat(name.length()).isLessThan(100);
        assertNotNull(name);
        assertTrue(name.startsWith("m"));
        assertNotEquals(100, name.length());

    }

    @Test
    @DisplayName("Validando se o cliente esta sendo criado sem nome")
    void deveCriarClientSemNome(){
        // 1. Cenario
        var client = new Client(null);
        // 2. Execução
        String name = client.getName();
        // 3. Verificação
        assertNull(name);
    }
}
