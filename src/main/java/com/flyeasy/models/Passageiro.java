package com.flyeasy.models;

public class Passageiro {
    private String nome;
    private String cpf;
    private String email;  // Novo atributo para notificações de e-mail
    private boolean statusVIP;  // Novo atributo para indicar status VIP do passageiro

    // Atualizar Construtor
    public Passageiro(String nome, String cpf, String email, boolean statusVIP) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.statusVIP = statusVIP;
    }

    // Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public boolean isStatusVIP() { return statusVIP; }
    public void setStatusVIP(boolean statusVIP) { this.statusVIP = statusVIP; }
}