import com.flyeasy.models.*;
import org.junit.jupiter.api.Test;
import java.util.logging.*;

import static org.junit.jupiter.api.Assertions.*;

public class UsuarioTest {

    // Configuração do Logger
    private static final Logger logger = Logger.getLogger(UsuarioTest.class.getName());

    @Test
    public void testarCriptografiaDeSenha() {
        logger.info("Iniciando o teste de criptografia de senha.");

        Usuario usuario = new Usuario();
        String senhaOriginal = "minhaSenhaSegura";
        usuario.setSenha(senhaOriginal);

        // Log da senha antes da criptografia (não deve ser logado em ambiente de produção)
        logger.fine("Senha original: " + senhaOriginal);

        // A senha criptografada não deve ser igual à senha original
        assertNotEquals(senhaOriginal, usuario.getSenha(), "A senha não deve ser igual à senha original após a criptografia.");

        // Log do resultado da comparação
        logger.info("Senha criptografada: " + usuario.getSenha());
        logger.info("Teste de criptografia concluído.");
    }

    @Test
    public void testarVerificacaoDeSenhaCorreta() {
        logger.info("Iniciando o teste de verificação de senha correta.");

        Usuario usuario = new Usuario();
        String senhaOriginal = "minhaSenhaSegura";
        usuario.setSenha(senhaOriginal);

        // Log da senha sendo verificada
        logger.fine("Verificando a senha correta: " + senhaOriginal);

        // A senha fornecida deve ser verificada como correta
        assertTrue(usuario.verificarSenha(senhaOriginal), "A senha fornecida deve ser verificada como correta.");

        logger.info("Senha verificada como correta. Teste concluído.");
    }

    @Test
    public void testarVerificacaoDeSenhaIncorreta() {
        logger.info("Iniciando o teste de verificação de senha incorreta.");

        Usuario usuario = new Usuario();
        String senhaOriginal = "minhaSenhaSegura";
        usuario.setSenha(senhaOriginal);

        // Log da senha incorreta sendo verificada
        String senhaErrada = "senhaErrada";
        logger.fine("Tentando verificar senha incorreta: " + senhaErrada);

        // A senha fornecida não deve ser verificada como correta
        assertFalse(usuario.verificarSenha(senhaErrada), "A senha fornecida não deve ser verificada como correta.");

        logger.info("Senha incorreta verificada corretamente. Teste concluído.");
    }
}