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
    }

    @Test
    public void testCartaoEmbarqueCriadoCorretamente() {
        // Verifica se o cartão de embarque foi criado com os dados corretos
        assertEquals("Nome", cartaoEmbarque.getNomePassageiro());
        assertEquals("Sobrenome", cartaoEmbarque.getSobrenomePassageiro());
        assertEquals("AER", cartaoEmbarque.getOrigem());
        assertEquals("DEST", cartaoEmbarque.getDestino());
        assertEquals(LocalTime.of(10, 30), cartaoEmbarque.getHorarioVoo());
        assertEquals(LocalTime.of(10, 00), cartaoEmbarque.getHorarioEmbarque());
        assertEquals(LocalDate.of(2024, 11, 5), cartaoEmbarque.getDataEmbarque());
        assertEquals("B30", cartaoEmbarque.getAssento());
    }

    @Test
    public void testCartaoEmbarqueFormatoString() {
        // Verifica se a representação em string do cartão de embarque está correta
        // Adicione o código necessário para o seu teste de formato de string.
    }
}
