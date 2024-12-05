import com.flyeasy.controllers.VooController;
import com.flyeasy.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Duration;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.*;

import static org.junit.jupiter.api.Assertions.*;

public class VooControllerTest {

    private VooController vooController;
    private Aeronave aeronave;

    // Configuração do Logger
    private static final Logger logger = Logger.getLogger(VooControllerTest.class.getName());

    @BeforeEach
    public void setUp() {
        vooController = new VooController();
        aeronave = new Aeronave("A320", 180, 20000.0, 30, 100.00);  // Criando aeronave para os testes
        logger.info("VooController e Aeronave configurados.");
    }

    @Test
    public void testCadastrarVoo() {
        logger.info("Iniciando o teste de cadastro de voo.");

        // Preparando os aeroportos para o teste
        Aeroporto aeroportoOrigem = new Aeroporto(
            "Aeroporto Internacional de São Paulo", "GRU", "São Paulo", "SP", "Brasil", 
            TipoVoo.INTERNACIONAL, -23.5505, -46.6333);
        Aeroporto aeroportoDestino = new Aeroporto(
            "Aeroporto Internacional de Lisboa", "LIS", "Lisboa", "Lisboa", "Portugal", 
            TipoVoo.INTERNACIONAL, 38.7169, -9.1395);
        
        // Criando o horário de decolagem para o voo
        LocalDateTime horarioDecolagem = LocalDateTime.now().plusHours(2);
        Duration duracao = Duration.ofHours(10);
        double valorPassagem = 3500.0;

        // Criando a Companhia Aérea para o voo
        CompanhiaAerea companhiaAerea = new CompanhiaAerea("TAP Portugal", "TP", "TAP", "12345678000100", 100.0, 50.0);

        // Cadastrando o voo
        logger.info("Cadastrando voo AD4114...");
        vooController.cadastrarVoo("AD4114", aeroportoOrigem.getSigla(), aeroportoDestino.getSigla(), 
            Arrays.asList(DiaSemana.SEGUNDA, DiaSemana.TERCA), aeronave, horarioDecolagem, duracao, valorPassagem, companhiaAerea);

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
        assertEquals(companhiaAerea, voo.getCompanhiaAerea(), "A companhia aérea do voo está incorreta.");

        logger.info("Cadastro do voo AD4114 concluído com sucesso.");
    }

    @Test
    public void testProgramarVoosAtivos() {
        logger.info("Iniciando o teste de programação de voos ativos.");

        CompanhiaAerea companhiaAerea = new CompanhiaAerea("TAP Portugal", "TP", "TAP", "12345678000100", 100.0, 50.0);

        LocalDateTime horarioDecolagem = LocalDateTime.now().plusHours(2);
        Duration duracao = Duration.ofHours(10);
        double valorPassagem = 3500.0;

        vooController.cadastrarVoo("AD4117", "BSB", "REC", Arrays.asList(DiaSemana.SEGUNDA, DiaSemana.QUARTA), aeronave, horarioDecolagem, duracao, valorPassagem, companhiaAerea);

        List<Voo> voosProgramados = vooController.programarVoosAtivos();

        assertNotNull(voosProgramados, "A lista de voos programados não deveria ser nula.");
        assertFalse(voosProgramados.isEmpty(), "Deveria haver voos programados.");
        for (Voo voo : voosProgramados) {
            assertEquals(aeronave, voo.getAeronave(), "A aeronave programada está incorreta.");
            assertEquals(180, voo.getCapacidadePassageiros(), "A capacidade de passageiros está incorreta.");
            assertEquals(companhiaAerea, voo.getCompanhiaAerea(), "A companhia aérea está incorreta.");
        }

        logger.info("Testes de voos ativos concluídos.");
    }

    @Test
    public void testAlteracaoSemCustoParaVIP() {
        logger.info("Iniciando o teste de alteração de voo sem custo para passageiro VIP.");

        Passageiro passageiroVIP = new Passageiro("Ana VIP", "123.456.789-00", "vip@email.com", true, "123456789");

        CompanhiaAerea companhiaAerea = new CompanhiaAerea("TAP Portugal", "TP", "TAP", "12345678000100", 100.0, 50.0);

        Aeroporto aeroporto1 = new Aeroporto("Aeroporto A", "AAA", "Cidade A", "Estado A", "Brasil", TipoVoo.DOMESTICO, -23.5505, -46.6333); // São Paulo
        Aeroporto aeroporto2 = new Aeroporto("Aeroporto B", "BBB", "Cidade B", "Estado B", "Brasil", TipoVoo.DOMESTICO, -22.9068, -43.1729); // Rio de Janeiro

        LocalDateTime horarioDecolagem = LocalDateTime.now().plusHours(2);
        Duration duracao = Duration.ofHours(10);
        double valorPassagem = 3500.0;

        Voo vooOriginal = new Voo("AD4114", "GRU", "LIS", Arrays.asList(DiaSemana.SEGUNDA), aeronave, horarioDecolagem, duracao, valorPassagem, companhiaAerea);

        Voo novoVoo = new Voo("AD5114", "GRU", "FCO", Arrays.asList(DiaSemana.TERCA), aeronave, LocalDateTime.now().plusDays(1), Duration.ofHours(11), 3800.0, companhiaAerea);

        Date dataHoraVoo = Date.from(LocalDateTime.now().atZone(java.time.ZoneId.systemDefault()).toInstant());

        PassagemAerea passagem = new PassagemAerea(aeroporto1, aeroporto2, dataHoraVoo, "TP1020", companhiaAerea, 2000.0, valorPassagem, 5000.0, "BRL");

        boolean alteracao = vooController.alterarVoo(passagem, passageiroVIP, novoVoo);
        assertTrue(alteracao, "A alteração não foi realizada corretamente para passageiro VIP.");

        logger.info("Alteração de voo para passageiro VIP concluída com sucesso.");
    }

    @Test
    public void testarCalculoDistancia() {
        logger.info("Iniciando o teste de cálculo de distância entre aeroportos.");

        Aeroporto aeroporto1 = new Aeroporto("Aeroporto A", "AAA", "Cidade A", "Estado A", "Brasil", TipoVoo.DOMESTICO, -23.5505, -46.6333); // São Paulo
        Aeroporto aeroporto2 = new Aeroporto("Aeroporto B", "BBB", "Cidade B", "Estado B", "Brasil", TipoVoo.DOMESTICO, -22.9068, -43.1729); // Rio de Janeiro

        double distancia = aeroporto1.calcularDistanciaKm(aeroporto2);
        assertEquals(360.74882490990007, distancia, 0.01); // Aproximadamente 355.99 km de diferença

        logger.info("Cálculo de distância concluído.");
    }

    @Test
    public void testarCalculoTempoDeVoo() {
        logger.info("Iniciando o teste de cálculo de tempo de voo.");

        Aeronave aeronave = new Aeronave("A320", 180, 20000.0, 30, 800.0);  // A velocidade média agora é fornecida.

        double tempoVoo = aeronave.calcularTempoViagem(355.99); // Distância entre os aeroportos
        assertEquals(355.99 / 800.0, tempoVoo, 0.0001);  // Calculando o tempo para a distância em relação à velocidade

        logger.info("Cálculo de tempo de voo concluído.");
    }
}