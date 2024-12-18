package com.flyeasy.controllers;

import com.flyeasy.models.Aeroporto;
import com.flyeasy.models.CompanhiaAerea;
import com.flyeasy.models.Passageiro;
import com.flyeasy.models.PassagemAerea;
import com.flyeasy.models.PassagemAerea.StatusPassagem;
import com.flyeasy.models.TipoVoo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PassagemController {
    private static List<PassagemAerea> passagens = new ArrayList<>();

    // Método para inicializar passagens
    public static void inicializarPassagens() {
        Aeroporto aeroportoOrigem1 = new Aeroporto("Aeroporto Internacional de São Paulo", "GRU", "São Paulo", "SP", "Brasil", TipoVoo.DOMESTICO, -23.5505, -46.6333);
        Aeroporto aeroportoDestino1 = new Aeroporto("Aeroporto Internacional de Lisboa", "LIS", "Lisboa", "Lisboa", "Portugal", TipoVoo.INTERNACIONAL, 38.7167, -9.1395);

        CompanhiaAerea companhia1 = new CompanhiaAerea("TAP Portugal", "TP", "TAP", "12345678000100", 100.0, 50.0);

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

        passagens.add(passagem1);
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
        passagem.setTarifaBasica(passagem.getTarifaBasica() - taxaCancelamento); // Aplicando o desconto de cancelamento na tarifa
        System.out.println("Taxa de cancelamento aplicada: " + taxaCancelamento);
        return true;
    }

    // Método para cancelar passagem
    public static boolean cancelarPassagem(PassagemAerea passagem, Passageiro passageiro) {
        if (passageiro.isStatusVIP()) {
            System.out.println("Cancelamento sem custo para passageiro VIP.");
            passagens.remove(passagem);
            return true;
        } else {
            System.out.println("Aplicando taxa de cancelamento.");
            return aplicarTaxaCancelamento(passagem);  // Aplica a taxa de cancelamento no valor da tarifa
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
