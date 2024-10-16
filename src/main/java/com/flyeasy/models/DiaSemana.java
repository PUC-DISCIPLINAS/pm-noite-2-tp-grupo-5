package com.flyeasy.models;

import java.time.DayOfWeek;

public enum DiaSemana {
    SEGUNDA, TERCA, QUARTA, QUINTA, SEXTA, SABADO, DOMINGO;

    // Método que converte DiaSemana para DayOfWeek
    public DayOfWeek toDayOfWeek() {
        switch (this) {
            case SEGUNDA:
                return DayOfWeek.MONDAY;
            case TERCA:
                return DayOfWeek.TUESDAY;
            case QUARTA:
                return DayOfWeek.WEDNESDAY;
            case QUINTA:
                return DayOfWeek.THURSDAY;
            case SEXTA:
                return DayOfWeek.FRIDAY;
            case SABADO:
                return DayOfWeek.SATURDAY;
            case DOMINGO:
                return DayOfWeek.SUNDAY;
            default:
                throw new IllegalArgumentException("Dia da semana inválido");
        }
    }
}
