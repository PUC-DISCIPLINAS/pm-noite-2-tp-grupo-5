package com.flyeasy.models;

import java.time.LocalDateTime;
import java.util.List;

public class Voo {
    private String codigo;
    private String origem;
    private String destino;
    private List<DiaSemana> diasSemana;
    private Aeronave aeronave;

    public Voo(String codigo, String origem, String destino, List<DiaSemana> diasSemana, Aeronave aeronave) {
        this.codigo = codigo;
        this.origem = origem;
        this.destino = destino;
        this.diasSemana = diasSemana;
        this.aeronave = aeronave; 
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public List<DiaSemana> getDiasSemana() {
        return diasSemana;
    }

    public void setDiasSemana(List<DiaSemana> diasSemana) {
        this.diasSemana = diasSemana;
    }

    public Aeronave getAeronave() {
        return aeronave;
    }

    public void setAeronave(Aeronave aeronave) {
        this.aeronave = aeronave;
    }

    public int getCapacidadePassageiros() {
        return aeronave.getCapacidadePassageiros();
    }
}
