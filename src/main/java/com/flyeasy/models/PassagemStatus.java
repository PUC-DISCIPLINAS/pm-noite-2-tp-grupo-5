package com.flyeasy.models;

public class PassagemStatus {

    public enum Status {
        ADQUIRIDA,
        CANCELADA,
        CHECK_IN_REALIZADO,
        EMBARQUE_REALIZADO,
        NO_SHOW
    }

    private Status statusAtual;

    public PassagemStatus() {
        this.statusAtual = Status.ADQUIRIDA;
    }

    public Status getStatusAtual() {
        return statusAtual;
    }

    public void realizarCheckIn() {
        if (statusAtual == Status.ADQUIRIDA) {
            statusAtual = Status.CHECK_IN_REALIZADO;
        }
    }

    public void realizarEmbarque() {
        if (statusAtual == Status.CHECK_IN_REALIZADO) {
            statusAtual = Status.EMBARQUE_REALIZADO;
        }
    }

    public void registrarNoShow() {
        if (statusAtual == Status.CHECK_IN_REALIZADO) {
            statusAtual = Status.NO_SHOW;
        }
    }

    public void cancelar() {
        if (statusAtual == Status.ADQUIRIDA) {
            statusAtual = Status.CANCELADA;
        }
    }
}
