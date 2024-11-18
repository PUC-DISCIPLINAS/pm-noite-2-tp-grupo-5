import com.flyeasy.models.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class AeroportoTest {

    @Test
    public void testarGettersESetters() {
        // Usando o construtor para criar uma instância de Aeroporto com tipo de voo
        Aeroporto aeroporto = new Aeroporto("Aeroporto Internacional", "AIG", "São Paulo", "SP", "Brasil", TipoVoo.INTERNACIONAL);

        // Verificando se os atributos foram definidos corretamente
        assertEquals("Aeroporto Internacional", aeroporto.getNome());
        assertEquals("AIG", aeroporto.getSigla());
        assertEquals("São Paulo", aeroporto.getCidade());
        assertEquals("SP", aeroporto.getEstado());
        assertEquals("Brasil", aeroporto.getPais());
        assertEquals(TipoVoo.INTERNACIONAL, aeroporto.getTipoVoo()); // Verifica o tipo de voo
    }

    @Test
    public void testarSetters() {
        Aeroporto aeroporto = new Aeroporto("Aeroporto Internacional", "AIG", "São Paulo", "SP", "Brasil", TipoVoo.INTERNACIONAL);

        // Testando os setters
        aeroporto.setNome("Aeroporto Nacional");
        aeroporto.setSigla("NAC");
        aeroporto.setCidade("Rio de Janeiro");
        aeroporto.setEstado("RJ");
        aeroporto.setPais("Brasil");
        aeroporto.setTipoVoo(TipoVoo.DOMESTICO);

        // Verificando se os atributos foram atualizados corretamente
        assertEquals("Aeroporto Nacional", aeroporto.getNome());
        assertEquals("NAC", aeroporto.getSigla());
        assertEquals("Rio de Janeiro", aeroporto.getCidade());
        assertEquals("RJ", aeroporto.getEstado());
        assertEquals("Brasil", aeroporto.getPais());
        assertEquals(TipoVoo.DOMESTICO, aeroporto.getTipoVoo()); // Verifica o novo tipo de voo
    }
}
