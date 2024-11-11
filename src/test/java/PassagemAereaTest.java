import com.flyeasy.models.*;
import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    public void testarReservarAssento() {
        PassagemAerea passagem = new PassagemAerea(
                new Aeroporto("Aeroporto Internacional", "AIG", "São Paulo", "SP", "Brasil"), 
                new Aeroporto("Aeroporto Internacional", "AIG", "Rio de Janeiro", "RJ", "Brasil"),
                new Date(),
                "LA123",
                new CompanhiaAerea("Latam", "LA", "Latam Airlines", "12345678000101", 100.0, 50.0),
                200.0,
                300.0,
                500.0,
                "BRL"
        );

        // Teste de reserva de assento disponível
        assertTrue(passagem.reservarAssento("A1"));
        assertFalse(passagem.verificarDisponibilidade("A1"), "Assento A1 deve estar ocupado após a reserva");

        // Teste de reserva de assento indisponível
        assertFalse(passagem.reservarAssento("A1"), "Não deve ser possível reservar o mesmo assento duas vezes");
    }

    @Test
    public void testarPrecoComTaxas() {
        PassagemAerea passagem = new PassagemAerea(
                new Aeroporto("Aeroporto Internacional", "AIG", "São Paulo", "SP", "Brasil"), 
                new Aeroporto("Aeroporto Internacional", "AIG", "Rio de Janeiro", "RJ", "Brasil"),
                new Date(),
                "LA123",
                new CompanhiaAerea("Latam", "LA", "Latam Airlines", "12345678000101", 100.0, 50.0),
                200.0,
                300.0,
                500.0,
                "BRL"
        );

        double precoEsperado = 240.0; // Tarifa básica + 20% de lucro
        assertEquals(precoEsperado, passagem.getPrecoComTaxas(), 0.01, "O preço com taxas deve incluir a tarifa básica e o lucro");
    }

    @Test
    public void testarAtualizacaoStatusPassagem() {
        PassagemAerea passagem = new PassagemAerea(
                new Aeroporto("Aeroporto Internacional", "AIG", "São Paulo", "SP", "Brasil"), 
                new Aeroporto("Aeroporto Internacional", "AIG", "Rio de Janeiro", "RJ", "Brasil"),
                new Date(),
                "LA123",
                new CompanhiaAerea("Latam", "LA", "Latam Airlines", "12345678000101", 100.0, 50.0),
                200.0,
                300.0,
                500.0,
                "BRL"
        );

        // Status inicial deve ser ADQUIRIDA
        assertEquals(PassagemAerea.StatusPassagem.ADQUIRIDA, passagem.getStatusPassagem(), "Status inicial deve ser 'ADQUIRIDA'");

        // Atualizar status para CHECKIN_REALIZADO
        passagem.atualizarStatus(PassagemAerea.StatusPassagem.CHECKIN_REALIZADO);
        assertEquals(PassagemAerea.StatusPassagem.CHECKIN_REALIZADO, passagem.getStatusPassagem(), "O status deve ser 'CHECKIN_REALIZADO' após atualização");

        // Atualizar status para CANCELADA
        passagem.atualizarStatus(PassagemAerea.StatusPassagem.CANCELADA);
        assertEquals(PassagemAerea.StatusPassagem.CANCELADA, passagem.getStatusPassagem(), "O status deve ser 'CANCELADA' após atualização");
    }
}
