package com.flyeasy.controllers;

import com.flyeasy.models.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class PassagemController {
    private static List<PassagemAerea> passagens = new ArrayList<>();

    public static void inicializarPassagens() {
        Aeroporto aeroportoOrigem = new Aeroporto();
        Aeroporto aeroportoDestino = new Aeroporto();
        CompanhiaAerea companhia = new CompanhiaAerea("Latam", "LA", "Latam Airlines", "12345678000101", 100.0, 50.0);

        PassagemAerea passagem1 = new PassagemAerea(aeroportoOrigem, aeroportoDestino, new Date(), "LA123", companhia, 200.0, 300.0, 500.0, "BRL");
        passagens.add(passagem1);
    }

    public static List<PassagemAerea> buscarPassagensPorOrigem(String origem) {
        return passagens.stream()
                .filter(p -> p.getAeroportoOrigem().getNome().equals(origem))
                .collect(Collectors.toList());
    }
}
