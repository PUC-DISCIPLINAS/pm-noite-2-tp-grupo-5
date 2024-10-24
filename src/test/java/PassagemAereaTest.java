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
        // Inicializando os objetos Aeroporto e CompanhiaAerea
        Aeroporto aeroportoOrigem = new Aeroporto("Aeroporto Internacional", "AIG", "São Paulo", "SP", "Brasil");
        Aeroporto aeroportoDestino = new Aeroporto("Aeroporto Internacional", "AIG", "Rio de Janeiro", "RJ", "Brasil");
        CompanhiaAerea companhiaAerea = new CompanhiaAerea("Latam", "LA", "Latam Airlines", "12345678000101", 100.0, 50.0);

        // Inicializando a passagem com tarifa básica de 200.0
        PassagemAerea passagem = new PassagemAerea(
                aeroportoOrigem, 
                aeroportoDestino, 
                new Date(), 
                "LA123", 
                companhiaAerea, 
                200.0, // Tarifa básica
                300.0, // Tarifa business
                500.0, // Tarifa premium
                "BRL"
        );

        // Cálculo esperado: 20% de 200.0 = 40.0
        double valorEsperado = 40.0; 
        assertEquals(valorEsperado, passagem.calcularTarifaLucro(), "O valor do lucro deve ser 20% da tarifa básica");
    }
    @Test
    public void testarReservaDeAssento() {
        PassagemAerea passagem = new PassagemAerea("Aeroporto Internacional de Guarulhos", 
                "Aeroporto Internacional de Lisboa", new Date(), "LA123", "Latam", 
                200.0, 300.0, 500.0, "BRL");

        boolean reserva1 = passagem.reservarAssento("12A");
        boolean reserva2 = passagem.reservarAssento("12A"); // Tentando reservar o mesmo assento novamente

        assertTrue(reserva1, "A reserva do assento 12A deve ser bem-sucedida");
        assertFalse(reserva2, "A reserva do assento 12A deve falhar, pois já foi reservado");
    }
}
}
