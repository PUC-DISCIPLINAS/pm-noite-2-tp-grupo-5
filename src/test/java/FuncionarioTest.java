import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class FuncionarioTest {

    @Test
    public void testarGettersESetters() {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome("João Silva");
        funcionario.setCpf("12345678909");
        funcionario.setEmail("joao.silva@flyeasy.com");

        assertEquals("João Silva", funcionario.getNome());
        assertEquals("12345678909", funcionario.getCpf());
        assertEquals("joao.silva@flyeasy.com", funcionario.getEmail());
    }
}
