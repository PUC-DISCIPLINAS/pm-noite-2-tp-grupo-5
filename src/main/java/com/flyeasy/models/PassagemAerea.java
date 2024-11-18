package com.flyeasy.models;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
<<<<<<< HEAD
    private Voo voo;

    public PassagemAerea(Aeroporto aeroportoOrigem, Aeroporto aeroportoDestino, Date dataHoraVoo,
                         String codigoVoo, CompanhiaAerea companhiaAerea, 
                         double tarifaBasica, double tarifaBusiness, double tarifaPremium, String moeda) {
=======
    private boolean checkInRealizado;  // Novo campo para verificar se o check-in foi realizado

    public PassagemAerea(Aeroporto aeroportoOrigem, Aeroporto aeroportoDestino, Date dataHoraVoo,
                         String codigoVoo, CompanhiaAerea companhiaAerea, double tarifaBasica, 
                         double tarifaBusiness, double tarifaPremium, String moeda) {
>>>>>>> origin/main
        this.aeroportoOrigem = aeroportoOrigem;
        this.aeroportoDestino = aeroportoDestino;
        this.dataHoraVoo = dataHoraVoo;
        this.codigoVoo = codigoVoo;
        this.companhiaAerea = companhiaAerea;
        this.tarifaBasica = tarifaBasica;
        this.tarifaBusiness = tarifaBusiness;
        this.tarifaPremium = tarifaPremium;
        this.moeda = moeda;
        this.assentosDisponiveis = new HashMap<>();
        this.checkInRealizado = false;  // Inicializa como falso

        for (int i = 1; i <= 10; i++) {
            this.assentosDisponiveis.put("A" + i, true);
        }
    }

<<<<<<< HEAD
    public Voo getVoo() {
        return voo;
    }

    public void setVoo(Voo voo) {
        this.voo = voo;
    }

    public Aeroporto getAeroportoOrigem() {
        return aeroportoOrigem;
=======
    // Getters e setters...

    // Método para verificar se o check-in está dentro do período permitido
    public boolean podeRealizarCheckIn() {
        Date agora = new Date();
        long diff = dataHoraVoo.getTime() - agora.getTime();
        long diffHours = diff / (60 * 60 * 1000) % 24;
        long diffMinutes = diff / (60 * 1000) % 60;
        
        return (diffHours >= 48 && diffMinutes >= 0) || (diffHours < 48 && diffMinutes >= 30);
>>>>>>> origin/main
    }

    // Método para realizar o check-in
    public boolean realizarCheckIn() {
        if (podeRealizarCheckIn()) {
            this.checkInRealizado = true;
            return true;
        }
        return false;  // Não é possível fazer o check-in se fora do período permitido
    }

    // Método para registrar No Show
    public void registrarNoShow() {
        if (!checkInRealizado && dataHoraVoo.before(new Date())) {
            System.out.println("No show registrado para o voo: " + codigoVoo);
        }
    }

<<<<<<< HEAD
    public double getPreco() {
        return tarifaBasica;
    }

    public double getPrecoComTaxas() {
        return tarifaBasica + calcularTarifaLucro();
    }

    public double getPrecoEmReais() {
        return "USD".equals(moeda) ? tarifaBasica * 5.0 : tarifaBasica;
    }

    public double calcularTarifaLucro() {
        return tarifaBasica * (percentualLucro / 100);
    }
=======
    // Getters e setters adicionais...
>>>>>>> origin/main
}
