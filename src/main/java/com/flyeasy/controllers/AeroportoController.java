package com.flyeasy.controllers;

import com.flyeasy.models.Aeroporto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AeroportoController {
    private static List<Aeroporto> aeroportos = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    // Inicializando com um aeroporto
    static {
        Aeroporto confins = new Aeroporto("Aeroporto Internacional de Confins", "CNF", "Confins", "Minas Gerais", "Brasil");
        aeroportos.add(confins);
    }

    // Método para cadastrar um novo aeroporto
    public static void cadastrarAeroporto() {
        System.out.print("Nome do Aeroporto: ");
        String nome = scanner.nextLine();

        String sigla;
        while (true) {
            System.out.print("Sigla (3 letras): ");
            sigla = scanner.nextLine().toUpperCase();
            if (sigla.length() == 3) {
                break;
            } else {
                System.out.println("Sigla deve ter 3 letras.");
            }
        }

        System.out.print("Cidade: ");
        String cidade = scanner.nextLine();

        System.out.print("Estado: ");
        String estado = scanner.nextLine();

        System.out.print("País: ");
        String pais = scanner.nextLine();

        // Usando o novo construtor da classe Aeroporto
        Aeroporto aeroporto = new Aeroporto(nome, sigla, cidade, estado, pais);
        aeroportos.add(aeroporto);
        System.out.println("\nAeroporto cadastrado com sucesso!");
    }

    // Método para listar os aeroportos
    public static void listarAeroportos() {
        if (aeroportos.isEmpty()) {
            System.out.println("Nenhum aeroporto cadastrado.");
        } else {
            System.out.println("\nLista de Aeroportos:");
            for (int i = 0; i < aeroportos.size(); i++) {
                Aeroporto aeroporto = aeroportos.get(i);
                System.out.printf("%d. Nome: %s, Sigla: %s, Cidade: %s, Estado: %s, País: %s%n",
                                  (i + 1), aeroporto.getNome(), aeroporto.getSigla(),
                                  aeroporto.getCidade(), aeroporto.getEstado(), aeroporto.getPais());
            }
        }
    }

    // Método para buscar um aeroporto pelo índice
    public static Aeroporto buscarAeroporto(int indice) {
        if (indice >= 1 && indice <= aeroportos.size()) {
            return aeroportos.get(indice - 1);
        }
        return null;
    }

    // Método para editar as informações de um aeroporto
    public static void editarAeroporto() {
        listarAeroportos();

        System.out.print("\nDigite o número do aeroporto que deseja editar: ");
        int escolha = scanner.nextInt();
        scanner.nextLine();

        Aeroporto aeroporto = buscarAeroporto(escolha);

        if (aeroporto == null) {
            System.out.println("Escolha inválida. Voltando ao menu principal.");
            return;
        }

        System.out.println("Editando Aeroporto: " + aeroporto.getNome());
        
        // Refatorado para permitir a edição
        System.out.print("Novo nome do Aeroporto (ou pressione Enter para manter o atual): ");
        String novoNome = scanner.nextLine();
        if (!novoNome.trim().isEmpty()) {
            aeroporto.setNome(novoNome);
        }

        System.out.print("Nova sigla do Aeroporto (ou pressione Enter para manter a atual): ");
        String novaSigla = scanner.nextLine().toUpperCase();
        if (novaSigla.length() == 3) {
            aeroporto.setSigla(novaSigla);
        }

        System.out.print("Nova cidade do Aeroporto (ou pressione Enter para manter a atual): ");
        String novaCidade = scanner.nextLine();
        if (!novaCidade.trim().isEmpty()) {
            aeroporto.setCidade(novaCidade);
        }

        System.out.print("Novo estado do Aeroporto (ou pressione Enter para manter o atual): ");
        String novoEstado = scanner.nextLine();
        if (!novoEstado.trim().isEmpty()) {
            aeroporto.setEstado(novoEstado);
        }

        System.out.print("Novo país do Aeroporto (ou pressione Enter para manter o atual): ");
        String novoPais = scanner.nextLine();
        if (!novoPais.trim().isEmpty()) {
            aeroporto.setPais(novoPais);
        }

        System.out.println("\nAeroporto atualizado com sucesso!");
    }

    // Método para deletar um aeroporto
    public static void deletarAeroporto() {
        listarAeroportos();

        System.out.print("\nDigite o número do aeroporto que deseja deletar: ");
        int escolha = scanner.nextInt();
        scanner.nextLine();

        Aeroporto aeroporto = buscarAeroporto(escolha);

        if (aeroporto == null) {
            System.out.println("Escolha inválida. Voltando ao menu principal.");
            return;
        }

        aeroportos.remove(aeroporto);
        System.out.println("Aeroporto deletado com sucesso!");
    }
}
