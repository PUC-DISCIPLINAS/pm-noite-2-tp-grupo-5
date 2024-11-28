import com.flyeasy.controllers.VooController;
import com.flyeasy.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class VooControllerTest {

    private VooController vooController;
    private Aeronave aeronave;

    @BeforeEach
    public void setUp() {
        vooController = new VooController();
        aeronave = new Aeronave("A320", 180, 20000.0, 30);  // Criando aeronave para os testes
    }

    @Test
    public void testCadastrarVoo() {
        // Preparando os aeroportos para o teste
        Aeroporto aeroportoOrigem = new Aeroporto("Aeroporto Internacional de São Paulo", "GRU", "São Paulo", "SP", "Brasil", TipoVoo.INTERNACIONAL);
        Aeroporto aeroportoDestino = new Aeroporto("Aeroporto Internacional de Lisboa", "LIS", "Lisboa", "Lisboa", "Portugal", TipoVoo.INTERNACIONAL);
        
        // Criando o horário de decolagem para o voo
        LocalDateTime horarioDecolagem = LocalDateTime.now().plusHours(2);
        Duration duracao = Duration.ofHours(10);
        double valorPassagem = 3500.0;

        // Cadastrando o voo
        vooController.cadastrarVoo("AD4114", aeroportoOrigem.getSigla(), aeroportoDestino.getSigla(), Arrays.asList(DiaSemana.SEGUNDA, DiaSemana.TERCA), aeronave, horarioDecolagem, duracao, valorPassagem);

        // Buscando o voo e validando os dados
        Voo voo = vooController.buscarVooPorCodigo("AD4114");
        assertNotNull(voo, "O voo cadastrado não foi encontrado.");
        assertEquals("AD4114", voo.getCodigo(), "O código do voo está incorreto.");
        assertEquals(aeroportoOrigem.getSigla(), voo.getOrigem(), "A origem do voo está incorreta.");
        assertEquals(aeroportoDestino.getSigla(), voo.getDestino(), "O destino do voo está incorreto.");
        assertEquals(aeronave, voo.getAeronave(), "A aeronave do voo está incorreta.");
        assertEquals(180, voo.getCapacidadePassageiros(), "A capacidade de passageiros da aeronave está incorreta.");
        assertEquals(horarioDecolagem, voo.getHorarioDecolagem(), "O horário de decolagem está incorreto.");
        assertEquals(duracao, voo.getDuracao(), "A duração do voo está incorreta.");
        assertEquals(valorPassagem, voo.getValorPassagem(), "O valor da passagem está incorreto.");
    }

    @Test
    public void testProgramarVoosAtivos() {
        // Criando o horário de decolagem para o voo
        LocalDateTime horarioDecolagem = LocalDateTime.now().plusHours(2);
        Duration duracao = Duration.ofHours(10);
        double valorPassagem = 3500.0;

        // Cadastrando o voo
        vooController.cadastrarVoo("AD4117", "BSB", "REC", Arrays.asList(DiaSemana.SEGUNDA, DiaSemana.QUARTA), aeronave, horarioDecolagem, duracao, valorPassagem);

        // Buscando os voos programados
        List<Voo> voosProgramados = vooController.programarVoosAtivos();

        // Validando a lista de voos programados
        assertNotNull(voosProgramados, "A lista de voos programados não deveria ser nula.");
        assertFalse(voosProgramados.isEmpty(), "Deveria haver voos programados.");
        for (Voo voo : voosProgramados) {
            assertEquals(aeronave, voo.getAeronave(), "A aeronave programada está incorreta.");
            assertEquals(180, voo.getCapacidadePassageiros(), "A capacidade de passageiros está incorreta.");
        }
    }

    @Test
    public void testAlteracaoSemCustoParaVIP() {
        // Criando passageiro VIP para o teste
        Passageiro passageiroVIP = new Passageiro("Ana VIP", "123.456.789-00", "vip@email.com", true);

        // Criando aeroportos e voos para o teste
        Aeroporto aeroportoOrigem = new Aeroporto("Aeroporto Internacional de São Paulo", "GRU", "São Paulo", "SP", "Brasil", TipoVoo.INTERNACIONAL);
        Aeroporto aeroportoDestino = new Aeroporto("Aeroporto Internacional de Lisboa", "LIS", "Lisboa", "Lisboa", "Portugal", TipoVoo.INTERNACIONAL);

        LocalDateTime horarioDecolagem = LocalDateTime.now().plusHours(2);
        Duration duracao = Duration.ofHours(10);
        double valorPassagem = 3500.0;

        Voo vooOriginal = new Voo("AD4114", "GRU", "LIS", Arrays.asList(DiaSemana.SEGUNDA), aeronave, horarioDecolagem, duracao, valorPassagem);

        Voo novoVoo = new Voo("AD5114", "GRU", "FCO", Arrays.asList(DiaSemana.TERCA), aeronave, LocalDateTime.now().plusDays(1), Duration.ofHours(11), 3800.0);

        // Criando passagem aérea
        PassagemAerea passagem = new PassagemAerea(aeroportoOrigem, aeroportoDestino, LocalDateTime.now(),
                "TP1020", new CompanhiaAerea("TAP Portugal", "TP", "TAP", "12345678000100", 100.0, 50.0),
                2000.0, valorPassagem, 5000.0, "BRL");

        // Cadastrando o voo original
        vooController.cadastrarVoo("AD4114", "GRU", "LIS", Arrays.asList(DiaSemana.SEGUNDA), aeronave, horarioDecolagem, duracao, valorPassagem);

        // Testando a alteração do voo sem custo para passageiro VIP
        boolean alteracaoBemSucedida = vooController.alterarVoo(passagem, passageiroVIP, novoVoo);

        assertTrue(alteracaoBemSucedida, "Passageiro VIP deveria alterar o voo sem custo.");
        assertEquals(novoVoo, passagem.getVoo(), "O voo associado à passagem deveria ser atualizado.");
    }

    @Test
    public void testAlteracaoComCustoParaNaoVIP() {
        // Criando passageiro não VIP
        Passageiro passageiroNaoVIP = new Passageiro("João Não VIP", "987.654.321-00", "naovip@email.com", false);

        // Criando aeroportos e voos para o teste
        Aeroporto aeroportoOrigem = new Aeroporto("Aeroporto Internacional de São Paulo", "GRU", "São Paulo", "SP", "Brasil", TipoVoo.INTERNACIONAL);
        Aeroporto aeroportoDestino = new Aeroporto("Aeroporto Internacional de Lisboa", "LIS", "Lisboa", "Lisboa", "Portugal", TipoVoo.INTERNACIONAL);

        LocalDateTime horarioDecolagem = LocalDateTime.now().plusHours(2);
        Duration duracao = Duration.ofHours(10);
        double valorPassagem = 3500.0;

        Voo vooOriginal = new Voo("AD4114", "GRU", "LIS", Arrays.asList(DiaSemana.SEGUNDA), aeronave, horarioDecolagem, duracao, valorPassagem);

        Voo novoVoo = new Voo("AD5114", "GRU", "FCO", Arrays.asList(DiaSemana.TERCA), aeronave, LocalDateTime.now().plusDays(1), Duration.ofHours(11), 3800.0);

        // Criando passagem aérea
        PassagemAerea passagem = new PassagemAerea(aeroportoOrigem, aeroportoDestino, LocalDateTime.now(),
                "TP1020", new CompanhiaAerea("TAP Portugal", "TP", "TAP", "12345678000100", 100.0, 50.0),
                2000.0, valorPassagem, 5000.0, "BRL");

        // Cadastrando o voo original
        vooController.cadastrarVoo("AD4114", "GRU", "LIS", Arrays.asList(DiaSemana.SEGUNDA), aeronave, horarioDecolagem, duracao, valorPassagem);

        // Testando a alteração do voo com custo para passageiro não VIP
        boolean alteracaoBemSucedida = vooController.alterarVoo(passagem, passageiroNaoVIP, novoVoo);

        assertTrue(alteracaoBemSucedida, "Passageiro não VIP deveria alterar o voo com custo.");
        assertEquals(novoVoo, passagem.getVoo(), "O voo associado à passagem deveria ser atualizado.");
    }
}
