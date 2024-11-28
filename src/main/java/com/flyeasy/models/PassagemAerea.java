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
    private boolean checkInRealizado;
    private Voo voo;

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
        this.assentosDisponiveis = new HashMap<>();
        this.checkInRealizado = false;

        for (int i = 1; i <= 10; i++) {
            this.assentosDisponiveis.put("A" + i, true);
        }
    }

    public boolean podeRealizarCheckIn() {
        Date agora = new Date();
        long diff = dataHoraVoo.getTime() - agora.getTime();
        long diffHours = diff / (60 * 60 * 1000) % 24;
        long diffMinutes = diff / (60 * 1000) % 60;

        return (diffHours >= 48 && diffMinutes >= 0) || (diffHours < 48 && diffMinutes >= 30);
    }

    public boolean realizarCheckIn() {
        if (podeRealizarCheckIn()) {
            this.checkInRealizado = true;
            return true;
        }
        return false;
    }

    public void registrarNoShow() {
        if (!checkInRealizado && dataHoraVoo.before(new Date())) {
            System.out.println("No show registrado para o voo: " + codigoVoo);
        }
    }

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

    public boolean reservarAssento(String assento) {
        if (assentosDisponiveis.containsKey(assento) && assentosDisponiveis.get(assento)) {
            assentosDisponiveis.put(assento, false);
            return true;
        }
        return false;
    }

    public Map<String, Boolean> getAssentosDisponiveis() {
        return assentosDisponiveis;
    }

    public Aeroporto getAeroportoOrigem() {
        return aeroportoOrigem;
    }

    public void setAeroportoOrigem(Aeroporto aeroportoOrigem) {
        this.aeroportoOrigem = aeroportoOrigem;
    }

    public Aeroporto getAeroportoDestino() {
        return aeroportoDestino;
    }

    public void setAeroportoDestino(Aeroporto aeroportoDestino) {
        this.aeroportoDestino = aeroportoDestino;
    }

    public Date getDataHoraVoo() {
        return dataHoraVoo;
    }

    public void setDataHoraVoo(Date dataHoraVoo) {
        this.dataHoraVoo = dataHoraVoo;
    }

    public String getCodigoVoo() {
        return codigoVoo;
    }

    public void setCodigoVoo(String codigoVoo) {
        this.codigoVoo = codigoVoo;
    }

    public CompanhiaAerea getCompanhiaAerea() {
        return companhiaAerea;
    }

    public void setCompanhiaAerea(CompanhiaAerea companhiaAerea) {
        this.companhiaAerea = companhiaAerea;
    }

    public double getTarifaBasica() {
        return tarifaBasica;
    }

    public Voo getVoo() {
        return voo;
    }

    public void setVoo(Voo voo) {
        this.voo = voo;
    }

    public double getTarifaBusiness() {
        return tarifaBusiness;
    }

    public double getTarifaPremium() {
        return tarifaPremium;
    }

    public String getMoeda() {
        return moeda;
    }
}