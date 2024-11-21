package com.flyeasy.models;

import java.util.*;

public class PassagemAerea {
    private Aeroporto aeroportoOrigem;
    private Aeroporto aeroportoDestino;
    private Date dataHoraVoo;
    private String codigoVoo;
    private CompanhiaAerea companhiaAerea;
    private double tarifaBasica;
    private double tarifaBusiness;
    private double tarifaPremium;
    private String moeda;
    private double percentualLucro = 20.0;
    private Map<String, Boolean> assentosDisponiveis;
    private StatusPassagem statusPassagem;
    private boolean checkInRealizado;
    private boolean passageiroVIP; // Indica se o passageiro é VIP

    public enum StatusPassagem {
        ADQUIRIDA, CANCELADA, CHECKIN_REALIZADO, EMBARQUE_REALIZADO, NO_SHOW
    }

    // Construtor
    public PassagemAerea(Aeroporto aeroportoOrigem, Aeroporto aeroportoDestino, Date dataHoraVoo,
                         String codigoVoo, CompanhiaAerea companhiaAerea, double tarifaBasica,
                         double tarifaBusiness, double tarifaPremium, String moeda) {
        this.aeroportoOrigem = aeroportoOrigem;
        this.aeroportoDestino = aeroportoDestino;
        this.dataHoraVoo = dataHoraVoo;
        this.codigoVoo = codigoVoo;
        this.companhiaAerea = companhiaAerea;
        this.tarifaBasica = tarifaBasica;
        this.tarifaBusiness = tarifaBusiness;
        this.tarifaPremium = tarifaPremium;
        this.moeda = moeda;
        this.statusPassagem = StatusPassagem.ADQUIRIDA;
        this.checkInRealizado = false; // Inicializa o check-in como não realizado
        this.passageiroVIP = false; // Inicializa como não VIP
        this.assentosDisponiveis = new HashMap<>();

        for (int i = 1; i <= 10; i++) {
            this.assentosDisponiveis.put("A" + i, true);
        }
    }

    // Métodos para busca de voos com conexão
    public static List<PassagemAerea> buscarVoosComConexao(List<PassagemAerea> voos, Aeroporto origem,
                                                           Aeroporto destino, Date data) {
        List<PassagemAerea> voosComConexao = new ArrayList<>();

        for (PassagemAerea voo1 : voos) {
            if (voo1.getAeroportoOrigem().equals(origem) && voo1.getDataHoraVoo().equals(data)) {
                for (PassagemAerea voo2 : voos) {
                    if (voo1.getAeroportoDestino().equals(voo2.getAeroportoOrigem())
                            && voo2.getAeroportoDestino().equals(destino)
                            && voo2.getDataHoraVoo().after(voo1.getDataHoraVoo())) {
                        voosComConexao.add(voo1);
                        voosComConexao.add(voo2);
                    }
                }
            }
        }
        return voosComConexao;
    }

    // Compra de passagem para passageiros VIP
    public void tornarPassageiroVIP() {
        this.passageiroVIP = true;
    }

    public boolean isPassageiroVIP() {
        return passageiroVIP;
    }

    public String detalhesCusto() {
        double tarifaFinal = tarifaBasica + calcularTarifaLucro();
        String detalhes = "Tarifa Básica: " + tarifaBasica + " " + moeda + "\n" +
                "Lucro: " + calcularTarifaLucro() + " " + moeda + "\n" +
                "Total: " + tarifaFinal + " " + moeda;

        if (passageiroVIP) {
            detalhes += "\nPassageiro VIP: Franquia de bagagem incluída.";
        }
        return detalhes;
    }

    // Cancelamento do voo e passagem
    public void cancelarPassagem() {
        atualizarStatus(StatusPassagem.CANCELADA);
        System.out.println("Passagem para o voo " + codigoVoo + " foi cancelada.");
    }

    public void cancelarVoo(List<PassagemAerea> passagens) {
        for (PassagemAerea passagem : passagens) {
            if (passagem.getCodigoVoo().equals(this.codigoVoo)) {
                passagem.cancelarPassagem();
            }
        }
        System.out.println("Voo " + codigoVoo + " foi cancelado.");
    }

    // Métodos auxiliares
    public Aeroporto getAeroportoOrigem() {
        return aeroportoOrigem;
    }

    public Aeroporto getAeroportoDestino() {
        return aeroportoDestino;
    }

    public Date getDataHoraVoo() {
        return dataHoraVoo;
    }

    public String getCodigoVoo() {
        return codigoVoo;
    }

    public double getTarifaBasica() {
        return tarifaBasica;
    }

    public double calcularTarifaLucro() {
        return tarifaBasica * (percentualLucro / 100);
    }

    public void atualizarStatus(StatusPassagem novoStatus) {
        this.statusPassagem = novoStatus;
    }
}
