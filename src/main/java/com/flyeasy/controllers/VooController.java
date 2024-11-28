package com.flyeasy.controllers;

import com.flyeasy.models.Voo;
import com.flyeasy.models.DiaSemana;
import com.flyeasy.models.Passageiro;
import com.flyeasy.models.PassagemAerea;
import com.flyeasy.models.Aeronave;
import com.flyeasy.models.CompanhiaAerea;  // Adiciona a classe CompanhiaAerea

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class VooController {
    private List<Voo> voos;

    public VooController() {
        this.voos = new ArrayList<>();
    }

    public void cadastrarVoo(String codigo, String origem, String destino, List<DiaSemana> diasSemana, 
                             Aeronave aeronave, LocalDateTime horarioDecolagem, Duration duracao, 
                             double valorPassagem, CompanhiaAerea companhiaAerea) {  // Adicionando CompanhiaAerea como parâmetro
        if (buscarVooPorCodigo(codigo) != null) {
            throw new IllegalArgumentException("Já existe um voo cadastrado com o código: " + codigo);
        }

        // Agora passando a CompanhiaAerea para o construtor do Voo
        Voo novoVoo = new Voo(codigo, origem, destino, diasSemana, aeronave, horarioDecolagem, duracao, valorPassagem, companhiaAerea);
        voos.add(novoVoo);
    }

    public List<Voo> listarVoos() {
        return new ArrayList<>(voos); // Retorna uma cópia para evitar alterações externas
    }

    public List<Voo> programarVoosAtivos() {
        LocalDate dataAtual = LocalDate.now();
        LocalDate dataFinal = dataAtual.plusDays(30);
        List<Voo> voosProgramados = new ArrayList<>();

        for (Voo voo : voos) {
            for (DiaSemana dia : voo.getDiasSemana()) {
                LocalDate proximaDataVoo = dataAtual.with(dia.toTemporalAdjuster()); // Corrigido para usar o ajuste correto

                while (!proximaDataVoo.isAfter(dataFinal)) {
                    LocalDateTime horarioDecolagem = proximaDataVoo.atTime(voo.getHorarioDecolagem().toLocalTime());
                    // Criando o Voo programado, agora com companhia aérea
                    Voo vooProgramado = new Voo(
                        voo.getCodigo(),
                        voo.getOrigem(),
                        voo.getDestino(),
                        voo.getDiasSemana(),
                        voo.getAeronave(),
                        horarioDecolagem,
                        voo.getDuracao(),
                        voo.getValorPassagem(),
                        voo.getCompanhiaAerea()  // Passando a companhia aérea para o voo programado
                    );
                    voosProgramados.add(vooProgramado);
                    proximaDataVoo = proximaDataVoo.plusWeeks(1);
                }
            }
        }
        return voosProgramados;
    }

    public Voo buscarVooPorCodigo(String codigo) {
        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("O código do voo não pode ser nulo ou vazio.");
        }

        return voos.stream()
                   .filter(voo -> voo.getCodigo().equals(codigo))
                   .findFirst()
                   .orElse(null);
    }

    public boolean alterarVoo(PassagemAerea passagem, Passageiro passageiro, Voo novoVoo) {
        if (passagem == null || passageiro == null || novoVoo == null) {
            throw new IllegalArgumentException("Passagem, passageiro e novo voo não podem ser nulos.");
        }

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
        if (passagem == null || novoVoo == null) {
            throw new IllegalArgumentException("Passagem e novo voo não podem ser nulos.");
        }

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
