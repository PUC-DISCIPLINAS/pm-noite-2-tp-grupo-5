package com.flyeasy.controllers;

import com.flyeasy.models.Voo;
import com.flyeasy.models.DiaSemana;
import com.flyeasy.models.Passageiro;
import com.flyeasy.models.PassagemAerea;
import com.flyeasy.models.Aeronave;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VooController {
    private List<Voo> voos;

    public VooController() {
        this.voos = new ArrayList<>();
    }

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
                    Voo vooProgramado = new Voo(
                        voo.getCodigo(),
                        voo.getOrigem(),
                        voo.getDestino(),
                        voo.getDiasSemana(),
                        voo.getAeronave()
                    );
                    voosProgramados.add(vooProgramado);
                    proximaDataVoo = proximaDataVoo.plusWeeks(1);
                }
            }
        }
        return voosProgramados;
    }

    public Voo buscarVooPorCodigo(String codigo) {
        return voos.stream()
                   .filter(voo -> voo.getCodigo().equals(codigo))
                   .findFirst()
                   .orElse(null);
    }

    public boolean alterarVoo(PassagemAerea passagem, Passageiro passageiro, Voo novoVoo) {
        if (passageiro.isStatusVIP()) {
            System.out.println("Alteração sem custo para passageiro VIP.");
            passagem.setVoo(novoVoo);
            return true;
        } else {
            System.out.println("Aplicando taxa de alteração.");
            return aplicarTaxaAlteracao(passagem, novoVoo);
        }
    }

    private boolean aplicarTaxaAlteracao(PassagemAerea passagem, Voo novoVoo) {
        double taxaAlteracao = passagem.getTarifaBasica() * 0.05;
        System.out.println("Taxa de alteração aplicada: " + taxaAlteracao);
        passagem.setVoo(novoVoo);
        return true;
    }

    public boolean vooOcorreNoDia(String codigo, DiaSemana dia) {
        Voo voo = buscarVooPorCodigo(codigo);
        if (voo != null) {
            return voo.getDiasSemana().contains(dia);
        }
        return false;
    }
}