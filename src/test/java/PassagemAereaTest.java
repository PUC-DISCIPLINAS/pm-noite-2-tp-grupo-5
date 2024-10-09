import com.flyeasy.models.*;
import com.flyeasy.controllers.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Date;

public class PassagemAereaTest {

    @Test
    public void testarGettersESetters() {
        Aeroporto aeroportoOrigem = new Aeroporto();
        aeroportoOrigem.setNome("Aeroporto de Origem");
        
        Aeroporto aeroportoDestino = new Aeroporto();
        aeroportoDestino.setNome("Aeroporto de Destino");

        CompanhiaAerea companhiaAerea = new CompanhiaAerea("Latam", "LA", "Latam Airlines", "12345678000101", 100.0, 50.0);
        Date dataHoraVoo = new Date();
        PassagemAerea passagem = new PassagemAerea(aeroportoOrigem, aeroportoDestino, dataHoraVoo, "LA123", companhiaAerea, 200.0, 300.0, 500.0, "BRL");

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
        PassagemAerea passagem = new PassagemAerea(new Aeroporto(), new Aeroporto(), new Date(), "LA123", new CompanhiaAerea("Latam", "LA", "Latam Airlines", "12345678000101", 100.0, 50.0), 200.0, 300.0, 500.0, "BRL");
        
        double valorEsperado = 40.0; // 20% de 200.0
        assertEquals(valorEsperado, passagem.calcularTarifaLucro(), "O valor do lucro deve ser 20% da tarifa básica");
    }
}
