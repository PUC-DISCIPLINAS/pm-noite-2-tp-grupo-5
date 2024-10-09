package controller;

import model.DiaSemana;
import model.Voo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class VooControllerTest {
    private VooController vooController;

    @BeforeEach
    public void setUp() {
        vooController = new VooController();
    }

    @Test
    public void testCadastrarVoo() {
        vooController.cadastrarVoo("AD4114", "VCD", "CNF", LocalTime.of(10, 30), Duration.ofMinutes(70), Arrays.asList(DiaSemana.SEGUNDA, DiaSemana.TERCA));

        Voo voo = vooController.buscarVooPorCodigo("AD4114");
        assertNotNull(voo);
        assertEquals("AD4114", voo.getCodigo());
        assertEquals("VCD", voo.getOrigem());
        assertEquals("CNF", voo.getDestino());
        assertEquals(LocalTime.of(10, 30), voo.getHorarioPartida());
        assertEquals(Duration.ofMinutes(70), voo.getDuracao());
        assertTrue(voo.getDiasSemana().contains(DiaSemana.SEGUNDA));
        assertFalse(voo.getDiasSemana().contains(DiaSemana.QUARTA));
    }

    @Test
    public void testBuscarVooPorCodigo() {
        vooController.cadastrarVoo("AD4115", "GIG", "GRU", LocalTime.of(9, 0), Duration.ofMinutes(90), Arrays.asList(DiaSemana.SEXTA));

        Voo voo = vooController.buscarVooPorCodigo("AD4115");
        assertNotNull(voo);
    }

    @Test
    public void testVooOcorreNoDia() {
        vooController.cadastrarVoo("AD4116", "CGH", "POA", LocalTime.of(12, 0), Duration.ofMinutes(120), Arrays.asList(DiaSemana.QUINTA, DiaSemana.DOMINGO));

        assertTrue(vooController.vooOcorreNoDia("AD4116", DiaSemana.QUINTA));
        assertFalse(vooController.vooOcorreNoDia("AD4116", DiaSemana.SEGUNDA));
    }
}
