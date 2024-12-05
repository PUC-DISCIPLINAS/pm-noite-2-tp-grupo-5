import com.flyeasy.models.Passageiro;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PassageiroTest {

    @Test
    public void testeCadastroPassageiroComDadosCompletos() {
        TestLogger.log("Iniciando teste: testeCadastroPassageiroComDadosCompletos");

        Passageiro passageiro = new Passageiro(
                "Ana Silva",
                "123.456.789-00",
                "ana@email.com",
                false,
                "RG1234567");

        assertEquals("Ana Silva", passageiro.getNome(), "O nome do passageiro está incorreto.");
        assertEquals("123.456.789-00", passageiro.getCpf(), "O CPF do passageiro está incorreto.");
        assertEquals("ana@email.com", passageiro.getEmail(), "O email do passageiro está incorreto.");
        assertFalse(passageiro.isStatusVIP(), "O status VIP deveria ser falso.");
        assertEquals("RG1234567", passageiro.getDocumento(), "O documento do passageiro está incorreto.");

        TestLogger.log("Teste testeCadastroPassageiroComDadosCompletos concluído com sucesso");
    }

    @Test
    public void testeCadastroPassageiroVIP() {
        TestLogger.log("Iniciando teste: testeCadastroPassageiroVIP");

        Passageiro passageiroVIP = new Passageiro(
                "Ana VIP",
                "123.456.789-00",
                "vip@email.com",
                true,
                "PassaporteX12345");

        assertEquals("Ana VIP", passageiroVIP.getNome(), "O nome do passageiro VIP está incorreto.");
        assertEquals("123.456.789-00", passageiroVIP.getCpf(), "O CPF do passageiro VIP está incorreto.");
        assertEquals("vip@email.com", passageiroVIP.getEmail(), "O email do passageiro VIP está incorreto.");
        assertTrue(passageiroVIP.isStatusVIP(), "O status VIP deveria ser verdadeiro.");
        assertEquals("PassaporteX12345", passageiroVIP.getDocumento(), "O documento do passageiro VIP está incorreto.");

        TestLogger.log("Teste testeCadastroPassageiroVIP concluído com sucesso");
    }

    @Test
    public void testeAlteracaoDeDadosDoPassageiro() {
        TestLogger.log("Iniciando teste: testeAlteracaoDeDadosDoPassageiro");

        Passageiro passageiro = new Passageiro(
                "Carlos Silva",
                "987.654.321-00",
                "carlos@email.com",
                false,
                "CNH987654");

        passageiro.setNome("Carlos Alberto");
        passageiro.setCpf("987.654.321-99");
        passageiro.setEmail("carlosalberto@email.com");
        passageiro.setStatusVIP(true);

        assertEquals("Carlos Alberto", passageiro.getNome(), "O nome alterado do passageiro está incorreto.");
        assertEquals("987.654.321-99", passageiro.getCpf(), "O CPF alterado do passageiro está incorreto.");
        assertEquals("carlosalberto@email.com", passageiro.getEmail(),
                "O email alterado do passageiro está incorreto.");
        assertTrue(passageiro.isStatusVIP(), "O status VIP alterado deveria ser verdadeiro.");
        assertEquals("CNH987654", passageiro.getDocumento(), "O documento do passageiro não deveria ser alterado.");

        TestLogger.log("Teste testeAlteracaoDeDadosDoPassageiro concluído com sucesso");
    }
}
