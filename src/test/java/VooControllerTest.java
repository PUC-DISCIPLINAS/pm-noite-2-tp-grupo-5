import com.flyeasy.controllers.VooController;
import com.flyeasy.models.Aeronave;
import com.flyeasy.models.Aeroporto;
import com.flyeasy.models.CompanhiaAerea;
import com.flyeasy.models.DiaSemana;
import com.flyeasy.models.Passageiro;
import com.flyeasy.models.PassagemAerea;
import com.flyeasy.models.Voo;
import com.flyeasy.models.TipoVoo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VooControllerTest {

    private VooController vooController;
    private Aeronave aeronave;

    @BeforeEach
    public void setUp() {
        vooController = new VooController();

        aeronave = new Aeronave("A320", 180, 15000, 30);
    }

    @Test
    public void testCadastrarVoo() {

        // Corrigindo a criação dos aeroportos com o TipoVoo
        Aeroporto aeroportoOrigem = new Aeroporto("Aeroporto Internacional de São Paulo", "GRU", "São Paulo", "SP", "Brasil", TipoVoo.INTERNACIONAL);
        Aeroporto aeroportoDestino = new Aeroporto("Aeroporto Internacional de Lisboa", "LIS", "Lisboa", "Lisboa", "Portugal", TipoVoo.INTERNACIONAL);

        vooController.cadastrarVoo("AD4114", aeroportoOrigem.getSigla(), aeroportoDestino.getSigla(), Arrays.asList(DiaSemana.SEGUNDA, DiaSemana.TERCA), aeronave);

        Voo voo = vooController.buscarVooPorCodigo("AD4114");
        assertNotNull(voo);
        assertEquals("AD4114", voo.getCodigo());
        assertEquals(aeroportoOrigem.getSigla(), voo.getOrigem());
        assertEquals(aeroportoDestino.getSigla(), voo.getDestino());
        assertEquals(aeronave, voo.getAeronave());
        assertEquals(180, voo.getCapacidadePassageiros());
    }

    @Test
    public void testProgramarVoosAtivos() {
        Aeronave aeronave = new Aeronave("A320", 180, 2000.0, 30);

        vooController.cadastrarVoo("AD4117", "BSB", "REC", Arrays.asList(DiaSemana.SEGUNDA, DiaSemana.QUARTA),
                aeronave);

        List<Voo> voosProgramados = vooController.programarVoosAtivos();

        assertEquals(10, voosProgramados.size());
        for (Voo voo : voosProgramados) {
            assertEquals(aeronave, voo.getAeronave());
            assertEquals(180, voo.getCapacidadePassageiros());
        }
    }

    @Test
    public void testAlteracaoSemCustoParaVIP() {
        Passageiro passageiroVIP = new Passageiro("Ana VIP", "123.456.789-00", "vip@email.com", true);

        // Corrigindo a criação dos aeroportos com o TipoVoo
        Aeroporto aeroportoOrigem = new Aeroporto("Aeroporto Internacional de São Paulo", "GRU", "São Paulo", "SP", "Brasil", TipoVoo.INTERNACIONAL);
        Aeroporto aeroportoDestino = new Aeroporto("Aeroporto Internacional de Lisboa", "LIS", "Lisboa", "Lisboa", "Portugal", TipoVoo.INTERNACIONAL);

        Voo vooOriginal = new Voo("AD4114", "GRU", "LIS", Arrays.asList(DiaSemana.SEGUNDA), new Aeronave("A320", 180, 2000.0, 30));
        Voo novoVoo = new Voo("AD5114", "GRU", "FCO", Arrays.asList(DiaSemana.TERCA), new Aeronave("A320", 180, 2000.0, 30));

        PassagemAerea passagem = new PassagemAerea(
                aeroportoOrigem,
                aeroportoDestino,
                new Date(),
                "TP1020",
                new CompanhiaAerea("TAP Portugal", "TP", "TAP", "12345678000100", 100.0, 50.0),
                2000.0, 3500.0, 5000.0, "BRL"
        );

        vooController.cadastrarVoo("AD4114", "GRU", "LIS", Arrays.asList(DiaSemana.SEGUNDA), new Aeronave("A320", 180, 2000.0, 30));

        boolean alteracaoBemSucedida = vooController.alterarVoo(passagem, passageiroVIP, novoVoo);

        assertTrue(alteracaoBemSucedida, "Passageiro VIP deveria alterar o voo sem custo.");
        assertEquals(novoVoo, passagem.getVoo(), "O voo associado à passagem deveria ser atualizado.");
    }
}
