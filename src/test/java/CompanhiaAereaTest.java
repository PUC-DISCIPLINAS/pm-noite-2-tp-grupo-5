import com.flyeasy.models.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CompanhiaAereaTest {

    @Test
    public void testarCalculoBagagemSemBagagem() {
        TestLogger.log("Iniciando teste: testarCalculoBagagemSemBagagem");

        CompanhiaAerea companhia = new CompanhiaAerea("Latam", "LA", "Latam Airlines", "12345678000101", 100.0, 50.0);
        double valorCalculado = companhia.calcularValorBagagem(0);
        System.out.println("Testar cálculo sem bagagem: Valor calculado com 0 bagagens é " + valorCalculado);
        assertEquals(0.0, valorCalculado, "Sem bagagem, o valor deve ser zero");

        TestLogger.log("Teste testarCalculoBagagemSemBagagem concluído com sucesso");
    }

    @Test
    public void testarCalculoBagagemComUmaBagagem() {
        TestLogger.log("Iniciando teste: testarCalculoBagagemComUmaBagagem");

        CompanhiaAerea companhia = new CompanhiaAerea("Latam", "LA", "Latam Airlines", "12345678000101", 100.0, 50.0);
        double valorCalculado = companhia.calcularValorBagagem(1);
        System.out.println("Testar cálculo com uma bagagem: Valor calculado com 1 bagagem é " + valorCalculado);
        assertEquals(100.0, valorCalculado, "Com uma bagagem, o valor deve ser 100");

        TestLogger.log("Teste testarCalculoBagagemComUmaBagagem concluído com sucesso");
    }

    @Test
    public void testarCalculoBagagemComDuasBagagens() {
        TestLogger.log("Iniciando teste: testarCalculoBagagemComDuasBagagens");

        CompanhiaAerea companhia = new CompanhiaAerea("Latam", "LA", "Latam Airlines", "12345678000101", 100.0, 50.0);
        double valorCalculado = companhia.calcularValorBagagem(2);
        System.out.println("Testar cálculo com duas bagagens: Valor calculado com 2 bagagens é " + valorCalculado);
        assertEquals(150.0, valorCalculado, "Com duas bagagens, o valor deve ser 150");

        TestLogger.log("Teste testarCalculoBagagemComDuasBagagens concluído com sucesso");
    }

    @Test
    public void testarCalculoBagagemComBagagensNegativas() {
        TestLogger.log("Iniciando teste: testarCalculoBagagemComBagagensNegativas");

        CompanhiaAerea companhia = new CompanhiaAerea("Latam", "LA", "Latam Airlines", "12345678000101", 100.0, 50.0);
        double valorCalculado = companhia.calcularValorBagagem(-1);
        System.out.println("Testar cálculo com bagagem negativa: Valor calculado com -1 bagagens é " + valorCalculado);
        assertEquals(0.0, valorCalculado, "O valor para bagagens negativas deve ser zero");

        TestLogger.log("Teste testarCalculoBagagemComBagagensNegativas concluído com sucesso");
    }

    @Test
    public void testarCalculoBagagemComGrandeQuantidade() {
        TestLogger.log("Iniciando teste: testarCalculoBagagemComGrandeQuantidade");

        CompanhiaAerea companhia = new CompanhiaAerea("Latam", "LA", "Latam Airlines", "12345678000101", 50.0, 50.0);
        double valorCalculado = companhia.calcularValorBagagem(1000);
        System.out.println("Testar cálculo com grande quantidade de bagagens: Valor calculado com 1000 bagagens é " + valorCalculado);
        assertEquals(50000.0, valorCalculado, "Com 1000 bagagens, o valor deve ser 50000");

        TestLogger.log("Teste testarCalculoBagagemComGrandeQuantidade concluído com sucesso");
    }    
    
}