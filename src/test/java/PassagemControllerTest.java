import com.flyeasy.controllers.PassagemController;
import com.flyeasy.models.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

public class PassagemControllerTest {

    @BeforeEach
    public void setUp() {
        PassagemController.inicializarPassagens();
    }

    @Test
    public void testRealizarCheckIn() {
        String resultado = PassagemController.realizarCheckIn("TP1020");
        assertTrue(resultado.contains("Check-in realizado com sucesso"));
    }

    @Test
    public void testRealizarCheckInForaDoPeriodo() {
        String resultado = PassagemController.realizarCheckIn("AA2150");
        assertTrue(resultado.contains("Não é possível realizar o check-in"));
    }

    @Test
    public void testCancelamentoPassagemVIP() {
        Passageiro passageiroVIP = new Passageiro("Ana VIP", "12345678900", "vip@example.com", true);

        Aeroporto aeroportoOrigem = new Aeroporto("Aeroporto de Origem", "ORG", "São Paulo", "SP", "Brasil", TipoVoo.DOMESTICO);
        Aeroporto aeroportoDestino = new Aeroporto("Aeroporto de Destino", "DST", "Rio de Janeiro", "RJ", "Brasil", TipoVoo.DOMESTICO);

        PassagemAerea passagem = new PassagemAerea(
                aeroportoOrigem,
                aeroportoDestino,
                new Date(),
                "TP1020",
                new CompanhiaAerea("TAP Portugal", "TP", "TAP", "12345678000100", 100.0, 50.0),
                2000.0, 3500.0, 5000.0, "BRL", "vip@example.com"
        );

        boolean cancelamentoBemSucedido = PassagemController.cancelarPassagem(passagem, passageiroVIP);

        assertTrue(cancelamentoBemSucedido, "Passageiro VIP deveria cancelar a passagem sem custo.");
    }

    @Test
    public void testCancelamentoPassagemRegular() {
        Passageiro passageiroRegular = new Passageiro("Carlos Regular", "98765432100", "regular@example.com", false);

        Aeroporto aeroportoOrigem = new Aeroporto("Aeroporto de Origem", "ORG", "São Paulo", "SP", "Brasil", TipoVoo.DOMESTICO);
        Aeroporto aeroportoDestino = new Aeroporto("Aeroporto de Destino", "DST", "Rio de Janeiro", "RJ", "Brasil", TipoVoo.DOMESTICO);

        PassagemAerea passagem = new PassagemAerea(
                aeroportoOrigem,
                aeroportoDestino,
                new Date(),
                "TP1020",
                new CompanhiaAerea("TAP Portugal", "TP", "TAP", "12345678000100", 100.0, 50.0),
                2000.0, 3500.0, 5000.0, "BRL", "regular@example.com"
        );

        boolean cancelamentoBemSucedido = PassagemController.cancelarPassagem(passagem, passageiroRegular);

        assertTrue(cancelamentoBemSucedido, "Passageiro regular deveria cancelar a passagem com taxa.");
    }

    @Test
    public void testCalcularCustoBagagemVIP() {
        Passageiro passageiroVIP = new Passageiro("Ana VIP", "12345678900", "vip@example.com", true);
        Aeroporto aeroportoOrigem = new Aeroporto("Aeroporto de Origem", "ORG", "São Paulo", "SP", "Brasil", TipoVoo.DOMESTICO);
        Aeroporto aeroportoDestino = new Aeroporto("Aeroporto de Destino", "DST", "Rio de Janeiro", "RJ", "Brasil", TipoVoo.DOMESTICO);

        PassagemAerea passagem = new PassagemAerea(
                aeroportoOrigem,
                aeroportoDestino,
                new Date(),
                "TP1020",
                new CompanhiaAerea("TAP Portugal", "TP", "TAP", "12345678000100", 100.0, 50.0),
                2000.0, 3500.0, 5000.0, "BRL", "vip@example.com"
        );

        double custoComDesconto = PassagemController.calcularCustoBagagem(passageiroVIP, 2);
        assertEquals(50.0 * 2 * 0.5, custoComDesconto, "Desconto de 50% deveria ser aplicado para passageiro VIP.");
    }

    @Test
    public void testCalcularCustoBagagemRegular() {
        Passageiro passageiroRegular = new Passageiro("Carlos Regular", "98765432100", "regular@example.com", false);
        Aeroporto aeroportoOrigem = new Aeroporto("Aeroporto de Origem", "ORG", "São Paulo", "SP", "Brasil", TipoVoo.DOMESTICO);
        Aeroporto aeroportoDestino = new Aeroporto("Aeroporto de Destino", "DST", "Rio de Janeiro", "RJ", "Brasil", TipoVoo.DOMESTICO);

        PassagemAerea passagem = new PassagemAerea(
                aeroportoOrigem,
                aeroportoDestino,
                new Date(),
                "TP1020",
                new CompanhiaAerea("TAP Portugal", "TP", "TAP", "12345678000100", 100.0, 50.0),
                2000.0, 3500.0, 5000.0, "BRL", "regular@example.com"
        );

        double custoSemDesconto = PassagemController.calcularCustoBagagem(passageiroRegular, 2);
        assertEquals(50.0 * 2, custoSemDesconto, "O custo de bagagem para passageiro regular deve ser 50 por bagagem.");
    }

    @Test
    public void testNotificacaoEnviada() {
        PassagemController.realizarCheckIn("TP1020");
    }
}
