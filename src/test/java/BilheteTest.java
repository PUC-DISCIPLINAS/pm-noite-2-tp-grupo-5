import com.flyeasy.models.*;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BilheteTest {

    @Test
    public void testEmitirBilhete() {
        Aeroporto aeroportoOrigem = new Aeroporto("Aeroporto de Origem", "ORG", "São Paulo", "SP", "Brasil", TipoVoo.INTERNACIONAL);
        Aeroporto aeroportoDestino = new Aeroporto("Aeroporto de Destino", "DST", "Rio de Janeiro", "RJ", "Brasil", TipoVoo.INTERNACIONAL);
        
        CompanhiaAerea companhiaAerea = new CompanhiaAerea("Companhia Aérea XYZ", "XYZ", "XYZ Airlines", "12345678000123", 100.0, 50.0);
        Date dataHoraVoo = new Date();

    PassagemAerea passagemAerea = new PassagemAerea(
        aeroportoOrigem, 
        aeroportoDestino, 
        dataHoraVoo, 
        "XYZ123", 
        companhiaAerea, 
        100.0,
        200.0,
        300.0,
        "BRL",
        "passageiro@example.com"
    );

        Bilhete bilhete = new Bilhete("João", "Silva", "123456789", passagemAerea);
        assertEquals("João", bilhete.getNome());
        assertEquals("Silva", bilhete.getSobrenome());
        assertEquals("123456789", bilhete.getDocumento());
        assertEquals(passagemAerea, bilhete.getPassagemAerea());
        assertEquals(120.0, bilhete.getValorTotal(), 0.01);
    }

    @Test
    public void testToString() {
        Aeroporto aeroportoOrigem = new Aeroporto("Aeroporto de Origem", "ORG", "São Paulo", "SP", "Brasil", TipoVoo.INTERNACIONAL);
        Aeroporto aeroportoDestino = new Aeroporto("Aeroporto de Destino", "DST", "Rio de Janeiro", "RJ", "Brasil", TipoVoo.INTERNACIONAL);
        CompanhiaAerea companhiaAerea = new CompanhiaAerea("Companhia Aérea XYZ", "XYZ", "XYZ Airlines", "12345678000123", 100.0, 50.0);
        Date dataHoraVoo = new Date();

    PassagemAerea passagemAerea = new PassagemAerea(
            aeroportoOrigem, 
            aeroportoDestino, 
            dataHoraVoo, 
            "XYZ123", 
            companhiaAerea, 
            100.0,
            200.0,
            300.0,
            "BRL",
            "passageiro@example.com"
    );

        Bilhete bilhete = new Bilhete("Maria", "Oliveira", "987654321", passagemAerea);

        String expectedString = "Bilhete{" +
                "nome='Maria'" +
                ", sobrenome='Oliveira'" +
                ", documento='987654321'" +
                ", passagemAerea=" + passagemAerea +
                ", valorTotal=" + bilhete.getValorTotal() +
                '}';
        
        assertEquals(expectedString, bilhete.toString());
    }
}
