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
    private List<Voo> voos;

    private List<Voo> voo = new ArrayList<>();
    
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
        return voos.stream()
                   .filter(voo -> voo.getCodigo().equals(codigo)) // Filtra a lista para encontrar o voo com o código correspondente
                   .findFirst()
                   .orElse(null); // Retorna o primeiro voo encontrado ou null se nenhum for encontrado
    }

    /**
     * Método para verificar se um voo ocorre em um dia específico da semana.
     * @param codigo Código do voo a ser verificado
     * @param dia Dia da semana a ser verificado
     * @return true se o voo ocorrer no dia, false caso contrário
     */
    public boolean vooOcorreNoDia(String codigo, DiaSemana dia) {
        Voo voo = buscarVooPorCodigo(codigo); // Busca o voo pelo código
        if (voo != null) { // Se o voo foi encontrado
            return voo.getDiasSemana().contains(dia); // Verifica se o dia está na lista de dias da semana do voo
        }
        return false; // Retorna false se o voo não ocorre no dia
    }
}

