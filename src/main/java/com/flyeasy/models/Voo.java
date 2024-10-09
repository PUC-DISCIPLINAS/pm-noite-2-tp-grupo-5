package model;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

public class Voo {
    private String codigo;
    private String origem;
    private String destino;
    private LocalTime horarioPartida;
    private Duration duracao;
    private List<DiaSemana> diasSemana;

    public Voo(String codigo, String origem, String destino, LocalTime horarioPartida, Duration duracao, List<DiaSemana> diasSemana) {
        this.codigo = codigo;
        this.origem = origem;
        this.destino = destino;
        this.horarioPartida = horarioPartida;
        this.duracao = duracao;
        this.diasSemana = diasSemana;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getOrigem() {
        return origem;
    }

    public String getDestino() {
        return destino;
    }

    public LocalTime getHorarioPartida() {
        return horarioPartida;
    }

    public Duration getDuracao() {
        return duracao;
    }

    public List<DiaSemana> getDiasSemana() {
        return diasSemana;
    }

    @Override
    public String toString() {
        return "Voo " + codigo + ": " + origem + " -> " + destino + ", Partida: " + horarioPartida + ", Duração: " + duracao.toMinutes() + " minutos, Dias: " + diasSemana;
    }
}
