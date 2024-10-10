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

    // Método estático para inicializar passagens com os novos objetos
    public static void inicializarPassagens() {
        // Aeroportos de origem e destino
        Aeroporto aeroportoOrigem1 = new Aeroporto("Aeroporto Internacional de São Paulo", "GRU", "São Paulo", "SP", "Brasil");
        Aeroporto aeroportoDestino1 = new Aeroporto("Aeroporto Internacional de Lisboa", "LIS", "Lisboa", "Lisboa", "Portugal");

        Aeroporto aeroportoOrigem2 = new Aeroporto("Aeroporto Internacional de Miami", "MIA", "Miami", "FL", "EUA");
        Aeroporto aeroportoDestino2 = new Aeroporto("Aeroporto Internacional de Malpensa", "MXP", "Milão", "Lombardia", "Itália");

        // Companhias aéreas
        CompanhiaAerea companhia1 = new CompanhiaAerea("TAP Portugal", "TP", "TAP", "12345678000100", 100.0, 50.0);
        CompanhiaAerea companhia2 = new CompanhiaAerea("American Airlines", "AA", "American Airlines", "12345678000200", 150.0, 60.0);

        // Passagens aéreas
        PassagemAerea passagem1 = new PassagemAerea(
            aeroportoOrigem1, 
            aeroportoDestino1,
            new Date(System.currentTimeMillis() + 86400000), 
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
            new Date(System.currentTimeMillis() + 86400000), 
            "AA2150",   
            companhia2,   
            1800.0,    
            3200.0,    
            4500.0,    
            "USD"
        );

        // Adicionando passagens à lista
        passagens.add(passagem1);
        passagens.add(passagem2);
    }

    // Método para pesquisar passagens
    public static List<PassagemAerea> pesquisarPassagens(Aeroporto origem, Aeroporto destino, Date data) {
        return passagens.stream()
                .filter(passagem -> (origem == null || passagem.getAeroportoOrigem().equals(origem)) &&
                                   (destino == null || passagem.getAeroportoDestino().equals(destino)) &&
                                   (data == null || (passagem.getDataHoraVoo().getTime() >= data.getTime() &&
                                                     passagem.getDataHoraVoo().getTime() < data.getTime() + 86400000))) // compara datas
                .collect(Collectors.toList());
    }
}
