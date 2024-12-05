import com.flyeasy.controllers.AeronaveController;
import com.flyeasy.models.Aeronave;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AeronaveControllerTest {

    private AeronaveController aeronaveController;

    @BeforeEach
    public void setUp() {
        aeronaveController = new AeronaveController();
        aeronaveController.cadastrarAeronave("AB123", 180, 15000.0, 30);
        aeronaveController.cadastrarAeronave("CD456", 200, 16000.0, 35);

        // Logando a configuração inicial
        TestLogger.log("Configuração inicial: Cadastrando aeronaves AB123 e CD456");
    }

    @Test
    public void testarCadastrarAeronave() {
        TestLogger.log("Iniciando teste: testarCadastrarAeronave");

        aeronaveController.cadastrarAeronave("EF789", 220, 17000.0, 40);
        Aeronave aeronave = aeronaveController.buscarAeronavePorCodigo("EF789");

        assertNotNull(aeronave);
        assertEquals("EF789", aeronave.getCodigoAeronave());
        assertEquals(220, aeronave.getCapacidadePassageiros());
        assertEquals(17000.0, aeronave.getCapacidadeCarga());
        assertEquals(40, aeronave.getNumeroFileiras());

        TestLogger.log("Teste de cadastro de aeronave EF789 concluído com sucesso");
    }

    @Test
    public void testarListarAeronaves() {
        TestLogger.log("Iniciando teste: testarListarAeronaves");

        assertEquals(2, aeronaveController.listarAeronaves().size());

        aeronaveController.cadastrarAeronave("EF789", 220, 17000.0, 40);
        assertEquals(3, aeronaveController.listarAeronaves().size());

        TestLogger.log("Teste de listar aeronaves concluído com sucesso");
    }

    @Test
    public void testarBuscarAeronavePorCodigo() {
        TestLogger.log("Iniciando teste: testarBuscarAeronavePorCodigo");

        Aeronave aeronave = aeronaveController.buscarAeronavePorCodigo("CD456");
        assertNotNull(aeronave);
        assertEquals("CD456", aeronave.getCodigoAeronave());

        assertNull(aeronaveController.buscarAeronavePorCodigo("ZZ999"));

        TestLogger.log("Teste de busca por código de aeronave concluído com sucesso");
    }

    @Test
    public void testarExibirDisposicaoAssentos() {
        TestLogger.log("Iniciando teste: testarExibirDisposicaoAssentos");

        aeronaveController.exibirDisposicaoAssentos("AB123");

        TestLogger.log("Teste de exibição de disposição de assentos concluído com sucesso");
    }
}