package com.flyeasy.controllers;

import com.flyeasy.models.Aeronave;

import java.util.ArrayList;
import java.util.List;


public class AeronaveController {

    private List<Aeronave> aeronaves;

    public AeronaveController() {
        this.aeronaves = new ArrayList<>();
    }

    public void cadastrarAeronave(String codigo, int capacidadePassageiros, double capacidadeCarga, int numeroFileiras) {
        Aeronave novaAeronave = new Aeronave(codigo, capacidadePassageiros, capacidadeCarga, numeroFileiras, 100.00);
        aeronaves.add(novaAeronave);
    }

    public List<Aeronave> listarAeronaves() {
        return aeronaves;
    }

    public Aeronave buscarAeronavePorCodigo(String codigo) {
        for (Aeronave aeronave : aeronaves) {
            if (aeronave.getCodigoAeronave().equals(codigo)) {
                return aeronave;
            }
        }
        return null;
    }

    public void exibirDisposicaoAssentos(String codigoAeronave) {
        Aeronave aeronave = buscarAeronavePorCodigo(codigoAeronave);
        if (aeronave != null) {
            System.out.println("Disposição de assentos para a aeronave " + aeronave.getCodigoAeronave() + ":");
            for (int i = 1; i <= aeronave.getNumeroFileiras(); i++) {
                for (char assento = 'A'; assento < 'A' + aeronave.getAssentosPorFileira(); assento++) {
                    System.out.print(i + "" + assento + " ");
                }
                System.out.println();
            }
        } else {
            System.out.println("Aeronave não encontrada.");
        }
    }
}
