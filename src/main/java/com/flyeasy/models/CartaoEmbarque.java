package com.flyeasy.models;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CartaoEmbarque {
    private String nomePassageiro;
    private String sobrenomePassageiro;
    private String origem;
    private String destino;
    private Date horarioVoo;
    private Date horarioEmbarque;
    private Date dataEmbarque;
    private String assento;

    public CartaoEmbarque(String nomePassageiro, String sobrenomePassageiro, String origem,
                          String destino, Date horarioVoo, Date horarioEmbarque, Date dataEmbarque, String assento) {
        this.nomePassageiro = nomePassageiro;
        this.sobrenomePassageiro = sobrenomePassageiro;
        this.origem = origem;
        this.destino = destino;
        this.horarioVoo = horarioVoo;
        this.dataEmbarque = dataEmbarque;
        this.assento = assento;
        this.horarioEmbarque = calcularHorarioEmbarque(horarioVoo);
    }

    private Date calcularHorarioEmbarque(Date horarioVoo) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(horarioVoo);
        calendar.add(Calendar.MINUTE, -40); // Subtrai 40 minutos
        return calendar.getTime();
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

    public Date getHorarioVoo() {
        return horarioVoo;
    }

    public Date getHorarioEmbarque() {
        return horarioEmbarque;
    }

    public String getAssento() {
        return assento;
    }

    public Date getDataEmbarque() {
        return dataEmbarque;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return "Cartão de Embarque:\n" +
               "Passageiro: " + nomePassageiro + " " + sobrenomePassageiro + "\n" +
               "Origem: " + origem + " - Destino: " + destino + "\n" +
               "Horário do Voo: " + dateFormat.format(horarioVoo) + "\n" +
               "Horário de Embarque: " + dateFormat.format(horarioEmbarque) + "\n" +
               "Assento: " + assento;
    }

}
