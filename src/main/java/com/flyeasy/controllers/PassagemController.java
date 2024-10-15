package com.flyeasy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class PassagemController {
    private static List<PassagemAerea> passagens = new ArrayList<>();

    // Método estático para inicializar passagens
    public static void inicializarPassagens() {
        PassagemAerea passagem1 = new PassagemAerea(
            "Aeroporto Internacional de São Paulo", 
            "Aeroporto Internacional de São Paulo",
            new Date(System.currentTimeMillis() + 86400000), 
            "TP1020",   
            "TAP Portugal",   
            2000.0,    
            3500.0,    
            5000.0,    
            "BRL"
        );

        PassagemAerea passagem2 = new PassagemAerea(
            "Aeroporto Internacional de Miami", 
            "Aeroporto Internacional de Malpensa",
            new Date(System.currentTimeMillis() + 86400000), 
            "AA2150",   
            "American Airlines",   
            1800.0,    
            3200.0,    
            4500.0,    
            "USD"
        );

        passagens.add(passagem1);
        passagens.add(passagem2);
    }

    // Método para pesquisar passagens
    public static List<PassagemAerea> pesquisarPassagens(String origem, String destino, Date data) {
        return passagens.stream()
                .filter(passagem -> (origem == null || passagem.getAeroportoOrigem().equalsIgnoreCase(origem)) &&
                                   (destino == null || passagem.getAeroportoDestino().equalsIgnoreCase(destino)) &&
                                   (data == null || (passagem.getDataHoraVoo().getTime() >= data.getTime() &&
                                                     passagem.getDataHoraVoo().getTime() < data.getTime() + 86400000))) // compara datas
                .collect(Collectors.toList());
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

