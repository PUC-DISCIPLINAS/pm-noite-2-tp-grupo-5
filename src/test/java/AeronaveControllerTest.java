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
    }

    @Test
    public void testarCadastrarAeronave() {
        aeronaveController.cadastrarAeronave("EF789", 220, 17000.0, 40);

        Aeronave aeronave = aeronaveController.buscarAeronavePorCodigo("EF789");
        assertNotNull(aeronave);
        assertEquals("EF789", aeronave.getCodigoAeronave());
        assertEquals(220, aeronave.getCapacidadePassageiros());
        assertEquals(17000.0, aeronave.getCapacidadeCarga());
        assertEquals(40, aeronave.getNumeroFileiras());
    }

    @Test
    public void testarListarAeronaves() {
        assertEquals(2, aeronaveController.listarAeronaves().size());

        aeronaveController.cadastrarAeronave("EF789", 220, 17000.0, 40);
        assertEquals(3, aeronaveController.listarAeronaves().size());
    }

    @Test
    public void testarBuscarAeronavePorCodigo() {
        Aeronave aeronave = aeronaveController.buscarAeronavePorCodigo("CD456");
        assertNotNull(aeronave);
        assertEquals("CD456", aeronave.getCodigoAeronave());

        assertNull(aeronaveController.buscarAeronavePorCodigo("ZZ999"));
    }

    @Test
    public void testarExibirDisposicaoAssentos() {
        aeronaveController.exibirDisposicaoAssentos("AB123");
    }
}
