package controllers;

import models.Aeronave;
import services.CalculadorService;
import services.SistemaLogsService;

public class MainController {
    public void executar() {
        SistemaLogsService logs = SistemaLogsService.getInstancia();

        // Dados de exemplo
        double lat1 = -23.5505, lon1 = -46.6333; // São Paulo
        double lat2 = 40.7128, lon2 = -74.0060; // Nova York
        Aeronave aeronave = new Aeronave(900); // 900 km/h

        // Cálculo da distância
        double distancia = CalculadorService.calcularDistancia(lat1, lon1, lat2, lon2);
        logs.registrarLog("Distância calculada: " + distancia + " km");

        // Cálculo do tempo
        double tempo = CalculadorService.calcularTempoViagem(distancia, aeronave.getVelocidadeMedia());
        logs.registrarLog("Tempo de viagem calculado: " + tempo + " horas");

        // Exibir resultados
        System.out.println("Distância: " + distancia + " km");
        System.out.println("Tempo de viagem: " + tempo + " horas");
        System.out.println("\nLOGS:");
        System.out.println(logs.obterLogs());
    }
}
