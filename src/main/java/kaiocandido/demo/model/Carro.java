package kaiocandido.demo.model;

public class Carro {
    private String modelo;
    private Double valorDiaria;

    public Carro(String modelo, Double valorDiaria) {
        this.modelo = modelo;
        this.valorDiaria = valorDiaria;
    }

    public double calcularValorAluguel(int dias){
        return dias * valorDiaria;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Double getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(Double valorDiaria) {
        valorDiaria = valorDiaria;
    }
}
