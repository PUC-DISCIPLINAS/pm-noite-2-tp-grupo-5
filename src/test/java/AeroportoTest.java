import com.flyeasy.models.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class AeroportoTest {

    @Test
    public void testarGettersESetters() {
        TestLogger.log("Iniciando teste: testarGettersESetters");

        // Criando a instância do aeroporto
        Aeroporto aeroporto = new Aeroporto(
            "Aeroporto Internacional", "AIG", "São Paulo", "SP", "Brasil", 
            TipoVoo.INTERNACIONAL, -23.4365, -46.4737
        );

        // Verificando os atributos com assertivas
        assertEquals("Aeroporto Internacional", aeroporto.getNome());
        assertEquals("AIG", aeroporto.getSigla());
        assertEquals("São Paulo", aeroporto.getCidade());
        assertEquals("SP", aeroporto.getEstado());
        assertEquals("Brasil", aeroporto.getPais());
        assertEquals(TipoVoo.INTERNACIONAL, aeroporto.getTipoVoo()); // Verifica o tipo de voo
        assertEquals(-23.4365, aeroporto.getLatitude(), 0.0001); // Verifica a latitude
        assertEquals(-46.4737, aeroporto.getLongitude(), 0.0001); // Verifica a longitude

        TestLogger.log("Teste testarGettersESetters concluído com sucesso");
    }

    @Test
    public void testarSetters() {
        TestLogger.log("Iniciando teste: testarSetters");

        // Criando a instância do aeroporto
        Aeroporto aeroporto = new Aeroporto(
            "Aeroporto Internacional", "AIG", "São Paulo", "SP", "Brasil", 
            TipoVoo.INTERNACIONAL, -23.4365, -46.4737
        );

        // Atualizando os valores com os setters
        aeroporto.setNome("Aeroporto Nacional");
        aeroporto.setSigla("NAC");
        aeroporto.setCidade("Rio de Janeiro");
        aeroporto.setEstado("RJ");
        aeroporto.setPais("Brasil");
        aeroporto.setTipoVoo(TipoVoo.DOMESTICO);
        aeroporto.setLatitude(-22.9035);
        aeroporto.setLongitude(-43.2096);

        // Verificando se os valores foram atualizados corretamente
        assertEquals("Aeroporto Nacional", aeroporto.getNome());
        assertEquals("NAC", aeroporto.getSigla());
        assertEquals("Rio de Janeiro", aeroporto.getCidade());
        assertEquals("RJ", aeroporto.getEstado());
        assertEquals("Brasil", aeroporto.getPais());
        assertEquals(TipoVoo.DOMESTICO, aeroporto.getTipoVoo());
        assertEquals(-22.9035, aeroporto.getLatitude(), 0.0001);
        assertEquals(-43.2096, aeroporto.getLongitude(), 0.0001);

        TestLogger.log("Teste testarSetters concluído com sucesso");
    }
}