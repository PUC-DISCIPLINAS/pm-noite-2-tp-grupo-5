package com.flyeasy.models;

import java.time.LocalDateTime;

public class LogVoo {
    private LocalDateTime dataInstancia;
    private String aeronave;
    private String responsavel;
    private LocalDateTime dataVoo;

    public LogVoo(LocalDateTime dataInstancia, String aeronave, String responsavel, LocalDateTime dataVoo) {
        this.dataInstancia = dataInstancia;
        this.aeronave = aeronave;
        this.responsavel = responsavel;
        this.dataVoo = dataVoo;
    }

    @Override
    public String toString() {
        return "LogVoo{" +
                "dataInstancia=" + dataInstancia +
                ", aeronave='" + aeronave + '\'' +
                ", responsavel='" + responsavel + '\'' +
                ", dataVoo=" + dataVoo +
                '}';
    }
}

