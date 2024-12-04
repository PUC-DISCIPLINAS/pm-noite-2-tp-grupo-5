package com.flyeasy.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CartaoEmbarqueTest {

    private CartaoEmbarque cartaoEmbarque;

    @BeforeEach
    public void setUp() {
        // Inicializando o cartão de embarque com dados fictícios
        cartaoEmbarque = new CartaoEmbarque("Nome", "Sobrenome", "AER", "DEST", "CA1234", "10:00", "2024-11-05");
    }

    @Test
    public void testCartaoEmbarqueCriadoCorretamente() {
        // Verifica se o cartão de embarque foi criado com os dados corretos
        assertEquals("Nome", cartaoEmbarque.getNomePassageiro());
        assertEquals("Sobrenome", cartaoEmbarque.getSobrenomePassageiro());
        assertEquals("AER", cartaoEmbarque.getOrigem());
        assertEquals("DEST", cartaoEmbarque.getDestino());
        assertEquals("CA1234", cartaoEmbarque.getCodigoVoo());
        assertEquals("10:00", cartaoEmbarque.getHoraEmbarque());
        assertEquals("2024-11-05", cartaoEmbarque.getDataEmbarque());
    }

    @Test
    public void testCartaoEmbarqueFormatoString() {
        // Verifica se a representação em string do cartão de embarque está correta
        String expectedString = "Cartão de Embarque: Nome Sobrenome\nVoo: CA1234\nOrigem: AER\nDestino: DEST\nHora: 10:00\nData: 2024-11-05";
        assertEquals(expectedString, cartaoEmbarque.toString());
    }
}
