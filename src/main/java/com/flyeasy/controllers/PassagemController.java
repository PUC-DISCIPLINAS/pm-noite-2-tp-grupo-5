package com.flyeasy.controllers;

import com.flyeasy.models.Aeroporto;
import com.flyeasy.models.CompanhiaAerea;
import com.flyeasy.models.Passageiro;
import com.flyeasy.models.PassagemAerea;
import com.flyeasy.models.PassagemAerea.StatusPassagem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PassagemController {
    private static List<PassagemAerea> passagens = new ArrayList<>();

    // Método para inicializar as passagens
    public static void inicializarPassagens() {
        Aeroporto aeroportoOrigem1 = new Aeroporto("Aeroporto Internacional de São Paulo", "GRU", "São Paulo", "SP",
                "Brasil", null);
        Aeroporto aeroportoDestino1 = new Aeroporto("Aeroporto Internacional de Lisboa", "LIS", "Lisboa", "Lisboa",
                "Portugal", null);

        Aeroporto aeroportoOrigem2 = new Aeroporto("Aeroporto Internacional de Miami", "MIA", "Miami", "FL", "EUA", null);
        Aeroporto aeroportoDestino2 = new Aeroporto("Aeroporto Internacional de Malpensa", "MXP", "Milão", "Lombardia",
                "Itália", null);

        CompanhiaAerea companhia1 = new CompanhiaAerea("TAP Portugal", "TP", "TAP", "12345678000100", 100.0, 50.0);
        CompanhiaAerea companhia2 = new CompanhiaAerea("American Airlines", "AA", "American Airlines", "12345678000200",
                150.0, 60.0);

        // Adicionando passagens à lista
        PassagemAerea passagem1 = new PassagemAerea(
                aeroportoOrigem1,
                aeroportoDestino1,
                new Date(System.currentTimeMillis() + 86400000), // 1 dia a partir de agora
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
                new Date(System.currentTimeMillis() + 172800000), // 2 dias a partir de agora
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

    // Método para pesquisar passagens
    public static List<PassagemAerea> pesquisarPassagens(Aeroporto origem, Aeroporto destino, Date data) {
        return passagens.stream()
                .filter(passagem -> (origem == null || passagem.getAeroportoOrigem().equals(origem)) &&
                        (destino == null || passagem.getAeroportoDestino().equals(destino)) &&
                        (data == null || (passagem.getDataHoraVoo().getTime() >= data.getTime() &&
                                passagem.getDataHoraVoo().getTime() < data.getTime() + 86400000)))
                .collect(Collectors.toList());
    }

    // Método para realizar o check-in
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

    // Método para verificar no-show
    public static void verificarNoShow() {
        for (PassagemAerea passagem : passagens) {
            passagem.registrarNoShow();
        }
    }

    // Método para reservar assento
    public static boolean reservarAssento(String codigoVoo, String assento) {
        for (PassagemAerea passagem : passagens) {
            if (passagem.getCodigoVoo().equals(codigoVoo)) {
                Map<String, Boolean> assentosDisponiveis = passagem.getAssentosDisponiveis();
                if (assentosDisponiveis.containsKey(assento) && assentosDisponiveis.get(assento)) {
                    assentosDisponiveis.put(assento, false);  // Marca o assento como reservado
                    return true;
                }
            }
        }
        return false;
    }

    // Método para alterar o status da passagem
    public static boolean atualizarStatusPassagem(String codigoVoo, StatusPassagem novoStatus) {
        for (PassagemAerea passagem : passagens) {
            if (passagem.getCodigoVoo().equals(codigoVoo)) {
                passagem.setStatusPassagem(novoStatus);
                return true;
            }
        }
        return false;
    }

    // Método privado para aplicar taxa de cancelamento
    private static boolean aplicarTaxaCancelamento(PassagemAerea passagem) {
        double taxaCancelamento = passagem.getTarifaBasica() * 0.1; // Exemplo: 10% da tarifa básica
        System.out.println("Taxa de cancelamento aplicada: " + taxaCancelamento);
        return true;
    }

    // Método para cancelar passagem
    public static boolean cancelarPassagem(PassagemAerea passagem, Passageiro passageiro) {
        if (passageiro.isStatusVIP()) {
            System.out.println("Cancelamento sem custo para passageiro VIP.");
            passagens.remove(passagem);
            return true; // Cancelamento processado
        } else {
            System.out.println("Aplicando taxa de cancelamento.");
            return aplicarTaxaCancelamento(passagem);
        }
    }

    // Método para calcular custo de bagagem
    public static double calcularCustoBagagem(Passageiro passageiro, int quantidadeAdicional, PassagemAerea passagem) {
        if (passageiro.isStatusVIP()) {
            System.out.println("Desconto de 50% aplicado para passageiro VIP.");
            return quantidadeAdicional * passagem.getCompanhiaAerea().getValorBagagemAdicional() * 0.5; // Desconto VIP
        }
        return quantidadeAdicional * passagem.getCompanhiaAerea().getValorBagagemAdicional(); // Valor regular
    }
}
