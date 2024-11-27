import com.flyeasy.controllers.PassagemController;
import com.flyeasy.models.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Date;

public class PassagemAereaTest {

    @Test
    public void testarGettersESetters() {
        Aeroporto aeroportoOrigem = new Aeroporto("Aeroporto Internacional de São Paulo", "GRU", "São Paulo", "SP", "Brasil", TipoVoo.INTERNACIONAL);
        Aeroporto aeroportoDestino = new Aeroporto("Aeroporto Internacional de Lisboa", "LIS", "Lisboa", "Lisboa", "Portugal", TipoVoo.INTERNACIONAL);

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
        Aeroporto aeroportoOrigem = new Aeroporto("Aeroporto Internacional de São Paulo", "GRU", "São Paulo", "SP", "Brasil", TipoVoo.INTERNACIONAL);
        Aeroporto aeroportoDestino = new Aeroporto("Aeroporto Internacional de Lisboa", "LIS", "Lisboa", "Lisboa", "Portugal", TipoVoo.INTERNACIONAL);
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
    public void testCancelamentoSemCustoParaVIP() {
        Passageiro passageiroVIP = new Passageiro("Ana VIP", "123.456.789-00", "vip@email.com", true);
        
        Aeroporto aeroportoOrigem = new Aeroporto("Aeroporto Internacional de São Paulo", "GRU", "São Paulo", "SP", "Brasil", TipoVoo.INTERNACIONAL);
        Aeroporto aeroportoDestino = new Aeroporto("Aeroporto Internacional de Lisboa", "LIS", "Lisboa", "Lisboa", "Portugal", TipoVoo.INTERNACIONAL);

        PassagemAerea passagem = new PassagemAerea(
                aeroportoOrigem,
                aeroportoDestino,
                new Date(System.currentTimeMillis() + 86400000),
                "TP1020",
                new CompanhiaAerea("TAP Portugal", "TP", "TAP", "12345678000100", 100.0, 50.0),
                2000.0, 3500.0, 5000.0, "BRL"
        );

        boolean cancelamentoBemSucedido = new PassagemController().cancelarPassagem(passagem, passageiroVIP);

        assertTrue(cancelamentoBemSucedido, "Passageiro VIP deveria cancelar a passagem sem custo.");
    }

    @Test
    public void testDescontoBagagemParaVIP() {
        Passageiro passageiroVIP = new Passageiro("Ana VIP", "123.456.789-00", "vip@email.com", true);

        PassagemController controller = new PassagemController();
        double custoComDesconto = controller.calcularCustoBagagem(passageiroVIP, 2);

        assertEquals(50.0 * 2 * 0.5, custoComDesconto, "Desconto de 50% deveria ser aplicado ao passageiro VIP.");
    }
}
