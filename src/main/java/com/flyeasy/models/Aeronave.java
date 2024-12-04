package com.flyeasy.models;

public class Aeronave {
    private String codigoAeronave;
    private int capacidadePassageiros;
    private double capacidadeCarga;
    private int numeroFileiras;
    private int assentosPorFileira = 6;
    private double velocidadeMedia;

    public Aeronave(String codigoAeronave, int capacidadePassageiros, double capacidadeCarga, int numeroFileiras, double velocidadeMedia) {
        this.codigoAeronave = codigoAeronave;
        this.capacidadePassageiros = capacidadePassageiros;
        this.capacidadeCarga = capacidadeCarga;
        this.numeroFileiras = numeroFileiras;
        this.velocidadeMedia = velocidadeMedia;
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

    public double getVelocidadeMedia() {
        return velocidadeMedia;
    }

    public double calcularTempoViagem(double distanciaKm) {
        return distanciaKm / this.velocidadeMedia;
    }

}
