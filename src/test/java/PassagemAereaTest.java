import com.flyeasy.models.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Date;

public class PassagemAereaTest {

    @Test
    public void testarGettersESetters() {
        Aeroporto aeroportoOrigem = new Aeroporto("Aeroporto Internacional", "AIG", "São Paulo", "SP", "Brasil");
        aeroportoOrigem.setNome("Aeroporto de Origem");
        
        Aeroporto aeroportoDestino = new Aeroporto("Aeroporto Internacional", "AIG", "São Paulo", "SP", "Brasil");
        aeroportoDestino.setNome("Aeroporto de Destino");

        CompanhiaAerea companhiaAerea = new CompanhiaAerea("Latam", "LA", "Latam Airlines", "12345678000101", 100.0, 50.0);
        Date dataHoraVoo = new Date();
        PassagemAerea passagem = new PassagemAerea(aeroportoOrigem, aeroportoDestino, dataHoraVoo, "LA123", companhiaAerea, 200.0, 300.0, 500.0, "BRL");

        // Teste dos getters
        assertEquals(aeroportoOrigem, passagem.getAeroportoOrigem());
        assertEquals(aeroportoDestino, passagem.getAeroportoDestino());
        assertEquals(dataHoraVoo, passagem.getDataHoraVoo());
        assertEquals("LA123", passagem.getCodigoVoo());
        assertEquals(companhiaAerea, passagem.getCompanhiaAerea());
        assertEquals(200.0, passagem.getTarifaBasica());
        assertEquals(300.0, passagem.getTarifaBusiness());
        assertEquals(500.0, passagem.getTarifaPremium());
        assertEquals("BRL", passagem.getMoeda());
    }

    @Test
    public void testarCalculoTarifaLucro() {
        Aeroporto aeroportoOrigem = new Aeroporto("Aeroporto Internacional", "AIG", "São Paulo", "SP", "Brasil");
        Aeroporto aeroportoDestino = new Aeroporto("Aeroporto Internacional", "AIG", "Rio de Janeiro", "RJ", "Brasil");
        CompanhiaAerea companhiaAerea = new CompanhiaAerea("Latam", "LA", "Latam Airlines", "12345678000101", 100.0, 50.0);

        PassagemAerea passagem = new PassagemAerea(
                aeroportoOrigem, 
                aeroportoDestino, 
                new Date(), 
                "LA123", 
                companhiaAerea, 
                200.0,
                300.0,
                500.0,
                "BRL"
        );

        double valorEsperado = 40.0; 
        assertEquals(valorEsperado, passagem.calcularTarifaLucro(), "O valor do lucro deve ser 20% da tarifa básica");
    }
}
