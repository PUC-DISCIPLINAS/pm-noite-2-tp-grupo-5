import com.flyeasy.models.*;
import com.flyeasy.controllers.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainTest {

    private static final String LOG_DIR = "storage";
    private static final String LOG_FILE = LOG_DIR + "/log.txt";

    public static void main(String[] args) {

        // Cria a pasta de logs se não existir
        criarPastaLog();

        SistemaAeroportoController sistemaAeroportoController = new SistemaAeroportoController();

        logToFile("Iniciando o sistema de aeroportos...");
        
        // 1. Cadastro Básico - Criar duas companhias aéreas com 3 voos cada
        logToFile("Cadastro Básico iniciado.");
        cadastroBasico(sistemaAeroportoController);
        logToFile("Cadastro Básico concluído com sucesso.");

        // 2. Cenário 1 - Buscar voos diretos, comprar passagem, check-in e embarque
        logToFile("Cenário 1 - Voos Diretos iniciado.");
        cenariosVoosDiretos(sistemaAeroportoController);
        logToFile("Cenário 1 concluído com sucesso.");

        // 3. Cenário 2 - Buscar voos com conexão, compra passagem para passageiro VIP, e cancelamento do voo
        logToFile("Cenário 2 - Voos com Conexão iniciado.");
        cenariosVoosComConexao(sistemaAeroportoController);
        logToFile("Cenário 2 concluído com sucesso.");
    }

    private static void criarPastaLog() {
        File dir = new File(LOG_DIR);
        if (!dir.exists()) {
            if (dir.mkdir()) {
                System.out.println("Pasta 'storage' criada com sucesso.");
            } else {
                System.err.println("Erro ao criar a pasta 'storage'. Verifique as permissões do sistema.");
            }
        }
    }

    private static void logToFile(String message) {
        try (FileWriter fw = new FileWriter(LOG_FILE, true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println(LocalDateTime.now() + " - " + message);
        } catch (IOException e) {
            System.err.println("Erro ao gravar no arquivo de log: " + e.getMessage());
        }
    }

    private static void cadastroBasico(SistemaAeroportoController sistemaAeroportoController) {
        CompanhiaAerea companhia1 = new CompanhiaAerea("TAP Portugal", "TP", "TAP", "12345678000100", 100.0, 50.0);
        CompanhiaAerea companhia2 = new CompanhiaAerea("LATAM", "LA", "LATAM", "98765432000100", 120.0, 60.0);

        Aeronave aeronave = new Aeronave("A320", 180, 20000.0, 30, 100.0);

        Aeroporto aeroportoOrigem = new Aeroporto("Aeroporto Internacional de São Paulo", "GRU", "São Paulo", "SP", "Brasil", TipoVoo.INTERNACIONAL, -23.5505, -46.6333);
        Aeroporto aeroportoDestino = new Aeroporto("Aeroporto Internacional de Lisboa", "LIS", "Lisboa", "Lisboa", "Portugal", TipoVoo.INTERNACIONAL, 38.7167, -9.1395);
        Aeroporto aeroportoConexao = new Aeroporto("Aeroporto Internacional de Miami", "MIA", "Miami", "FL", "EUA", TipoVoo.INTERNACIONAL, 25.7959, -80.2871);

        Voo voo1 = new Voo("TP1020", "GRU", "LIS", Arrays.asList(DiaSemana.SEGUNDA), aeronave, LocalDateTime.now().plusHours(2), Duration.ofHours(10), 3500.0, companhia1);
        Voo voo2 = new Voo("TP1021", "GRU", "LIS", Arrays.asList(DiaSemana.TERCA), aeronave, LocalDateTime.now().plusHours(3), Duration.ofHours(12), 4000.0, companhia1);
        Voo voo3 = new Voo("TP1022", "GRU", "LIS", Arrays.asList(DiaSemana.QUARTA), aeronave, LocalDateTime.now().plusHours(2), Duration.ofHours(10), 5500.0, companhia1);
        Voo voo4 = new Voo("TP1023", "GRU", "LIS", Arrays.asList(DiaSemana.QUINTA), aeronave, LocalDateTime.now().plusHours(3), Duration.ofHours(12), 5000.0, companhia1);

        Voo vooConexao1 = new Voo("TP2000", "GRU", "MIA", Arrays.asList(DiaSemana.SEGUNDA), aeronave, LocalDateTime.now().plusHours(5), Duration.ofHours(8), 3000.0, companhia1);  
        Voo vooConexao2 = new Voo("TP2001", "MIA", "LIS", Arrays.asList(DiaSemana.SEGUNDA), aeronave, LocalDateTime.now().plusHours(15), Duration.ofHours(7), 4000.0, companhia1);
    
        sistemaAeroportoController.adicionarVoo(voo1);
        sistemaAeroportoController.adicionarVoo(voo2);
        sistemaAeroportoController.adicionarVoo(voo3);
        sistemaAeroportoController.adicionarVoo(voo4);
        sistemaAeroportoController.adicionarVoo(vooConexao1);
        sistemaAeroportoController.adicionarVoo(vooConexao2);

        gerarVoosFuturos(sistemaAeroportoController);

        logToFile("Cadastro de voos básico realizado com sucesso.");
    }

    private static void gerarVoosFuturos(SistemaAeroportoController sistemaAeroportoController) {
        LocalDateTime dataInicial = LocalDateTime.now();
        Set<String> voosGerados = new HashSet<>();
        
        List<Voo> voosOriginais = sistemaAeroportoController.listarVoos();

        for (int i = 1; i <= 3; i++) {
            LocalDateTime dataVoo = dataInicial.plusDays(i);
            for (Voo voo : voosOriginais) {
                String novoCodigo = voo.getCodigo() + "_DIA" + i;

                if (voosGerados.contains(novoCodigo)) continue;

                voosGerados.add(novoCodigo);
                Voo vooFuturo = new Voo(
                    novoCodigo, 
                    voo.getOrigem(), 
                    voo.getDestino(), 
                    voo.getDiasSemana(), 
                    voo.getAeronave(), 
                    dataVoo, 
                    voo.getDuracao(), 
                    voo.getValorPassagem() + (i * 50),
                    voo.getCompanhiaAerea()
                );

                sistemaAeroportoController.adicionarVoo(vooFuturo);
            }
        }
        logToFile("Voos futuros gerados com controle para evitar duplicações.");
    }

    private static void cenariosVoosDiretos(SistemaAeroportoController sistemaAeroportoController) {
        logToFile("Iniciando busca de voos diretos de GRU para LIS.");

        Passageiro passageiro = new Passageiro("João Silva", "123.456.789-00", "joao@email.com", false, "123456789");

        System.out.println("Buscando voos diretos de GRU para LIS na data atual...");
        List<Voo> voos = sistemaAeroportoController.buscarVoosDiretos("GRU", "LIS", LocalDateTime.now());

        if (voos.isEmpty()) {
            System.out.println("Nenhum voo direto encontrado.");
            logToFile("Nenhum voo direto encontrado.");
            return;
        }

        System.out.println("Voos encontrados:");
        for (Voo voo : voos) {
            LocalDateTime horarioChegada = voo.getHorarioDecolagem().plus(voo.getDuracao());
            System.out.printf("Voo: %s | Decolagem: %s | Duração: %s | Chegada: %s | Valor: R$ %.2f%n",
                    voo.getCodigo(),
                    voo.getHorarioDecolagem(),
                    voo.getDuracao(),
                    horarioChegada,
                    voo.getValorPassagem());
        }

        Voo vooSelecionado = voos.get(0);
        logToFile("Selecionando o voo " + vooSelecionado.getCodigo() + " para compra.");

        Bilhete bilhete = sistemaAeroportoController.comprarPassagem(passageiro, vooSelecionado);
        logToFile("Passagem comprada com sucesso.");

        System.out.println("Realizando check-in para o voo...");
        sistemaAeroportoController.realizarCheckIn(bilhete);

        System.out.println("Registrando embarque no voo...");
        sistemaAeroportoController.exibirCartaoEmbarque(bilhete);

        logToFile("Cenário 1 concluído com sucesso.");
    }

    private static void cenariosVoosComConexao(SistemaAeroportoController sistemaAeroportoController) {
        logToFile("Iniciando busca de voos com conexão de GRU para LIS.");

        Passageiro passageiroVIP = new Passageiro("Ana VIP", "123.456.789-00", "vip@email.com", true, "123456789");

        System.out.println("Buscando voos com conexão de GRU para LIS na data atual...");
        List<Voo> voosConectados = sistemaAeroportoController.buscarVoosComConexao("GRU", "LIS", LocalDateTime.now());

        if (voosConectados.isEmpty()) {
            System.out.println("Nenhum voo com conexão encontrado.");
            logToFile("Nenhum voo com conexão encontrado.");
            return;
        }

        System.out.println("Voos com conexão encontrados:");
        for (int i = 0; i < voosConectados.size(); i += 2) {
            Voo vooOrigem = voosConectados.get(i);
            Voo vooDestino = voosConectados.get(i + 1);
            System.out.printf("Conexão: %s -> %s -> %s | Valor Total: R$ %.2f%n",
                    vooOrigem.getOrigem(), vooOrigem.getDestino(), vooDestino.getDestino(),
                    vooOrigem.getValorPassagem() + vooDestino.getValorPassagem());
        }

        Voo vooSelecionadoOrigem = voosConectados.get(0);
        Voo vooSelecionadoDestino = voosConectados.get(1);

        Bilhete bilhete = sistemaAeroportoController.comprarPassagem(passageiroVIP, vooSelecionadoOrigem);
        sistemaAeroportoController.comprarPassagem(passageiroVIP, vooSelecionadoDestino);

        logToFile("Voos com conexão selecionados e passagens compradas com sucesso.");

        System.out.println("\nDetalhamento dos custos da passagem:");
        System.out.printf("Valor do primeiro voo: R$ %.2f%n", vooSelecionadoOrigem.getValorPassagem());
        System.out.printf("Valor do segundo voo: R$ %.2f%n", vooSelecionadoDestino.getValorPassagem());
        System.out.printf("Total: R$ %.2f%n",
                vooSelecionadoOrigem.getValorPassagem() + vooSelecionadoDestino.getValorPassagem());

        sistemaAeroportoController.exibirCartaoEmbarque(bilhete);

        System.out.println("\nCancelando o voo...");
        sistemaAeroportoController.cancelarVoo(vooSelecionadoOrigem);
        sistemaAeroportoController.cancelarVoo(vooSelecionadoDestino);
        sistemaAeroportoController.cancelarPassagem(bilhete);

        logToFile("Cenário 2 concluído com sucesso.");
    }
}
