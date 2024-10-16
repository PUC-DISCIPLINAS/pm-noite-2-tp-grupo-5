package controller;

import model.Voo;
import model.DiaSemana;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class VooController {
    private List<Voo> voos; // Lista que armazenará todos os voos cadastrados

    public VooController() {
        this.voos = new ArrayList<>(); // Inicializa a lista de voos
    }

    /**
     * Método para cadastrar um novo voo.
     * @param codigo Código identificador do voo
     * @param origem Cidade de origem do voo
     * @param destino Cidade de destino do voo
     * @param horarioPartida Horário de partida do voo
     * @param duracao Duração do voo
     * @param diasSemana Lista com os dias da semana em que o voo ocorre
     */
    public void cadastrarVoo(String codigo, String origem, String destino, LocalTime horarioPartida, Duration duracao, List<DiaSemana> diasSemana) {
        Voo novoVoo = new Voo(codigo, origem, destino, horarioPartida, duracao, diasSemana); // Cria um novo objeto Voo
        voos.add(novoVoo); // Adiciona o voo à lista
    }

    /**
     * Método para listar todos os voos cadastrados.
     * @return Lista de todos os voos cadastrados
     */
    public List<Voo> listarVoos() {
        return voos; // Retorna a lista de voos cadastrados
    }

    /**
     * Método para buscar um voo pelo código.
     * @param codigo Código do voo a ser buscado
     * @return O objeto Voo correspondente, ou null se não encontrado
     */
    public Voo buscarVooPorCodigo(String codigo) {
        return voos.stream()
                   .filter(voo -> voo.getCodigo().equals(codigo)) // Filtra a lista para encontrar o voo com o código correspondente
                   .findFirst()
                   .orElse(null); // Retorna o primeiro voo encontrado ou null se nenhum for encontrado
    }

    /**
     * Método para verificar se um voo ocorre em um dia específico da semana.
     * @param codigo Código do voo a ser verificado
     * @param dia Dia da semana a ser verificado
     * @return true se o voo ocorrer no dia, false caso contrário
     */
    public boolean vooOcorreNoDia(String codigo, DiaSemana dia) {
        Voo voo = buscarVooPorCodigo(codigo); // Busca o voo pelo código
        if (voo != null) { // Se o voo foi encontrado
            return voo.getDiasSemana().contains(dia); // Verifica se o dia está na lista de dias da semana do voo
        }
        return false; // Retorna false se o voo não ocorre no dia
    }
}

