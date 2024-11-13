package com.flyeasy.controllers;

import com.flyeasy.models.Aeroporto;
import com.flyeasy.models.CompanhiaAerea;
import com.flyeasy.models.Passageiro;
import com.flyeasy.models.PassagemAerea;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class PassagemController {
    private static List<PassagemAerea> passagens = new ArrayList<>();
    private static final double precoBagagem = 50.0; // Valor fixo para bagagem adicional

    public static void inicializarPassagens() {
        Aeroporto aeroportoOrigem1 = new Aeroporto("Aeroporto Internacional de São Paulo", "GRU", "São Paulo", "SP", "Brasil");
        Aeroporto aeroportoDestino1 = new Aeroporto("Aeroporto Internacional de Lisboa", "LIS", "Lisboa", "Lisboa", "Portugal");

        CompanhiaAerea companhia1 = new CompanhiaAerea("TAP Portugal", "TP", "TAP", "12345678000100", 100.0, 50.0);

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

        // Adicionando passagem à lista
        passagens.add(passagem1);
    }

    public static List<PassagemAerea> pesquisarPassagens(Aeroporto origem, Aeroporto destino, Date data) {
        return passagens.stream()
                .filter(passagem -> (origem == null || passagem.getAeroportoOrigem().equals(origem)) &&
                                   (destino == null || passagem.getAeroportoDestino().equals(destino)) &&
                                   (data == null || (passagem.getDataHoraVoo().getTime() >= data.getTime() &&
                                                     passagem.getDataHoraVoo().getTime() < data.getTime() + 86400000))) // compara datas
                .collect(Collectors.toList());
    }

    public static boolean reservarAssento(String codigoVoo, String assento) {
        for (PassagemAerea passagem : passagens) {
            if (passagem.getCodigoVoo().equals(codigoVoo)) {
                return passagem.reservarAssento(assento);
            }
        }
        return false; // Voo não encontrado
    }

    // Método para aplicar uma taxa de cancelamento
    private boolean aplicarTaxaCancelamento(PassagemAerea passagem) {
        double taxaCancelamento = passagem.getTarifaBasica() * 0.1; // Exemplo: 10% da tarifa básica
        System.out.println("Taxa de cancelamento aplicada: " + taxaCancelamento);
        return true;
    }

    public boolean cancelarPassagem(PassagemAerea passagem, Passageiro passageiro) {
        if (passageiro.isStatusVIP()) {
            System.out.println("Cancelamento sem custo para passageiro VIP.");
            passagens.remove(passagem);
            return true; // Cancelamento processado
        } else {
            System.out.println("Aplicando taxa de cancelamento.");
            return aplicarTaxaCancelamento(passagem);
        }
    }

    public double calcularCustoBagagem(Passageiro passageiro, int quantidadeAdicional) {
        if (passageiro.isStatusVIP()) {
            System.out.println("Desconto de 50% aplicado para passageiro VIP.");
            return quantidadeAdicional * precoBagagem * 0.5; // Desconto VIP
        }
        return quantidadeAdicional * precoBagagem; // Valor regular
    }
}
