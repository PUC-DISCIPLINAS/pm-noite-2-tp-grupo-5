import com.flyeasy.models.*;
import com.flyeasy.controllers.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class MainTest {

    public static void main(String[] args) {

        SistemaAeroportoController sistemaAeroportoController = new SistemaAeroportoController();

        // 1. Cadastro Básico - Criar duas companhias aéreas com 3 voos cada
        cadastroBasico(sistemaAeroportoController);

        // 2. Cenário 1 - Buscar voos diretos, comprar passagem, check-in e embarque
        cenariosVoosDiretos(sistemaAeroportoController);

        // 3. Cenário 2 - Buscar voos com conexão, compra passagem para passageiro VIP, e cancelamento do voo
        cenariosVoosComConexao(sistemaAeroportoController);
    }

    private static void cadastroBasico(SistemaAeroportoController sistemaAeroportoController) {

        CompanhiaAerea companhia1 = new CompanhiaAerea("TAP Portugal", "TP", "TAP", "12345678000100", 100.0, 50.0);
        CompanhiaAerea companhia2 = new CompanhiaAerea("LATAM", "LA", "LATAM", "98765432000100", 120.0, 60.0);

        Aeronave aeronave = new Aeronave("A320", 180, 20000.0, 30, 100.0);

        Aeroporto aeroportoOrigem = new Aeroporto("Aeroporto Internacional de São Paulo", "GRU", "São Paulo", "SP", "Brasil", TipoVoo.INTERNACIONAL, -23.5505, -46.6333);
        Aeroporto aeroportoDestino = new Aeroporto("Aeroporto Internacional de Lisboa", "LIS", "Lisboa", "Lisboa", "Portugal", TipoVoo.INTERNACIONAL, 38.7167, -9.1395);
        Aeroporto aeroportoConexao = new Aeroporto("Aeroporto Internacional de Miami", "MIA", "Miami", "FL", "EUA", TipoVoo.INTERNACIONAL, 25.7959, -80.2871);

        Voo voo1 = new Voo("TP1020", "GRU", "LIS", Arrays.asList(DiaSemana.SEGUNDA), aeronave, LocalDateTime.now().plusHours(2), Duration.ofHours(10), 3500.0, companhia1);
        Voo voo2 = new Voo("TP1020", "GRU", "LIS", Arrays.asList(DiaSemana.TERCA), aeronave, LocalDateTime.now().plusHours(2), Duration.ofHours(10), 4000.0, companhia1);
        Voo voo3 = new Voo("TP1021", "GRU", "LIS", Arrays.asList(DiaSemana.QUARTA), aeronave, LocalDateTime.now().plusHours(2), Duration.ofHours(10), 4500.0, companhia1);
        Voo voo4 = new Voo("TP1040", "GRU", "LIS", Arrays.asList(DiaSemana.QUINTA), aeronave, LocalDateTime.now().plusHours(2), Duration.ofHours(10), 5500.0, companhia1);
        Voo voo5 = new Voo("TP1030", "MAD", "MIA", Arrays.asList(DiaSemana.SEGUNDA), aeronave, LocalDateTime.now().plusHours(5), Duration.ofHours(8), 3200.0, companhia1);
        Voo voo6 = new Voo("TP1050", "MAD", "MIA", Arrays.asList(DiaSemana.TERCA), aeronave, LocalDateTime.now().plusHours(9), Duration.ofHours(11), 4500.0, companhia2);
        Voo voo7 = new Voo("TP1060", "MAD", "MIA", Arrays.asList(DiaSemana.QUARTA), aeronave, LocalDateTime.now().plusHours(11), Duration.ofHours(2), 1500.0, companhia2);
        Voo voo8 = new Voo("TP1060", "MAD", "MIA", Arrays.asList(DiaSemana.QUINTA), aeronave, LocalDateTime.now().plusHours(11), Duration.ofHours(2), 1500.0, companhia2);        

        Voo vooConexao1 = new Voo("TP2000", "GRU", "MIA", Arrays.asList(DiaSemana.SEGUNDA), aeronave, LocalDateTime.now().plusHours(5), Duration.ofHours(8), 3000.0, companhia1);
        Voo vooConexao2 = new Voo("TP2001", "MIA", "LIS", Arrays.asList(DiaSemana.SEGUNDA), aeronave, LocalDateTime.now().plusHours(15), Duration.ofHours(7), 4000.0, companhia1);
        Voo vooConexao3 = new Voo("TP2002", "GRU", "MAD", Arrays.asList(DiaSemana.TERCA), aeronave, LocalDateTime.now().plusHours(6), Duration.ofHours(11), 4200.0, companhia1);
        Voo vooConexao4 = new Voo("TP2003", "MAD", "LIS", Arrays.asList(DiaSemana.TERCA), aeronave, LocalDateTime.now().plusHours(18), Duration.ofHours(2), 1800.0, companhia1);
        Voo vooConexao5 = new Voo("TP2004", "GRU", "CDG", Arrays.asList(DiaSemana.QUARTA), aeronave, LocalDateTime.now().plusHours(7), Duration.ofHours(12), 4600.0, companhia2);
        Voo vooConexao6 = new Voo("TP2005", "CDG", "LIS", Arrays.asList(DiaSemana.QUARTA), aeronave, LocalDateTime.now().plusHours(20), Duration.ofHours(3), 2000.0, companhia2);        

        sistemaAeroportoController.adicionarVoo(voo1);
        sistemaAeroportoController.adicionarVoo(voo2);
        sistemaAeroportoController.adicionarVoo(vooConexao1);
        sistemaAeroportoController.adicionarVoo(vooConexao2);

        gerarVoosFuturos(sistemaAeroportoController);

        System.out.println("Cadastro Básico realizado com sucesso!");
    }

    private static void gerarVoosFuturos(SistemaAeroportoController sistemaAeroportoController) {
        LocalDateTime dataInicial = LocalDateTime.now();
    
        List<Voo> voosOriginais = sistemaAeroportoController.listarVoos();
    
        for (int i = 1; i <= 10; i++) {
            LocalDateTime dataVoo = dataInicial.plusDays(i);
            for (Voo voo : voosOriginais) {

                String novoCodigo = voo.getCodigo() + "_DIA" + i;
    
                double novoValorPassagem = voo.getValorPassagem() + (i * 50);
    
                Voo vooFuturo = new Voo(
                    novoCodigo, 
                    voo.getOrigem(), 
                    voo.getDestino(), 
                    voo.getDiasSemana(), 
                    voo.getAeronave(), 
                    dataVoo, 
                    voo.getDuracao(), 
                    novoValorPassagem, 
                    voo.getCompanhiaAerea()
                );
    
                sistemaAeroportoController.adicionarVoo(vooFuturo);
            }
        }
    
        System.out.println("Voos futuros gerados até 10 dias a partir da data de execução com informações diferenciadas.");
    }     

    private static void cenariosVoosDiretos(SistemaAeroportoController sistemaAeroportoController) {

        Passageiro passageiro = new Passageiro("João Silva", "123.456.789-00", "joao@email.com", false, "123456789");

        System.out.println("Buscando voos diretos de GRU para LIS na data atual...");
        List<Voo> voos = sistemaAeroportoController.buscarVoosDiretos("GRU", "LIS", LocalDateTime.now());

        if (voos.isEmpty()) {
            System.out.println("Nenhum voo direto encontrado.");
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
        System.out.printf("Selecionando o voo %s para compra...%n", vooSelecionado.getCodigo());

        Bilhete bilhete = sistemaAeroportoController.comprarPassagem(passageiro, vooSelecionado);
        System.out.println("Passagem comprada com sucesso!");

        System.out.println("Realizando check-in para o voo...");
        sistemaAeroportoController.realizarCheckIn(bilhete);

        System.out.println("Registrando embarque no voo...");
        sistemaAeroportoController.exibirCartaoEmbarque(bilhete);

        System.out.println("\nCenário 1 concluído com sucesso!");
    }

    private static void cenariosVoosComConexao(SistemaAeroportoController sistemaAeroportoController) {
        Passageiro passageiroVIP = new Passageiro("Ana VIP", "123.456.789-00", "vip@email.com", true, "123456789");
    
        System.out.println("Buscando voos com conexão de GRU para LIS na data atual...");
        List<Voo> voosConectados = sistemaAeroportoController.buscarVoosComConexao("GRU", "LIS", LocalDateTime.now());
    
        if (voosConectados.isEmpty()) {
            System.out.println("Nenhum voo com conexão encontrado.");
            return;
        }
    
        System.out.println("Voos com conexão encontrados:");
        for (Voo voo : voosConectados) {
            System.out.printf("Voo: %s | Origem: %s | Destino: %s | Decolagem: %s | Duração: %s | Valor: R$ %.2f%n",
                    voo.getCodigo(),
                    voo.getOrigem(),
                    voo.getDestino(),
                    voo.getHorarioDecolagem(),
                    voo.getDuracao(),
                    voo.getValorPassagem());
        }
    
        Voo vooSelecionado = voosConectados.get(0);
        Bilhete bilhete = sistemaAeroportoController.comprarPassagem(passageiroVIP, vooSelecionado);
    
        sistemaAeroportoController.adicionarFranquiaBagagem(bilhete, 0);
    
        System.out.println("\nDetalhamento dos custos da passagem:");
        System.out.printf("Valor do voo: R$ %.2f%n", bilhete.getVoo().getValorPassagem());
        System.out.printf("Taxa de bagagem: R$ %.2f%n", bilhete.getFranquiaBagagem());
        System.out.printf("Total: R$ %.2f%n", bilhete.getVoo().getValorPassagem() + bilhete.getFranquiaBagagem());
    
        sistemaAeroportoController.exibirCartaoEmbarque(bilhete);
    
        System.out.println("\nCancelando o voo...");
        sistemaAeroportoController.cancelarVoo(vooSelecionado);
        sistemaAeroportoController.cancelarPassagem(bilhete);
    
        System.out.println("\nCenário 2 concluído com sucesso!");
    }
}
