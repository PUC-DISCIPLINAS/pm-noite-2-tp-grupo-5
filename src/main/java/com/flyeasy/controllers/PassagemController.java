package com.flyeasy.controllers;

import com.flyeasy.models.Aeroporto;
import com.flyeasy.models.CompanhiaAerea;
import com.flyeasy.models.PassagemAerea;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class PassagemController {
    private static List<PassagemAerea> passagens = new ArrayList<>();

    public static void inicializarPassagens() {
        Aeroporto aeroportoOrigem1 = new Aeroporto("Aeroporto Internacional de São Paulo", "GRU", "São Paulo", "SP", "Brasil");
        Aeroporto aeroportoDestino1 = new Aeroporto("Aeroporto Internacional de Lisboa", "LIS", "Lisboa", "Lisboa", "Portugal");

        Aeroporto aeroportoOrigem2 = new Aeroporto("Aeroporto Internacional de Miami", "MIA", "Miami", "FL", "EUA");
        Aeroporto aeroportoDestino2 = new Aeroporto("Aeroporto Internacional de Malpensa", "MXP", "Milão", "Lombardia", "Itália");

        CompanhiaAerea companhia1 = new CompanhiaAerea("TAP Portugal", "TP", "TAP", "12345678000100", 100.0, 50.0);
        CompanhiaAerea companhia2 = new CompanhiaAerea("American Airlines", "AA", "American Airlines", "12345678000200", 150.0, 60.0);

        PassagemAerea passagem1 = new PassagemAerea(
                aeroportoOrigem1,
                aeroportoDestino1,
                new Date(System.currentTimeMillis() + 86400000), // 1 dia de antecedência
                "TP1020",
                companhia1,
                2000.0,
                3500.0,
                5000.0,
                "BRL"
        );

        PassagemAerea passagem2 = new PassagemAerea(
                aeroportoOrigem2,
                aeroportoDestino2,
                new Date(System.currentTimeMillis() + 86400000), // 1 dia de antecedência
                "AA2150",
                companhia2,
                1800.0,
                3200.0,
                4500.0,
                "USD"
        );

        passagens.add(passagem1);
        passagens.add(passagem2);
    }

    // Método para verificar e realizar o check-in
    public static String realizarCheckIn(String codigoVoo) {
        for (PassagemAerea passagem : passagens) {
            if (passagem.getCodigoVoo().equals(codigoVoo)) {
                if (passagem.realizarCheckIn()) {
                    return "Check-in realizado com sucesso para o voo " + codigoVoo;
                } else {
                    return "Não é possível realizar o check-in para o voo " + codigoVoo + ". Fora do período permitido.";
                }
            }
        }
        return "Voo não encontrado.";
    }

    // Método para verificar No Show
    public static void verificarNoShow() {
        for (PassagemAerea passagem : passagens) {
            passagem.registrarNoShow();
        }
    }

    // Método para reservar um assento
    public static boolean reservarAssento(String codigoVoo, String assento) {
        for (PassagemAerea passagem : passagens) {
            if (passagem.getCodigoVoo().equals(codigoVoo)) {
                return passagem.reservarAssento(assento);
            }
        }
        return false; // Voo não encontrado
    }
}
