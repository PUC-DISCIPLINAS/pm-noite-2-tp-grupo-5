package com.flyeasy.models;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PassagemAerea {
    private Aeroporto aeroportoOrigem;  // Ajustado para usar objeto Aeroporto
    private Aeroporto aeroportoDestino; // Ajustado para usar objeto Aeroporto
    private Date dataHoraVoo;
    private String codigoVoo;
    private CompanhiaAerea companhiaAerea;   // Ajustado para usar objeto CompanhiaAerea
    private double tarifaBasica;
    private double tarifaBusiness;
    private double tarifaPremium;
    private String moeda;
    private double percentualLucro = 20.0;
    private Map<String, Boolean> assentosDisponiveis;

    public PassagemAerea(Aeroporto aeroportoOrigem, Aeroporto aeroportoDestino, Date dataHoraVoo,
                         String codigoVoo, CompanhiaAerea companhiaAerea, // Ajustado para aceitar objetos
                         double tarifaBasica, double tarifaBusiness, double tarifaPremium, String moeda) {
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
        
        for (int i = 1; i <= 10; i++) {
            this.assentosDisponiveis.put("A" + i, true);
        }
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

    public void setTarifaBasica(double tarifaBasica) {
        this.tarifaBasica = tarifaBasica;
    }

    public double getTarifaBusiness() {
        return tarifaBusiness;
    }

    public void setTarifaBusiness(double tarifaBusiness) {
        this.tarifaBusiness = tarifaBusiness;
    }

    public double getTarifaPremium() {
        return tarifaPremium;
    }

    public void setTarifaPremium(double tarifaPremium) {
        this.tarifaPremium = tarifaPremium;
    }

    public String getMoeda() {
        return moeda;
    }

    public void setMoeda(String moeda) {
        this.moeda = moeda;
    }

    public double getPercentualLucro() {
        return percentualLucro;
    }

    public void setPercentualLucro(double percentualLucro) {
        this.percentualLucro = percentualLucro;
    }

    public boolean verificarDisponibilidade(String assento) {
        return assentosDisponiveis.getOrDefault(assento, false);
    }

    public boolean reservarAssento(String assento) {
        if (verificarDisponibilidade(assento)) {
            assentosDisponiveis.put(assento, false);
            return true;
        } else {
            return false;
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
}
