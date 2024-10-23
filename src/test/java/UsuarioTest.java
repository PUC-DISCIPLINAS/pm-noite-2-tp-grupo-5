import com.flyeasy.models.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class UsuarioTest {

    @Test
    public void testarCriptografiaDeSenha() {
        Usuario usuario = new Usuario();
        String senhaOriginal = "minhaSenhaSegura";
        usuario.setSenha(senhaOriginal);
        
        assertNotEquals(senhaOriginal, usuario.getSenha(), "A senha não deve ser igual à senha original após a criptografia.");
    }

    @Test
    public void testarVerificacaoDeSenhaCorreta() {
        Usuario usuario = new Usuario();
        String senhaOriginal = "minhaSenhaSegura";
        usuario.setSenha(senhaOriginal);
        
        assertTrue(usuario.verificarSenha(senhaOriginal), "A senha fornecida deve ser verificada como correta.");
    }

    @Test
    public void testarVerificacaoDeSenhaIncorreta() {
        Usuario usuario = new Usuario();
        String senhaOriginal = "minhaSenhaSegura";
        usuario.setSenha(senhaOriginal);
        
        assertFalse(usuario.verificarSenha("senhaErrada"), "A senha fornecida não deve ser verificada como correta.");
    }
}
