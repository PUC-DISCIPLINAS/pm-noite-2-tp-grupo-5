package com.flyeasy.models;

import java.time.LocalDate;
import java.time.LocalTime;

public class CartaoEmbarque {
    private String nomePassageiro;
    private String sobrenomePassageiro;
    private String origem;
    private String destino;
    private LocalTime horarioVoo;
    private LocalTime horarioEmbarque;
    private LocalDate dataEmbarque;
    private String assento;

    public CartaoEmbarque(String nomePassageiro, String sobrenomePassageiro, String origem,
                          String destino, LocalTime horarioVoo, LocalTime horarioEmbarque, LocalDate dataEmbarque, String assento) {
        this.nomePassageiro = nomePassageiro;
        this.sobrenomePassageiro = sobrenomePassageiro;
        this.origem = origem;
        this.destino = destino;
        this.horarioVoo = horarioVoo;
        this.dataEmbarque = dataEmbarque;
        this.assento = assento;
        this.horarioEmbarque = calcularHorarioEmbarque(horarioVoo);
    }

    // Método para calcular o horário de embarque, subtraindo 40 minutos do horário do voo
    private LocalTime calcularHorarioEmbarque(LocalTime horarioVoo) {
        return horarioVoo.minusMinutes(40); // Subtrai 40 minutos para calcular o horário de embarque
    }

    // Métodos getters para os dados do cartão de embarque
    public String getNomePassageiro() {
        return nomePassageiro;
    }

    public String getSobrenomePassageiro() {
        return sobrenomePassageiro;
    }

    public String getOrigem() {
        return origem;
    }

    public String getDestino() {
        return destino;
    }

    public LocalTime getHorarioVoo() {
        return horarioVoo;
    }

    public LocalTime getHorarioEmbarque() {
        return horarioEmbarque;
    }

    public LocalDate getDataEmbarque() {
        return dataEmbarque;
    }

    public String getAssento() {
        return assento;
    }

    // Representação em string do Cartão de Embarque
    @Override
    public String toString() {
        return "Cartão de Embarque: " + nomePassageiro + " " + sobrenomePassageiro + "\n" +
               "Voo: " + origem + " -> " + destino + "\n" +
               "Horário do Voo: " + horarioVoo + "\n" +
               "Horário de Embarque: " + horarioEmbarque + "\n" +
               "Data de Embarque: " + dataEmbarque + "\n" +
               "Assento: " + assento;
    }
}
