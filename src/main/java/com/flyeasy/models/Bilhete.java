package com.flyeasy.models;

public class Bilhete {
    private String nome;
    private String sobrenome;
    private String documento;
    private PassagemAerea passagemAerea;
    private double valorTotal;
    private Passageiro passageiro;
    private Voo voo;
    private String assento;
    private double franquiaBagagem;
    public String status;
    private String codigo;

    // Construtor
    public Bilhete(String codigo, String nome, String sobrenome, String documento, PassagemAerea passagemAerea, Passageiro passageiro,
            Voo voo) {
        this.codigo = codigo;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.documento = documento;
        this.passagemAerea = passagemAerea;
        this.valorTotal = calcularValorTotal();
        this.passageiro = passageiro;
        this.voo = voo;
        this.assento = assento;
        this.status = "Emitido";
    }

    public Passageiro getPassageiro() {
        return passageiro;
    }

    public Voo getVoo() {
        return voo;
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

    public String getAssento() {
        return assento;
    }

    public void setAssento(String assento) {
        this.assento = assento;
    }

    public double getFranquiaBagagem() {
        return franquiaBagagem;
    }

    public void setFranquiaBagagem(double franquiaBagagem) {
        this.franquiaBagagem = franquiaBagagem;
    }

    public void adicionarFranquiaBagagem(Bilhete bilhete, double custo) {
        bilhete.setFranquiaBagagem(custo);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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
