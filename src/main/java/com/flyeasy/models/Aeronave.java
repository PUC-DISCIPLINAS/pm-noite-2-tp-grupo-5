package com.flyeasy.models;

public class Aeronave {
    private String codigoAeronave;
    private int capacidadePassageiros;
    private double capacidadeCarga; // Capacidade de carga em kg
    private int numeroFileiras;
    private int assentosPorFileira = 6; // Assumindo 6 assentos por fileira conforme especificação

        public Aeronave(String codigoAeronave, int capacidadePassageiros, double capacidadeCarga, int numeroFileiras) {
        this.codigoAeronave = codigoAeronave;
        this.capacidadePassageiros = capacidadePassageiros;
        this.capacidadeCarga = capacidadeCarga;
        this.numeroFileiras = numeroFileiras;
    }

    public String getCodigoAeronave() {
        return codigoAeronave;
    }

    public int getCapacidadePassageiros() {
        return capacidadePassageiros;
    }

    public double getCapacidadeCarga() {
        return capacidadeCarga;
    }

    public int getNumeroFileiras() {
        return numeroFileiras;
    }

    public int getAssentosPorFileira() {
        return assentosPorFileira;
    }
}
