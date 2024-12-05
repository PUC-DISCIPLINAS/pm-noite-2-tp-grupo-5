import com.flyeasy.models.*;
import com.flyeasy.controllers.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.List;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class SistemaAeroportoControllerTest {

    private SistemaAeroportoController sistemaAeroportoController;
    private Aeronave aeronave;

    @BeforeEach
    public void setUp() {
        sistemaAeroportoController = new SistemaAeroportoController();
        aeronave = new Aeronave("A320", 180, 20000.0, 30, 100.00);  // Criando aeronave para os testes
    }

@Test
public void testVoosComConexao() {
    // Criando os aeroportos de origem e destino
    Aeroporto aeroportoOrigem = new Aeroporto("Aeroporto Internacional de São Paulo", "GRU", "São Paulo", "SP", "Brasil", TipoVoo.INTERNACIONAL, -23.5505, -46.6333);
    Aeroporto aeroportoEscala = new Aeroporto("Aeroporto de Madrid", "MAD", "Madrid", "Madrid", "Espanha", TipoVoo.INTERNACIONAL, 40.4168, -3.7038); // Aeroporto intermediário
    Aeroporto aeroportoDestino = new Aeroporto("Aeroporto Internacional de Lisboa", "LIS", "Lisboa", "Lisboa", "Portugal", TipoVoo.INTERNACIONAL, 38.7167, -9.1395);

    // Criando a companhia aérea
    CompanhiaAerea companhiaAerea = new CompanhiaAerea("TAP Portugal", "TP", "TAP", "12345678000100", 100.0, 50.0);

    // Criando os voos
    LocalDateTime horarioDecolagem1 = LocalDateTime.now().plusHours(2); // Primeiro voo
    Duration duracao1 = Duration.ofHours(10); // Duracao do primeiro voo
    Voo voo1 = new Voo("TP1020", "GRU", "MAD", Arrays.asList(DiaSemana.SEGUNDA), aeronave, horarioDecolagem1, duracao1, 3500.0, companhiaAerea);

    LocalDateTime horarioDecolagem2 = horarioDecolagem1.plusHours(5); // Segundo voo após 5 horas (escala)
    Duration duracao2 = Duration.ofHours(2); // Duracao do segundo voo
    Voo voo2 = new Voo("TP1021", "MAD", "LIS", Arrays.asList(DiaSemana.SEGUNDA), aeronave, horarioDecolagem2, duracao2, 1500.0, companhiaAerea);

    // Adicionando os voos ao sistema
    sistemaAeroportoController.adicionarVoo(voo1);
    sistemaAeroportoController.adicionarVoo(voo2);

    // Buscando voos com conexão (escala) entre GRU e LIS
    List<Voo> voosConectados = sistemaAeroportoController.buscarVoosDiretos("GRU", "LIS", LocalDateTime.now());

    // Exibindo os voos encontrados
    assertNotNull(voosConectados, "A lista de voos conectados não pode ser nula.");
    assertEquals(2, voosConectados.size(), "Deveriam ser encontrados 2 voos.");
    assertEquals("TP1020", voosConectados.get(0).getCodigo(), "O primeiro voo não está correto.");
    assertEquals("TP1021", voosConectados.get(1).getCodigo(), "O segundo voo não está correto.");
}


@Test
public void testCompraPassagemVIP() {
    // Criando um passageiro VIP
    Passageiro passageiroVIP = new Passageiro("Ana VIP", "123.456.789-00", "vip@email.com", true, "123456789");

    // Criando os voos de conexão (do passo anterior)
    Aeroporto aeroportoOrigem = new Aeroporto("Aeroporto Internacional de São Paulo", "GRU", "São Paulo", "SP", "Brasil", TipoVoo.INTERNACIONAL, -23.5505, -46.6333);
    Aeroporto aeroportoDestino = new Aeroporto("Aeroporto Internacional de Lisboa", "LIS", "Lisboa", "Lisboa", "Portugal", TipoVoo.INTERNACIONAL, 38.7167, -9.1395);
    CompanhiaAerea companhiaAerea = new CompanhiaAerea("TAP Portugal", "TP", "TAP", "12345678000100", 100.0, 50.0);
    Voo voo = new Voo("TP1020", "GRU", "LIS", Arrays.asList(DiaSemana.SEGUNDA), aeronave, LocalDateTime.now().plusHours(2), Duration.ofHours(10), 3500.0, companhiaAerea);

    // Gerando bilhete para o passageiro VIP
    Bilhete bilhete = sistemaAeroportoController.comprarPassagem(passageiroVIP, voo);

    // Exibindo detalhes da passagem
    sistemaAeroportoController.exibirCartaoEmbarque(bilhete);
}


@Test
public void testCancelamentoVoo() {
    // Criando um passageiro VIP
    Passageiro passageiroVIP = new Passageiro("Ana VIP", "123.456.789-00", "vip@email.com", true, "123456789");

    // Criando os voos de conexão
    Aeroporto aeroportoOrigem = new Aeroporto("Aeroporto Internacional de São Paulo", "GRU", "São Paulo", "SP", "Brasil", TipoVoo.INTERNACIONAL, -23.5505, -46.6333);
    Aeroporto aeroportoDestino = new Aeroporto("Aeroporto Internacional de Lisboa", "LIS", "Lisboa", "Lisboa", "Portugal", TipoVoo.INTERNACIONAL, 38.7167, -9.1395);
    CompanhiaAerea companhiaAerea = new CompanhiaAerea("TAP Portugal", "TP", "TAP", "12345678000100", 100.0, 50.0);
    Voo voo = new Voo("TP1020", "GRU", "LIS", Arrays.asList(DiaSemana.SEGUNDA), aeronave, LocalDateTime.now().plusHours(2), Duration.ofHours(10), 3500.0, companhiaAerea);

    // Gerando bilhete para o passageiro VIP
    Bilhete bilhete = sistemaAeroportoController.comprarPassagem(passageiroVIP, voo);

    // Cancelando o voo
    sistemaAeroportoController.cancelarVoo(voo);

    // Verificando se a passagem foi cancelada
}


// @Test
// public void testLogOperacoes() {
//     // Realizando várias operações
//     Aeroporto aeroportoOrigem = new Aeroporto("Aeroporto Internacional de São Paulo", "GRU", "São Paulo", "SP", "Brasil", TipoVoo.INTERNACIONAL, -23.5505, -46.6333);
//     Aeroporto aeroportoDestino = new Aeroporto("Aeroporto Internacional de Lisboa", "LIS", "Lisboa", "Lisboa", "Portugal", TipoVoo.INTERNACIONAL, 38.7167, -9.1395);

//     // Criando e registrando log de operações
//     LogController.registrarLog("Voo cadastrado: TP1020");

//     // Cancelando voo e registrando log
//     LogController.registrarLog("Voo TP1020 cancelado.");

//     // Exibindo o log
//     LogController.exibirLog();
// }

// }
}