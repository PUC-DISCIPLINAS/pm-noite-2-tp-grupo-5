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

    // Método para cadastrar voos
    public void cadastrarVoo(String codigo, String origem, String destino, List<DiaSemana> diasSemana) {
        Voo novoVoo = new Voo(codigo, origem, destino, diasSemana);
        voos.add(novoVoo);
    }

    // Método para listar todos os voos
    public List<Voo> listarVoos() {
        return voos;
    }

    // Método para programar voos ativos nos próximos 30 dias
    public List<Voo> programarVoosAtivos() {
        LocalDate dataAtual = LocalDate.now();
        LocalDate dataFinal = dataAtual.plusDays(30);
        List<Voo> voosProgramados = new ArrayList<>();

        // Para cada voo cadastrado, programar seus voos de acordo com os dias da semana
        for (Voo voo : voos) {
            for (DiaSemana dia : voo.getDiasSemana()) {
                // Iniciar a programação a partir da próxima ocorrência do dia da semana
                LocalDate proximaDataVoo = dataAtual.with(dia.toDayOfWeek());

                // Se o próximo dia de voo estiver no passado, ajustar para a próxima semana
                if (proximaDataVoo.isBefore(dataAtual)) {
                    proximaDataVoo = proximaDataVoo.plusWeeks(1);
                }

                // Programar voos enquanto a data for dentro do período de 30 dias
                while (!proximaDataVoo.isAfter(dataFinal)) {  // Corrigido para não incluir a data final
                    Voo vooProgramado = new Voo(voo.getCodigo(), voo.getOrigem(), voo.getDestino(), voo.getDiasSemana());
                    voosProgramados.add(vooProgramado);
                    proximaDataVoo = proximaDataVoo.plusWeeks(1);  // Próximo voo na semana seguinte
                }
            }
        }

        return voosProgramados;
    }

    // Método para buscar voo por código
    public Voo buscarVooPorCodigo(String codigo) {
        for (Voo voo : voos) {
            if (voo.getCodigo().equals(codigo)) {
                return voo;
            }
        }
        return null;
    }
}
