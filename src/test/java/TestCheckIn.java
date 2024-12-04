import com.flyeasy.controllers.PassagemController;
import com.flyeasy.models.Aeroporto;
import com.flyeasy.models.CompanhiaAerea;
import com.flyeasy.models.PassagemAerea;

import java.util.Date;

public class TestCheckIn {

    public static void main(String[] args) {
        // Inicializando as passagens
        PassagemController.inicializarPassagens();

        // Teste 1: Realizando check-in dentro do período permitido
        System.out.println(PassagemController.realizarCheckIn("TP1020"));

        // Teste 2: Tentando realizar check-in fora do período permitido
        try {
            Thread.sleep(1000); // Espera 1 segundo para simular o tempo
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(PassagemController.realizarCheckIn("AA2150"));

        // Teste 3: Verificando No Show após o horário de partida
        PassagemController.verificarNoShow();
    }
}
