package com.flyeasy.models;

public class Passageiro {
    private String nome;
    private String cpf;
    private String email;  // Novo atributo para notificações de e-mail

    // Construtor
    public Passageiro(String nome, String cpf, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
    }

    // Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
