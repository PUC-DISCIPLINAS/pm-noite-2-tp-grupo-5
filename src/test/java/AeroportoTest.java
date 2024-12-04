import com.flyeasy.models.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class AeroportoTest {

    @Test
    public void testarGettersESetters() {
        // Usando o construtor para criar uma instância de Aeroporto com tipo de voo, latitude e longitude
        Aeroporto aeroporto = new Aeroporto(
            "Aeroporto Internacional", "AIG", "São Paulo", "SP", "Brasil", 
            TipoVoo.INTERNACIONAL, -23.4365, -46.4737 // Exemplo de coordenadas
        );

        // Verificando se os atributos foram definidos corretamente
        assertEquals("Aeroporto Internacional", aeroporto.getNome());
        assertEquals("AIG", aeroporto.getSigla());
        assertEquals("São Paulo", aeroporto.getCidade());
        assertEquals("SP", aeroporto.getEstado());
        assertEquals("Brasil", aeroporto.getPais());
        assertEquals(TipoVoo.INTERNACIONAL, aeroporto.getTipoVoo()); // Verifica o tipo de voo
        assertEquals(-23.4365, aeroporto.getLatitude(), 0.0001); // Verifica a latitude com precisão
        assertEquals(-46.4737, aeroporto.getLongitude(), 0.0001); // Verifica a longitude com precisão
    }

    @Test
    public void testarSetters() {
        Aeroporto aeroporto = new Aeroporto(
            "Aeroporto Internacional", "AIG", "São Paulo", "SP", "Brasil", 
            TipoVoo.INTERNACIONAL, -23.4365, -46.4737
        );

        // Testando os setters
        aeroporto.setNome("Aeroporto Nacional");
        aeroporto.setSigla("NAC");
        aeroporto.setCidade("Rio de Janeiro");
        aeroporto.setEstado("RJ");
        aeroporto.setPais("Brasil");
        aeroporto.setTipoVoo(TipoVoo.DOMESTICO);
        aeroporto.setLatitude(-22.9035); // Nova latitude
        aeroporto.setLongitude(-43.2096); // Nova longitude

        // Verificando se os atributos foram atualizados corretamente
        assertEquals("Aeroporto Nacional", aeroporto.getNome());
        assertEquals("NAC", aeroporto.getSigla());
        assertEquals("Rio de Janeiro", aeroporto.getCidade());
        assertEquals("RJ", aeroporto.getEstado());
        assertEquals("Brasil", aeroporto.getPais());
        assertEquals(TipoVoo.DOMESTICO, aeroporto.getTipoVoo()); // Verifica o novo tipo de voo
        assertEquals(-22.9035, aeroporto.getLatitude(), 0.0001); // Verifica a nova latitude
        assertEquals(-43.2096, aeroporto.getLongitude(), 0.0001); // Verifica a nova longitude
    }
}
