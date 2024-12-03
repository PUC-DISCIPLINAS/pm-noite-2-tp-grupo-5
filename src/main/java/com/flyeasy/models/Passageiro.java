package com.flyeasy.models;

public class Passageiro {
    private String nome;
    private String cpf;
    private String email;
    private boolean statusVIP;
    private String documento;

    public Passageiro(String nome, String cpf, String email, boolean statusVIP, String documento) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.statusVIP = statusVIP;
        this.documento = documento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isStatusVIP() {
        return statusVIP;
    }

    public void setStatusVIP(boolean statusVIP) {
        this.statusVIP = statusVIP;
    }

    public String getDocumento() {
        return documento;
    }
}