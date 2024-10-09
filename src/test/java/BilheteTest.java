package com.flyeasy;

import com.flyeasy.models.*;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BilheteTest {

    @Test
    public void testEmitirBilhete() {
        // Criar objetos de teste
        Aeroporto aeroportoOrigem = new Aeroporto("Aeroporto de Origem", "Origem");
        Aeroporto aeroportoDestino = new Aeroporto("Aeroporto de Destino", "Destino");
        CompanhiaAerea companhiaAerea = new CompanhiaAerea("Companhia Aérea XYZ");
        PassagemAerea passagemAerea = new PassagemAerea(
                aeroportoOrigem,
                aeroportoDestino,
                new Date(),
                "XYZ123",
                companhiaAerea,
                100.0,  // tarifaBasica
                200.0,  // tarifaBusiness
                300.0,  // tarifaPremium
                "BRL"   // moeda
        );

        // Criar um bilhete
        Bilhete bilhete = new Bilhete("João", "Silva", "123456789", passagemAerea);

        // Verificar se os valores estão corretos
        assertEquals("João", bilhete.getNome());
        assertEquals("Silva", bilhete.getSobrenome());
        assertEquals("123456789", bilhete.getDocumento());
        assertEquals(passagemAerea, bilhete.getPassagemAerea());
        assertEquals(120.0, bilhete.getValorTotal(), 0.01); // valor total esperado com 20% de lucro
    }

    @Test
    public void testToString() {
        Aeroporto aeroportoOrigem = new Aeroporto("Aeroporto de Origem", "Origem");
        Aeroporto aeroportoDestino = new Aeroporto("Aeroporto de Destino", "Destino");
        CompanhiaAerea companhiaAerea = new CompanhiaAerea("Companhia Aérea XYZ");
        PassagemAerea passagemAerea = new PassagemAerea(
                aeroportoOrigem,
                aeroportoDestino,
                new Date(),
                "XYZ123",
                companhiaAerea,
                100.0,  // tarifaBasica
                200.0,  // tarifaBusiness
                300.0,  // tarifaPremium
                "BRL"   // moeda
        );

        Bilhete bilhete = new Bilhete("Maria", "Oliveira", "987654321", passagemAerea);
        
        // Verificar a saída do toString
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
