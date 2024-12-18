import com.flyeasy.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PassagemAereaTest {

    private PassagemAerea passagemAerea;
    private Aeroporto aeroportoOrigem;
    private Aeroporto aeroportoDestino;
    private CompanhiaAerea companhia;

    @BeforeEach
    public void setUp() {
        TestLogger.log("Iniciando configuração do teste: setUp");

        aeroportoOrigem = new Aeroporto(
                "Aeroporto Internacional de São Paulo", 
                "GRU", 
                "São Paulo", 
                "SP", 
                "Brasil", 
                TipoVoo.INTERNACIONAL, 
                -23.435556, 
                -46.473056
        );

        aeroportoDestino = new Aeroporto(
                "Aeroporto Internacional de Lisboa", 
                "LIS", 
                "Lisboa", 
                "Lisboa", 
                "Portugal", 
                TipoVoo.INTERNACIONAL, 
                38.774167, 
                -9.134167
        );

        companhia = new CompanhiaAerea("TAP Portugal", "TP", "TAP", "12345678000100", 100.0, 50.0);
        Date dataHoraVoo = new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 48)); // Voo daqui a 48 horas

        passagemAerea = new PassagemAerea(
                aeroportoOrigem,
                aeroportoDestino,
                dataHoraVoo,
                "TP1020",
                companhia,
                2000.0,
                3500.0,
                5000.0,
                "USD"
        );

        TestLogger.log("Configuração do teste setUp concluída.");
    }

    @Test
    public void testValoresIniciais() {
        TestLogger.log("Iniciando teste: testValoresIniciais");

        assertNotNull(passagemAerea.getAeroportoOrigem(), "O aeroporto de origem não deve ser nulo.");
        assertNotNull(passagemAerea.getAeroportoDestino(), "O aeroporto de destino não deve ser nulo.");
        assertNotNull(passagemAerea.getDataHoraVoo(), "A data e hora do voo não devem ser nulas.");
        assertEquals("TP1020", passagemAerea.getCodigoVoo(), "O código do voo está incorreto.");
        assertEquals(2000.0, passagemAerea.getTarifaBasica(), "A tarifa básica está incorreta.");
        assertEquals("USD", passagemAerea.getMoeda(), "A moeda está incorreta.");

        TestLogger.log("Teste testValoresIniciais concluído com sucesso.");
    }

    @Test
    public void testReservarAssento() {
        TestLogger.log("Iniciando teste: testReservarAssento");

        assertTrue(passagemAerea.reservarAssento("A1"), "O assento A1 deveria estar disponível para reserva.");
        assertFalse(passagemAerea.reservarAssento("A1"), "O assento A1 não deveria estar disponível após ser reservado.");
        assertFalse(passagemAerea.reservarAssento("Z1"), "Assentos inexistentes não devem ser reserváveis.");

        TestLogger.log("Teste testReservarAssento concluído com sucesso.");
    }

    @Test
    public void testLatitudeLongitudeAeroportos() {
        TestLogger.log("Iniciando teste: testLatitudeLongitudeAeroportos");

        assertEquals(-23.435556, aeroportoOrigem.getLatitude(), 0.0001, "A latitude do aeroporto de origem está incorreta.");
        assertEquals(-46.473056, aeroportoOrigem.getLongitude(), 0.0001, "A longitude do aeroporto de origem está incorreta.");
        assertEquals(38.774167, aeroportoDestino.getLatitude(), 0.0001, "A latitude do aeroporto de destino está incorreta.");
        assertEquals(-9.134167, aeroportoDestino.getLongitude(), 0.0001, "A longitude do aeroporto de destino está incorreta.");

        TestLogger.log("Teste testLatitudeLongitudeAeroportos concluído com sucesso.");
    }

    @Test
    public void testDistanciaEntreAeroportos() {
        TestLogger.log("Iniciando teste: testDistanciaEntreAeroportos");

        double distancia = aeroportoOrigem.calcularDistanciaKm(aeroportoDestino);
        assertTrue(distancia > 0, "A distância entre os aeroportos deve ser maior que 0.");
        assertEquals(7935, Math.round(distancia), "A distância entre os aeroportos está incorreta.");

        TestLogger.log("Teste testDistanciaEntreAeroportos concluído com sucesso.");
    }

    @Test
    public void testCheckInValido() {
        TestLogger.log("Iniciando teste: testCheckInValido");

        // Configurar data do voo para 23 horas a partir de agora (dentro do período permitido)
        passagemAerea.setDataHoraVoo(new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 23))); 
    
        // Verificar se é possível realizar o check-in e se ele ocorre com sucesso
        assertTrue(passagemAerea.podeRealizarCheckIn(), "Deveria ser possível realizar o check-in no período correto.");
        assertTrue(passagemAerea.realizarCheckIn(), "O check-in deveria ser realizado com sucesso.");

        TestLogger.log("Teste testCheckInValido concluído com sucesso.");
    }
    
    @Test
    public void testCheckInForaDoPeriodo() {
        TestLogger.log("Iniciando teste: testCheckInForaDoPeriodo");

        passagemAerea.setDataHoraVoo(new Date(System.currentTimeMillis() - (1000 * 60 * 60))); // Voo já ocorreu
        assertFalse(passagemAerea.podeRealizarCheckIn(), "Não deveria ser possível realizar check-in para voos passados.");
        assertFalse(passagemAerea.realizarCheckIn(), "O check-in não deveria ser realizado fora do período permitido.");

        TestLogger.log("Teste testCheckInForaDoPeriodo concluído com sucesso.");
    }

    @Test
    public void testPrecoComTaxas() {
        TestLogger.log("Iniciando teste: testPrecoComTaxas");

        double precoComTaxas = passagemAerea.getPrecoComTaxas();
        double tarifaLucro = passagemAerea.calcularTarifaLucro();
        assertEquals(2000.0 + tarifaLucro, precoComTaxas, "O preço com taxas não foi calculado corretamente.");

        TestLogger.log("Teste testPrecoComTaxas concluído com sucesso.");
    }

    @Test
    public void testPrecoEmReais() {
        TestLogger.log("Iniciando teste: testPrecoEmReais");

        double precoEmReais = passagemAerea.getPrecoEmReais();
        assertEquals(2000.0 * 5.0, precoEmReais, "A conversão do preço para reais está incorreta.");

        TestLogger.log("Teste testPrecoEmReais concluído com sucesso.");
    }

    @Test
    public void testRegistrarNoShow() {
        TestLogger.log("Iniciando teste: testRegistrarNoShow");

        passagemAerea.setDataHoraVoo(new Date(System.currentTimeMillis() - (1000 * 60 * 60))); // Voo já ocorreu
        passagemAerea.registrarNoShow(); // Deve registrar no show
        assertFalse(passagemAerea.realizarCheckIn(), "Check-in não deve ser possível após o voo.");

        TestLogger.log("Teste testRegistrarNoShow concluído com sucesso.");
    }

    @Test
    public void testAssentosDisponiveis() {
        TestLogger.log("Iniciando teste: testAssentosDisponiveis");

        Map<String, Boolean> assentos = passagemAerea.getAssentosDisponiveis();
        assertNotNull(assentos, "A lista de assentos não deve ser nula.");
        assertEquals(10, assentos.size(), "A quantidade de assentos disponíveis deve ser 10 inicialmente.");

        TestLogger.log("Teste testAssentosDisponiveis concluído com sucesso.");
    }
}