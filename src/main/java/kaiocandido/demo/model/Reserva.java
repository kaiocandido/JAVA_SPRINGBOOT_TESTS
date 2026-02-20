package kaiocandido.demo.model;

import kaiocandido.demo.exceptions.ReservaInvalidaException;

public class Reserva {
    private int qntdDias;
    private Carro carro;
    private Client client;

    public Reserva(int qntdDias, Carro carro, Client client) {
         if(qntdDias < 1) {
            throw new ReservaInvalidaException("Reserva invalida! Dias tem que ser maior que 0");
        }

        this.qntdDias = qntdDias;
        this.carro = carro;
        this.client = client;
    }

    public int getQntdDias() {
        return qntdDias;
    }

    public void setQntdDias(int qntdDias) {
        this.qntdDias = qntdDias;
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public double totalDaReserva(int dias){
        double desconto = 50;
            if (dias >= 5){
                return  dias * carro.getValorDiaria() - desconto;
            }else {
                return dias * carro.getValorDiaria();
            }
    }
}
