import com.flyeasy.models.*;
import com.flyeasy.controllers.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class MainTest {

    public static void main(String[] args) {
        // 1. Cadastro Básico - Criar duas companhias aéreas com 3 voos cada,
        cadastroBasico();

        // 2. Cenário 1 - Buscar voos diretos, comprar passagem, check-in e embarque
        cenariosVoosDiretos();

        // 3. Cenário 2 - Buscar voos com conexão, compra passagem para passageiro VIP, e cancelamento do voo
        cenariosVoosComConexao();
    }

    private static void cadastroBasico() {

        CompanhiaAerea companhia1 = new CompanhiaAerea("TAP Portugal", "TP", "TAP", "12345678000100", 100.0, 50.0);
        CompanhiaAerea companhia2 = new CompanhiaAerea("LATAM", "LA", "LATAM", "98765432000100", 120.0, 60.0);

        Aeronave aeronave = new Aeronave("A320", 180, 20000.0, 30, 100.0);

        Aeroporto aeroportoOrigem = new Aeroporto("Aeroporto Internacional de São Paulo", "GRU", "São Paulo", "SP", "Brasil", TipoVoo.INTERNACIONAL, -23.5505, -46.6333);
        Aeroporto aeroportoDestino = new Aeroporto("Aeroporto Internacional de Lisboa", "LIS", "Lisboa", "Lisboa", "Portugal", TipoVoo.INTERNACIONAL, 38.7167, -9.1395);

        Voo voo1 = new Voo("TP1020", "GRU", "LIS", Arrays.asList(DiaSemana.SEGUNDA), aeronave, LocalDateTime.now().plusHours(2), Duration.ofHours(10), 3500.0, companhia1);
        Voo voo2 = new Voo("TP1021", "GRU", "LIS", Arrays.asList(DiaSemana.SEGUNDA), aeronave, LocalDateTime.now().plusHours(3), Duration.ofHours(12), 4000.0, companhia1);
        Voo voo3 = new Voo("TP1022", "GRU", "LIS", Arrays.asList(DiaSemana.SEGUNDA), aeronave, LocalDateTime.now().plusHours(4), Duration.ofHours(8), 3500.0, companhia1);

        Voo voo4 = new Voo("LA1020", "GRU", "LIS", Arrays.asList(DiaSemana.SEGUNDA), aeronave, LocalDateTime.now().plusHours(5), Duration.ofHours(9), 3500.0, companhia2);
        Voo voo5 = new Voo("LA1021", "GRU", "LIS", Arrays.asList(DiaSemana.SEGUNDA), aeronave, LocalDateTime.now().plusHours(6), Duration.ofHours(11), 4000.0, companhia2);
        Voo voo6 = new Voo("LA1022", "GRU", "LIS", Arrays.asList(DiaSemana.SEGUNDA), aeronave, LocalDateTime.now().plusHours(7), Duration.ofHours(10), 3500.0, companhia2);

        SistemaAeroportoController sistemaAeroportoController = new SistemaAeroportoController();

        sistemaAeroportoController.adicionarVoo(voo1);
        sistemaAeroportoController.adicionarVoo(voo2);
        sistemaAeroportoController.adicionarVoo(voo3);
        sistemaAeroportoController.adicionarVoo(voo4);
        sistemaAeroportoController.adicionarVoo(voo5);
        sistemaAeroportoController.adicionarVoo(voo6);

        System.out.println("Cadastro Básico realizado com sucesso!");
    }

    private static void cenariosVoosDiretos() {

        Passageiro passageiro = new Passageiro("João Silva", "123.456.789-00", "joao@email.com", false, "123456789");

        SistemaAeroportoController sistemaAeroportoController = new SistemaAeroportoController();
        List<Voo> voos = sistemaAeroportoController.buscarVoosDiretos("GRU", "LIS", LocalDateTime.now());

        for (Voo voo : voos) {
            System.out.println(voo);
        }

        Bilhete bilhete = sistemaAeroportoController.comprarPassagem(passageiro, voos.get(0));

        sistemaAeroportoController.realizarCheckIn(bilhete);
        sistemaAeroportoController.exibirCartaoEmbarque(bilhete);

        System.out.println("Cenário 1 - Compra de passagem e check-in concluídos com sucesso!");
    }

    private static void cenariosVoosComConexao() {

        Passageiro passageiroVIP = new Passageiro("Ana VIP", "123.456.789-00", "vip@email.com", true, "123456789");

        SistemaAeroportoController sistemaAeroportoController = new SistemaAeroportoController();
        List<Voo> voosConectados = sistemaAeroportoController.buscarVoosDiretos("GRU", "LIS", LocalDateTime.now());

        Bilhete bilhete = sistemaAeroportoController.comprarPassagem(passageiroVIP, voosConectados.get(0));

        sistemaAeroportoController.exibirCartaoEmbarque(bilhete);

        sistemaAeroportoController.cancelarVoo(voosConectados.get(0));

        System.out.println("Cenário 2 - Compra de passagem VIP, custo de bagagem e cancelamento concluídos com sucesso!");
    }
}
