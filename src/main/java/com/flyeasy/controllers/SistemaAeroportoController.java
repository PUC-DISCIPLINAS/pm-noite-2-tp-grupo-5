package com.flyeasy.controllers;

import com.flyeasy.models.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.time.ZoneId;

public class SistemaAeroportoController {
    private List<Voo> voos;

    public SistemaAeroportoController() {
        this.voos = new ArrayList<>();
    }

    public void adicionarVoo(Voo voo) {
        voos.add(voo);
    }

    public List<Voo> buscarVoosDiretos(String origem, String destino, LocalDateTime data) {
        return voos.stream()
                .filter(v -> v.getOrigem().equalsIgnoreCase(origem) &&
                             v.getDestino().equalsIgnoreCase(destino) &&
                             v.getHorarioDecolagem().toLocalDate().equals(data.toLocalDate()))
                .collect(Collectors.toList());
    }

    public Bilhete comprarPassagem(Passageiro passageiro, Voo voo) {
        // Determinar o TipoVoo com base nos aeroportos de origem e destino
        TipoVoo tipoVoo = determinarTipoVoo(voo.getOrigem(), voo.getDestino());

        // Criar aeroportos de origem e destino com a latitude e longitude
        Aeroporto aeroportoOrigem = new Aeroporto(
                voo.getOrigem(), // Nome
                "SIGLA-" + voo.getOrigem(), // Sigla fictícia (ajuste conforme necessidade)
                "Cidade " + voo.getOrigem(), // Cidade fictícia
                "Estado", // Estado fictício
                "Brasil", // País fictício
                tipoVoo, // Tipo de voo
                -23.5505, // Latitude fictícia (São Paulo)
                -46.6333  // Longitude fictícia (São Paulo)
        );

        Aeroporto aeroportoDestino = new Aeroporto(
                voo.getDestino(), // Nome
                "SIGLA-" + voo.getDestino(), // Sigla fictícia
                "Cidade " + voo.getDestino(), // Cidade fictícia
                "Estado", // Estado fictício
                "Brasil", // País fictício
                tipoVoo, // Tipo de voo
                38.7167, // Latitude fictícia (Lisboa)
                -9.1395  // Longitude fictícia (Lisboa)
        );

        // Converter a data de decolagem do voo (LocalDateTime) para Date
        Date dataHoraVoo = Date.from(voo.getHorarioDecolagem().atZone(ZoneId.systemDefault()).toInstant());

        // Criar PassagemAerea com os dados necessários
        PassagemAerea passagemAerea = new PassagemAerea(
                aeroportoOrigem,
                aeroportoDestino,
                dataHoraVoo,
                voo.getCodigo(),
                voo.getCompanhiaAerea(), // Supondo que Voo tenha um método getCompanhiaAerea()
                voo.getValorPassagem(),  // Se tiver tarifas diferentes, ajuste aqui
                voo.getValorPassagem(),  // Ajuste conforme a tarifa Business, Premium, etc.
                voo.getValorPassagem(),  // Ajuste conforme a tarifa Business, Premium, etc.
                "BRL" // Considerando que a moeda seja em Reais (BRL)
        );

        // Criar o bilhete e retornar
        return new Bilhete(
                passageiro.getNome(),
                passageiro.getCpf(),
                passageiro.getEmail(),
                passagemAerea,
                passageiro,
                voo
        );
    }

    // Método para determinar o tipo do voo com base na origem e destino
    private TipoVoo determinarTipoVoo(String origem, String destino) {
        // Para simplificar, estamos considerando que a origem e destino têm o mesmo país se for doméstico
        if (origem.equalsIgnoreCase(destino)) {
            return TipoVoo.DOMESTICO;
        } else {
            return TipoVoo.INTERNACIONAL;
        }
    }

    // Método para cancelar o voo
    public void cancelarVoo(Voo voo) {
        if (voos.contains(voo)) {
            voos.remove(voo);
            System.out.println("Voo " + voo.getCodigo() + " cancelado com sucesso.");
        } else {
            System.out.println("Voo não encontrado.");
        }
    }

    // Método para realizar o check-in
    public void realizarCheckIn(Bilhete bilhete) {
        System.out.println("Check-in realizado para " + bilhete.getPassageiro().getNome());
    }

    // Método para exibir o cartão de embarque
    public void exibirCartaoEmbarque(Bilhete bilhete) {
        System.out.println("Cartão de Embarque: ");
        System.out.println("Passageiro: " + bilhete.getPassageiro().getNome());
        System.out.println("Voo: " + bilhete.getVoo().toString());
    }
}
