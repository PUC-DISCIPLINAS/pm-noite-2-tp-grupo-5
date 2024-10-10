package com.flyeasy.models;

public class CompanhiaAerea {
    private String nome;
    private String codigo;
    private String razaoSocial;
    private String cnpj;
    private double valorPrimeiraBagagem;
    private double valorBagagemAdicional; 

    // Construtor
    public CompanhiaAerea(String nome, String codigo, String razaoSocial, String cnpj, 
                          double valorPrimeiraBagagem, double valorBagagemAdicional) {
        this.nome = nome;
        this.codigo = codigo;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.valorPrimeiraBagagem = valorPrimeiraBagagem;
        this.valorBagagemAdicional = valorBagagemAdicional;
    }
    
    // Getters e Setters
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public double getValorPrimeiraBagagem() {
        return valorPrimeiraBagagem;
    }

    public void setValorPrimeiraBagagem(double valorPrimeiraBagagem) {
        this.valorPrimeiraBagagem = valorPrimeiraBagagem;
    }

    public double getValorBagagemAdicional() {
        return valorBagagemAdicional;
    }

    public void setValorBagagemAdicional(double valorBagagemAdicional) {
        this.valorBagagemAdicional = valorBagagemAdicional;
    }
    
    // Método para calcular o valor da bagagem
    public double calcularValorBagagem(int quantidadeBagagens) {
        if (quantidadeBagagens <= 0) {
            return 0.0;
        } else if (quantidadeBagagens == 1) {
            return valorPrimeiraBagagem;
        } else {
            return valorPrimeiraBagagem + (quantidadeBagagens - 1) * valorBagagemAdicional;
        }
    }
}
