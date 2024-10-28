package com.flyeasy.controllers;

import com.flyeasy.models.Voo;
import com.flyeasy.models.DiaSemana;
import com.flyeasy.models.Aeronave;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.flyeasy.models.DiaSemana;
import com.flyeasy.models.Voo;

public class VooController {

    private List<Voo> voos = new ArrayList<>();  // Inicializa a lista de voos

    public void cadastrarVoo(String codigo, String origem, String destino, List<DiaSemana> diasSemana, Aeronave aeronave) {
        Voo novoVoo = new Voo(codigo, origem, destino, diasSemana, aeronave);
        voos.add(novoVoo);
    }

    public List<Voo> listarVoos() {
        return voos;
    }

    public List<Voo> programarVoosAtivos() {
        LocalDate dataAtual = LocalDate.now();
        LocalDate dataFinal = dataAtual.plusDays(30);
        List<Voo> voosProgramados = new ArrayList<>();
    
        for (Voo voo : voos) {
            for (DiaSemana dia : voo.getDiasSemana()) {
                LocalDate proximaDataVoo = dataAtual.with(dia.toDayOfWeek());
    
                while (!proximaDataVoo.isAfter(dataFinal)) {
                    Voo vooProgramado = new Voo(voo.getCodigo(), voo.getOrigem(), voo.getDestino(), voo.getDiasSemana(), voo.getAeronave());
                    voosProgramados.add(vooProgramado);
                    proximaDataVoo = proximaDataVoo.plusWeeks(1);
                }
            }
        }
        return voosProgramados;
    }
    
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
