package services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SistemaLogsService {
    private static SistemaLogsService instancia;
    private StringBuilder logs;

    private SistemaLogsService() {
        logs = new StringBuilder();
    }

    public static SistemaLogsService getInstancia() {
        if (instancia == null) {
            instancia = new SistemaLogsService();
        }
        return instancia;
    }

    public void registrarLog(String operacao) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        logs.append(timestamp).append(" - ").append(operacao).append("\n");
    }

    public String obterLogs() {
        return logs.toString();
    }
}
