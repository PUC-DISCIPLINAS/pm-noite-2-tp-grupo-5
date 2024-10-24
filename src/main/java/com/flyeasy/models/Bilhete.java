package com.flyeasy.models;

public class Bilhete {
    private String nome;
    private String sobrenome;
    private String documento;
    private PassagemAerea passagemAerea; // Composição com PassagemAerea
    private double valorTotal;

    // Construtor
    public Bilhete(String nome, String sobrenome, String documento, PassagemAerea passagemAerea) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.documento = documento;
        this.passagemAerea = passagemAerea;
        this.valorTotal = calcularValorTotal();
    }

    private double calcularValorTotal() {
        return passagemAerea.getTarifaBasica() + passagemAerea.calcularTarifaLucro();
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public PassagemAerea getPassagemAerea() {
        return passagemAerea;
    }

    public void setPassagemAerea(PassagemAerea passagemAerea) {
        this.passagemAerea = passagemAerea;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    @Override
    public String toString() {
        return "Bilhete{" +
                "nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", documento='" + documento + '\'' +
                ", passagemAerea=" + passagemAerea +
                ", valorTotal=" + valorTotal +
                '}';
    }
}
