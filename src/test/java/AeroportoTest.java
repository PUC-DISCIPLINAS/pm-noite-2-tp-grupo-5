import com.flyeasy.models.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class AeroportoTest {

    @Test
    public void testarGettersESetters() {
        Aeroporto aeroporto = new Aeroporto();
        aeroporto.setNome("Aeroporto Internacional");
        aeroporto.setSigla("AIG");
        aeroporto.setCidade("São Paulo");
        aeroporto.setEstado("SP");
        aeroporto.setPais("Brasil");

        assertEquals("Aeroporto Internacional", aeroporto.getNome());
        assertEquals("AIG", aeroporto.getSigla());
        assertEquals("São Paulo", aeroporto.getCidade());
        assertEquals("SP", aeroporto.getEstado());
        assertEquals("Brasil", aeroporto.getPais());
    }
}
