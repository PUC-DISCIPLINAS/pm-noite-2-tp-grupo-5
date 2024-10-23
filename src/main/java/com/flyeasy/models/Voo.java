package com.flyeasy.models;

import java.time.LocalDateTime;
import java.util.List;

public class Voo {
    private String codigo;
    private String origem;
    private String destino;
    private List<DiaSemana> diasSemana;
    private LocalDateTime dataVoo;  // Novo campo para armazenar a data e hora do voo

    // Construtor atualizado para incluir a data do voo
    public Voo(String codigo, String origem, String destino, List<DiaSemana> diasSemana, LocalDateTime dataVoo) {
        this.codigo = codigo;
        this.origem = origem;
        this.destino = destino;
        this.diasSemana = diasSemana;
        this.dataVoo = dataVoo;
    }

    // Getter para o código
    public String getCodigo() {
        return codigo;
    }

    // Setter para o código
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    // Getter para a origem
    public String getOrigem() {
        return origem;
    }

    // Setter para a origem
    public void setOrigem(String origem) {
        this.origem = origem;
    }

    // Getter para o destino
    public String getDestino() {
        return destino;
    }

    // Setter para o destino
    public void setDestino(String destino) {
        this.destino = destino;
    }

    // Getter para os dias da semana
    public List<DiaSemana> getDiasSemana() {
        return diasSemana;
    }

    // Setter para os dias da semana
    public void setDiasSemana(List<DiaSemana> diasSemana) {
        this.diasSemana = diasSemana;
    }

    // Getter para a data do voo
    public LocalDateTime getDataVoo() {
        return dataVoo;
    }

    // Setter para a data do voo, caso precise atualizar
    public void setDataVoo(LocalDateTime dataVoo) {
        this.dataVoo = dataVoo;
    }
}
