import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// Classe de teste para a classe Aeronave
public class AeronaveTest {

    private Aeronave aeronave;

    // Método para inicializar o objeto aeronave antes de cada teste
    @BeforeEach
    public void setUp() {
        aeronave = new Aeronave("AB123", 180, 15000.0, 30);
    }

    @Test
    public void testarGetters() {
        assertEquals("AB123", aeronave.getCodigoAeronave());
        assertEquals(180, aeronave.getCapacidadePassageiros());
        assertEquals(15000.0, aeronave.getCapacidadeCarga());
        assertEquals(30, aeronave.getNumeroFileiras());
        assertEquals(6, aeronave.getAssentosPorFileira());
    }

    @Test
    public void testarExibirDisposicaoAssentos() {
        // Teste apenas para garantir que o método seja executado sem erros.
        aeronave.exibirDisposicaoAssentos();
    }

    @Test
    public void testarCapacidadePassageiros() {
        int capacidadeEsperada = 180;
        assertEquals(capacidadeEsperada, aeronave.getCapacidadePassageiros());
    }

    @Test
    public void testarCapacidadeCarga() {
        double capacidadeCargaEsperada = 15000.0;
        assertEquals(capacidadeCargaEsperada, aeronave.getCapacidadeCarga());
    }
}
