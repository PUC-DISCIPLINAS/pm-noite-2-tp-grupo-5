package com.flyeasy.controllers;

public class AeronaveController {
    public Aeronave(String codigoAeronave, int capacidadePassageiros, double capacidadeCarga, int numeroFileiras) {
        this.codigoAeronave = codigoAeronave;
        this.capacidadePassageiros = capacidadePassageiros;
        this.capacidadeCarga = capacidadeCarga;
        this.numeroFileiras = numeroFileiras;
    }

    public void exibirDisposicaoAssentos() {
        System.out.println("Disposição de assentos para a aeronave " + codigoAeronave + ":");
        for (int i = 1; i <= numeroFileiras; i++) {
            for (char assento = 'A'; assento < 'A' + assentosPorFileira; assento++) {
                System.out.print(i + "" + assento + " ");
            }
            System.out.println();
        }
    }
}
