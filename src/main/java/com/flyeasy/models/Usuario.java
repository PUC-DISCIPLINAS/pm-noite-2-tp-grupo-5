package com.flyeasy.models;

import org.mindrot.jbcrypt.BCrypt;

public class Usuario {
    private String username;
    private String senha;

    // Getters e Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = BCrypt.hashpw(senha, BCrypt.gensalt());
    }

    public boolean verificarSenha(String senha) {
        return BCrypt.checkpw(senha, this.senha);
    }
}
