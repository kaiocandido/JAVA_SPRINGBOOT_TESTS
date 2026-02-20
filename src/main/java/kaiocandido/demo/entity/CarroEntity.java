package kaiocandido.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "carro")
public class CarroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String modelo;
    private double valorDiaria;
    private int carroAno;

    @Deprecated
    public CarroEntity(){}

    public CarroEntity(String modelo, double valorDiaria, int carroAno) {
        this.modelo = modelo;
        this.valorDiaria = valorDiaria;
        this.carroAno = carroAno;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(double valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    public int getCarroAno() {
        return carroAno;
    }

    public void setCarroAno(int carroAno) {
        this.carroAno = carroAno;
    }

}
