package kaiocandido.demo.model;

public class Carro {
    private String modelo;
    private final Double valorDiaria;

    public Carro(String modelo, Double valorDiaria) {
        this.modelo = modelo;
        this.valorDiaria = valorDiaria;
    }

    public double calcularValorAluguel(int dias){
        double desconto = 50;
        if (dias >= 10){
            return  dias * valorDiaria - desconto;
        }else {
            return dias * valorDiaria;
        }
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
