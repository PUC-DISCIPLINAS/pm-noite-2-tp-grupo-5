package com.flyeasy;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PassagemAerea {
    private String aeroportoOrigem;  
    private String aeroportoDestino;
    private Date dataHoraVoo;
    private String codigoVoo;
    private String companhiaAerea;   
    private double tarifaBasica;
    private double tarifaBusiness;
    private double tarifaPremium;
    private String moeda;
    private double percentualLucro = 20.0;
    private Map<String, Boolean> assentosDisponiveis;

    // Construtor
    public PassagemAerea(String aeroportoOrigem, String aeroportoDestino, Date dataHoraVoo,
                         String codigoVoo, String companhiaAerea,
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
        
        // Inicializa os assentos (exemplo com 10 assentos: A1 a A10)
        for (int i = 1; i <= 10; i++) {
            this.assentosDisponiveis.put("A" + i, true); // todos os assentos começam como disponíveis
        }
    }

    // Getters e Setters
    public String getAeroportoOrigem() {
        return aeroportoOrigem;
    }

    public void setAeroportoOrigem(String aeroportoOrigem) {
        this.aeroportoOrigem = aeroportoOrigem;
    }

    public String getAeroportoDestino() {
        return aeroportoDestino;
    }

    public void setAeroportoDestino(String aeroportoDestino) {
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

    public String getCompanhiaAerea() {
        return companhiaAerea;
    }

    public void setCompanhiaAerea(String companhiaAerea) {
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

    // Métodos para verificar e reservar assento
    public boolean verificarDisponibilidade(String assento) {
        return assentosDisponiveis.getOrDefault(assento, false);
    }

    public boolean reservarAssento(String assento) {
        if (verificarDisponibilidade(assento)) {
            assentosDisponiveis.put(assento, false); // Marca o assento como reservado
            return true;
        } else {
            return false;
        }
    }

    // Métodos para calcular os preços
    public double getPreco() {
        return tarifaBasica;
    }

    public double getPrecoComTaxas() {
        return tarifaBasica + calcularTarifaLucro(); // Preço básico + lucro
    }

    public double getPrecoEmReais() {
        // Aqui pode-se converter para reais, considerando uma taxa de câmbio se necessário
        return "USD".equals(moeda) ? tarifaBasica * 5.0 : tarifaBasica; // Exemplo: converte USD para BRL
    }

    public double calcularTarifaLucro() {
        return tarifaBasica * (percentualLucro / 100);
    }
}

