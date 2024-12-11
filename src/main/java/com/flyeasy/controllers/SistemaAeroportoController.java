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

    public List<Voo> listarVoos() {
        return new ArrayList<>(voos); // Retorna uma cópia da lista de voos
    }

    public List<Voo> buscarVoosDiretos(String origem, String destino, LocalDateTime data) {
        return voos.stream()
                .filter(v -> v.getOrigem().equalsIgnoreCase(origem) &&
                        v.getDestino().equalsIgnoreCase(destino) &&
                        v.getHorarioDecolagem().toLocalDate().equals(data.toLocalDate()))
                .collect(Collectors.toList());
    }

    public List<Voo> buscarVoosComConexao(String origem, String destino, LocalDateTime dataInicial) {
        System.out.println("Iniciando busca de voos com conexão...");
        List<Voo> voosConexao = new ArrayList<>();
    
        // Filtrar voos que partem da origem
        List<Voo> voosPartindoOrigem = listarVoos().stream()
                .filter(voo -> voo.getOrigem().equals(origem) && 
                               voo.getHorarioDecolagem().isAfter(dataInicial))
                .toList();
    
        // Filtrar voos que chegam ao destino
        List<Voo> voosChegandoDestino = listarVoos().stream()
                .filter(voo -> voo.getDestino().equals(destino))
                .toList();
    
        System.out.printf("Total de voos cadastrados no sistema: %d%n", listarVoos().size());
        System.out.printf("Voos partindo de %s: %d%n", origem, voosPartindoOrigem.size());
        System.out.printf("Voos chegando a %s: %d%n", destino, voosChegandoDestino.size());
    
        // Conectar voos partindo da origem com os que chegam ao destino
        for (Voo vooOrigem : voosPartindoOrigem) {
            for (Voo vooDestino : voosChegandoDestino) {
                // Verifica se o destino intermediário do primeiro voo é a origem do segundo voo
                // e se o horário de chegada do primeiro voo é antes do horário de decolagem do segundo
                LocalDateTime chegadaOrigem = vooOrigem.getHorarioDecolagem().plus(vooOrigem.getDuracao());
                if (vooOrigem.getDestino().equals(vooDestino.getOrigem()) && 
                    chegadaOrigem.isBefore(vooDestino.getHorarioDecolagem())) {
                    voosConexao.add(vooOrigem);
                    voosConexao.add(vooDestino);
                }
            }
        }
    
        System.out.printf("Total de voos com conexão encontrados: %d%n", voosConexao.size() / 2);
        return voosConexao;
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
                -46.6333 // Longitude fictícia (São Paulo)
        );

        Aeroporto aeroportoDestino = new Aeroporto(
                voo.getDestino(), // Nome
                "SIGLA-" + voo.getDestino(), // Sigla fictícia
                "Cidade " + voo.getDestino(), // Cidade fictícia
                "Estado", // Estado fictício
                "Brasil", // País fictício
                tipoVoo, // Tipo de voo
                38.7167, // Latitude fictícia (Lisboa)
                -9.1395 // Longitude fictícia (Lisboa)
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
                voo.getValorPassagem(), // Se tiver tarifas diferentes, ajuste aqui
                voo.getValorPassagem(), // Ajuste conforme a tarifa Business, Premium, etc.
                voo.getValorPassagem(), // Ajuste conforme a tarifa Business, Premium, etc.
                "BRL" // Considerando que a moeda seja em Reais (BRL)
        );

        // Criar o bilhete e retornar
        return new Bilhete(
                passageiro.getNome(),
                passageiro.getCpf(),
                passageiro.getEmail(),
                passageiro.getDocumento(),   
                passagemAerea,
                passageiro,
                voo);
    }

    // Método para determinar o tipo do voo com base na origem e destino
    private TipoVoo determinarTipoVoo(String origem, String destino) {
        // Para simplificar, estamos considerando que a origem e destino têm o mesmo
        // país se for doméstico
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

    public void cancelarPassagem(Bilhete bilhete) {
        if (bilhete == null) {
            System.out.println("Bilhete inválido.");
            return;
        }

        // Alterar o status do bilhete para 'Cancelado'
        bilhete.setStatus("Cancelado");
        System.out.println("Passagem cancelada com sucesso para o bilhete: " + bilhete.getCodigo());

        // Registrar no log de operações, se aplicável
        logOperacoes.add("Passagem cancelada: " + bilhete.getCodigo());
    }

    private List<String> logOperacoes = new ArrayList<>();

    public void exibirLogOperacoes() {
        for (String log : logOperacoes) {
            System.out.println(log);
        }
    }

    public void adicionarFranquiaBagagem(Bilhete bilhete, double custo) {
        bilhete.setFranquiaBagagem(custo);
    }    
}
