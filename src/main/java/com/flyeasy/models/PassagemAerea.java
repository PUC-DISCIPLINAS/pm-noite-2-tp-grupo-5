package com.flyeasy.models;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PassagemAerea {
    private Aeroporto aeroportoOrigem;  // Corrigido para o tipo Aeroporto
    private Aeroporto aeroportoDestino; // Corrigido para o tipo Aeroporto
    private Date dataHoraVoo;
    private String codigoVoo;
    private CompanhiaAerea companhiaAerea;  // Corrigido para o tipo CompanhiaAerea
    private double tarifaBasica;
    private double tarifaBusiness;
    private double tarifaPremium;
    private String moeda;
    private double percentualLucro = 20.0;
    private Map<String, Boolean> assentosDisponiveis;

    // Construtor
    public PassagemAerea(Aeroporto aeroportoOrigem, Aeroporto aeroportoDestino, Date dataHoraVoo,
                         String codigoVoo, CompanhiaAerea companhiaAerea,
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

    // Getters e Setters corrigidos
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
