package com.flyeasy.controllers;

import com.flyeasy.models.Voo;
import com.flyeasy.models.DiaSemana;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VooController {
    private List<Voo> voos;

    public VooController() {
        this.voos = new ArrayList<>();
    }

    public void cadastrarVoo(String codigo, String origem, String destino, List<DiaSemana> diasSemana) {
        Voo novoVoo = new Voo(codigo, origem, destino, diasSemana);
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
            LocalDate proximaDataVoo = dataAtual;

            while (proximaDataVoo.isBefore(dataFinal) || proximaDataVoo.isEqual(dataFinal)) {
                for (DiaSemana dia : voo.getDiasSemana()) {
                    if (proximaDataVoo.getDayOfWeek().equals(dia.toDayOfWeek())) {
                        Voo vooProgramado = new Voo(voo.getCodigo(), voo.getOrigem(), voo.getDestino(), voo.getDiasSemana());
                        voosProgramados.add(vooProgramado);
                    }
                }
                proximaDataVoo = proximaDataVoo.plusDays(1);
            }
        }
        return voosProgramados;
    }

    public Voo buscarVooPorCodigo(String codigo) {
        for (Voo voo : voos) {
            if (voo.getCodigo().equals(codigo)) {
                return voo;
            }
        }
        return null;
    }
}
