package kaiocandido.demo.model;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBaseTest {
    static Connection connection;

    @BeforeAll //executa sempre no começo
    static void setupDataBase() throws  Exception{
        connection = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
        connection.createStatement().execute("CREATE TABLE users (id INT, name VARCHAR)");
    }

    @BeforeEach // executa sempre no começo de cada metodo
    @DisplayName("Testando insert no banco H2")
    void inserUserTest() throws Exception{
        connection.createStatement().execute("insert into users(id, name) values (1, 'jose')");
    }

    @Test
    @DisplayName("Validando se usuario existe")
    // @Disabled teste não vai ser executado
    void testUserExists() throws  Exception{
        var result = connection.createStatement().executeQuery("select * from users where id = 1 ");

        Assertions.assertTrue(result.next());
    }

    @AfterAll // executa no final
    //@AfterEach executada todas as vezes depois de cada metodo
    static void closeDataBase() throws  Exception{
        connection.close();
    }
}
