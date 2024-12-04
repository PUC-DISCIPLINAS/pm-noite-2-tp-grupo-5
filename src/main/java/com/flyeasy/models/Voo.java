package com.flyeasy.models;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class Voo {
    private String codigo;
    private String origem;
    private String destino;
    private LocalDateTime horarioDecolagem;
    private Duration duracao;
    private double valorPassagem;
    private List<DiaSemana> diasSemana;
    private Aeronave aeronave;
    private CompanhiaAerea companhiaAerea;

    // Construtor
    public Voo(String codigo, String origem, String destino, List<DiaSemana> diasSemana, Aeronave aeronave,
            LocalDateTime horarioDecolagem, Duration duracao, double valorPassagem, CompanhiaAerea companhiaAerea) {
        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("O código do voo não pode ser nulo ou vazio.");
        }
        if (origem == null || origem.isBlank()) {
            throw new IllegalArgumentException("A origem não pode ser nula ou vazia.");
        }
        if (destino == null || destino.isBlank()) {
            throw new IllegalArgumentException("O destino não pode ser nulo ou vazio.");
        }
        if (horarioDecolagem == null) {
            throw new IllegalArgumentException("O horário de decolagem não pode ser nulo.");
        }
        if (duracao == null || duracao.isNegative() || duracao.isZero()) {
            throw new IllegalArgumentException("A duração deve ser positiva.");
        }
        if (valorPassagem < 0) {
            throw new IllegalArgumentException("O valor da passagem não pode ser negativo.");
        }
        if (aeronave == null) {
            throw new IllegalArgumentException("A aeronave não pode ser nula.");
        }

        this.codigo = codigo;
        this.origem = origem;
        this.destino = destino;
        this.diasSemana = diasSemana;
        this.aeronave = aeronave;
        this.horarioDecolagem = horarioDecolagem;
        this.duracao = duracao;
        this.valorPassagem = valorPassagem;
        this.companhiaAerea = companhiaAerea;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("O código do voo não pode ser nulo ou vazio.");
        }
        this.codigo = codigo;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        if (origem == null || origem.isBlank()) {
            throw new IllegalArgumentException("A origem não pode ser nula ou vazia.");
        }
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        if (destino == null || destino.isBlank()) {
            throw new IllegalArgumentException("O destino não pode ser nulo ou vazio.");
        }
        this.destino = destino;
    }

    public LocalDateTime getHorarioDecolagem() {
        return horarioDecolagem;
    }

    public void setHorarioDecolagem(LocalDateTime horarioDecolagem) {
        if (horarioDecolagem == null) {
            throw new IllegalArgumentException("O horário de decolagem não pode ser nulo.");
        }
        this.horarioDecolagem = horarioDecolagem;
    }

    public Duration getDuracao() {
        return duracao;
    }

    public void setDuracao(Duration duracao) {
        if (duracao == null || duracao.isNegative() || duracao.isZero()) {
            throw new IllegalArgumentException("A duração deve ser positiva.");
        }
        this.duracao = duracao;
    }

    public double getValorPassagem() {
        return valorPassagem;
    }

    public void setValorPassagem(double valorPassagem) {
        if (valorPassagem < 0) {
            throw new IllegalArgumentException("O valor da passagem não pode ser negativo.");
        }
        this.valorPassagem = valorPassagem;
    }

    public List<DiaSemana> getDiasSemana() {
        return diasSemana;
    }

    public void setDiasSemana(List<DiaSemana> diasSemana) {
        this.diasSemana = diasSemana;
    }

    public Aeronave getAeronave() {
        return aeronave;
    }

    public void setAeronave(Aeronave aeronave) {
        if (aeronave == null) {
            throw new IllegalArgumentException("A aeronave não pode ser nula.");
        }
        this.aeronave = aeronave;
    }

    public int getCapacidadePassageiros() {
        return aeronave.getCapacidadePassageiros();
    }

    public LocalDateTime getHorarioChegada() {
        return horarioDecolagem.plus(duracao);
    }

    public CompanhiaAerea getCompanhiaAerea() {
        return companhiaAerea;  // Método para acessar a companhia aérea
    }

        public LocalDateTime calcularHorarioChegada() {
        // Calcula a distância entre os aeroportos
        Aeroporto aeroportoOrigem = new Aeroporto();
        Aeroporto aeroportoDestino = new Aeroporto();

        double distancia = aeroportoOrigem.calcularDistanciaKm(aeroportoDestino); // Km
        double velocidadeMedia = aeronave.getVelocidadeMedia(); // km/h
        double tempoDeViagemHoras = distancia / velocidadeMedia; // Tempo de viagem em horas
        Duration duracaoViagem = Duration.ofHours((long) tempoDeViagemHoras);

        return horarioDecolagem.plus(duracaoViagem); // Calcula o horário de chegada
    }

    // Representação em string
    @Override
    public String toString() {
        return String.format(
                "Voo {Código: %s, Origem: %s, Destino: %s, Horário Decolagem: %s, Horário Chegada: %s, Valor Passagem: R$ %.2f}",
                codigo, origem, destino, horarioDecolagem, getHorarioChegada(), valorPassagem);
    }
}
