package com.flyeasy.controllers;

import com.flyeasy.models.Aeronave;

public class AeronaveController {

    public void exibirDisposicaoAssentos(Aeronave aeronave) {
        System.out.println("Disposição de assentos para a aeronave " + aeronave.getCodigoAeronave() + ":");
        for (int i = 1; i <= aeronave.getNumeroFileiras(); i++) {
            for (char assento = 'A'; assento < 'A' + aeronave.getAssentosPorFileira(); assento++) {
                System.out.print(i + "" + assento + " ");
            }
            System.out.println();
        }
    }
}
