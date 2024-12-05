import com.flyeasy.models.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class CartaoEmbarqueTest {

    private CartaoEmbarque cartaoEmbarque;

    @BeforeEach
    public void setUp() {
        TestLogger.log("Iniciando setup para CartaoEmbarqueTest");

        // Inicializando o cartão de embarque com dados fictícios
        cartaoEmbarque = new CartaoEmbarque(
                "Nome", 
                "Sobrenome", 
                "AER", 
                "DEST", 
                LocalTime.of(10, 30), // Usando LocalTime para horário
                LocalTime.of(10, 00), // Usando LocalTime para horário
                LocalDate.of(2024, 11, 5), // Usando LocalDate para data
                "B30"
        );

        TestLogger.log("Setup concluído para CartaoEmbarqueTest");
    }

    @Test
    public void testCartaoEmbarqueCriadoCorretamente() {
        TestLogger.log("Iniciando teste: testCartaoEmbarqueCriadoCorretamente");

        // Verifica se o cartão de embarque foi criado com os dados corretos
        assertEquals("Nome", cartaoEmbarque.getNomePassageiro());
        assertEquals("Sobrenome", cartaoEmbarque.getSobrenomePassageiro());
        assertEquals("AER", cartaoEmbarque.getOrigem());
        assertEquals("DEST", cartaoEmbarque.getDestino());
        assertEquals(LocalTime.of(10, 30), cartaoEmbarque.getHorarioVoo());
        assertEquals(LocalTime.of(9, 50), cartaoEmbarque.getHorarioEmbarque());
        assertEquals(LocalDate.of(2024, 11, 5), cartaoEmbarque.getDataEmbarque());
        assertEquals("B30", cartaoEmbarque.getAssento());

        TestLogger.log("Teste testCartaoEmbarqueCriadoCorretamente concluído com sucesso");
    }

    @Test
    public void testCartaoEmbarqueFormatoString() {
        TestLogger.log("Iniciando teste: testCartaoEmbarqueFormatoString");

        // Adicione o código necessário para o seu teste de formato de string.
        // Por exemplo, se o cartão de embarque possui uma representação em string que você quer verificar:
        String expectedString = "Cartão de Embarque: Nome Sobrenome - AER para DEST, Assento: B30, Horário de Voo: 10:30, Horário de Embarque: 09:50, Data: 2024-11-05";
        assertEquals(expectedString, cartaoEmbarque.toString());

        TestLogger.log("Teste testCartaoEmbarqueFormatoString concluído com sucesso");
    }
}
