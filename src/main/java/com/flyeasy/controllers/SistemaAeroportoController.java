package com.flyeasy.controllers;

import com.flyeasy.views.*;
import com.flyeasy.models.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SistemaAeroportoController {
    private List<Voo> voos;

    public SistemaAeroportoController() {
        this.voos = new ArrayList<>();
    }

    public void adicionarVoo(Voo voo) {
        voos.add(voo);
    }

    public List<Voo> buscarVoosDiretos(String origem, String destino, LocalDateTime data) {
        return voos.stream()
                .filter(v -> v.getOrigem().equalsIgnoreCase(origem) &&
                             v.getDestino().equalsIgnoreCase(destino) &&
                             v.getHorarioDecolagem().toLocalDate().equals(data.toLocalDate()))
                .collect(Collectors.toList());
    }

    public Bilhete comprarPassagem(Passageiro passageiro, Voo voo) {
        // Criar PassagemAerea (o objeto precisa ser corretamente instanciado)
        PassagemAerea passagemAerea = new PassagemAerea(); // Defina a criação da passagem adequadamente
        return new Bilhete(
                passageiro.getNome(),
                passageiro.getSobrenome(),
                passageiro.getDocumento(),
                passagemAerea,
                passageiro,
                voo
        );
    }

    public void realizarCheckIn(Bilhete bilhete) {
        System.out.println("Check-in realizado para " + bilhete.getPassageiro().getNome());
    }

    public void exibirCartaoEmbarque(Bilhete bilhete) {
        System.out.println("Cartão de Embarque: ");
        System.out.println("Passageiro: " + bilhete.getPassageiro().getNome());
        System.out.println("Voo: " + bilhete.getVoo().toString());
    }
}
