package controller;

import model.Voo;
import model.DiaSemana;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class VooController {
    private List<Voo> voos;

    public VooController() {
        this.voos = new ArrayList<>();
    }

    // Método para cadastrar um novo voo
    public void cadastrarVoo(String codigo, String origem, String destino, LocalTime horarioPartida, Duration duracao, List<DiaSemana> diasSemana) {
        Voo novoVoo = new Voo(codigo, origem, destino, horarioPartida, duracao, diasSemana);
        voos.add(novoVoo);
    }

    // Método para listar todos os voos cadastrados
    public List<Voo> listarVoos() {
        return voos;
    }

    // Método para encontrar um voo pelo código
    public Voo buscarVooPorCodigo(String codigo) {
        for (Voo voo : voos) {
            if (voo.getCodigo().equals(codigo)) {
                return voo;
            }
        }
        return null;
    }

    // Método para verificar se um voo ocorre em um dia específico
    public boolean vooOcorreNoDia(String codigo, DiaSemana dia) {
        Voo voo = buscarVooPorCodigo(codigo);
        if (voo != null) {
            return voo.getDiasSemana().contains(dia);
        }
        return false;
    }
}
