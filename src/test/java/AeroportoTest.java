import com.flyeasy.models.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class AeroportoTest {

    @Test
    public void testarGettersESetters() {
        // Usando o construtor para criar uma instância de Aeroporto
        Aeroporto aeroporto = new Aeroporto("Aeroporto Internacional", "AIG", "São Paulo", "SP", "Brasil");

        // Verificando se os atributos foram definidos corretamente
        assertEquals("Aeroporto Internacional", aeroporto.getNome());
        assertEquals("AIG", aeroporto.getSigla());
        assertEquals("São Paulo", aeroporto.getCidade());
        assertEquals("SP", aeroporto.getEstado());
        assertEquals("Brasil", aeroporto.getPais());
    }
}
