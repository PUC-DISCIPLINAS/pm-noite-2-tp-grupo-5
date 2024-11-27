import com.flyeasy.models.Passageiro;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PassageiroTest {

    @Test
    public void testeCadastroPassageiroComEmail() {
        Passageiro passageiro = new Passageiro("Ana Silva", "123.456.789-00", "ana@email.com", false);

        assertEquals("Ana Silva", passageiro.getNome());
        assertEquals("123.456.789-00", passageiro.getCpf());
        assertEquals("ana@email.com", passageiro.getEmail());
        assertEquals(false, passageiro.isStatusVIP());
    }

    @Test
    public void testePassageiroVIP() {
        Passageiro passageiroVIP = new Passageiro("Ana VIP", "123.456.789-00", "vip@email.com", true);

        assertEquals("Ana VIP", passageiroVIP.getNome());
        assertEquals("123.456.789-00", passageiroVIP.getCpf());
        assertEquals("vip@email.com", passageiroVIP.getEmail());
        assertEquals(true, passageiroVIP.isStatusVIP());
    }
}