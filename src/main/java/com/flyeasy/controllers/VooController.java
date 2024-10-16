package com.flyeasy.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.flyeasy.models.DiaSemana;
import com.flyeasy.models.Voo;

public class VooController {

    private List<Voo> voos = new ArrayList<>();  // Inicializa a lista de voos

    // Método para cadastrar um novo voo
    public void cadastrarVoo(String codigo, String origem, String destino, List<DiaSemana> diasSemana) {
        Voo novoVoo = new Voo(codigo, origem, destino, diasSemana, null); // Null para dataVoo inicial
        voos.add(novoVoo);  // Adiciona o novo voo à lista
    }

    // Método para buscar voo por código
    public Voo buscarVooPorCodigo(String codigo) {
        Optional<Voo> vooEncontrado = voos.stream()
                .filter(voo -> voo.getCodigo().equals(codigo))
                .findFirst();
        return vooEncontrado.orElse(null);  // Retorna o voo encontrado ou null
    }

    // Programar voos ativos (essa parte já estava implementada)
    public List<Voo> programarVoosAtivos() {
        // Sua lógica existente para programar voos
        return new ArrayList<>();  // Modifique conforme a lógica de seu projeto
    }
}
