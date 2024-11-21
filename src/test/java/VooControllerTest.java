import com.flyeasy.controllers.VooController;
import com.flyeasy.models.Aeronave;
import com.flyeasy.models.DiaSemana;
import com.flyeasy.models.Voo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

        vooController.cadastrarVoo("AD4114", "VCP", "CNF", Arrays.asList(DiaSemana.SEGUNDA, DiaSemana.TERCA), aeronave);

        Voo voo = vooController.buscarVooPorCodigo("AD4114");
        assertNotNull(voo);
        assertEquals("AD4114", voo.getCodigo());
        assertEquals("VCP", voo.getOrigem());
        assertEquals("CNF", voo.getDestino());
        assertEquals(aeronave, voo.getAeronave());
        assertEquals(180, voo.getCapacidadePassageiros());  
    }

    @Test
    public void testProgramarVoosAtivos() {

        vooController.cadastrarVoo("AD4117", "BSB", "REC", Arrays.asList(DiaSemana.SEGUNDA, DiaSemana.QUARTA), aeronave);

        List<Voo> voosProgramados = vooController.programarVoosAtivos();

        assertEquals(8, voosProgramados.size());

        for (Voo voo : voosProgramados) {
            assertEquals(aeronave, voo.getAeronave());
            assertEquals(180, voo.getCapacidadePassageiros()); 
        }
    }
}
