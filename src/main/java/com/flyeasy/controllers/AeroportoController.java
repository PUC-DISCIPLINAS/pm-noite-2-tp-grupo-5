package com.flyeasy.controllers;

import com.flyeasy.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AeroportoController {
    private static List<Aeroporto> aeroportos = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    static {
        Aeroporto confins = new Aeroporto();
        confins.setNome("Aeroporto Internacional de Confins");
        confins.setSigla("CNF");
        confins.setCidade("Confins");
        confins.setEstado("Minas Gerais");
        confins.setPais("Brasil");
        aeroportos.add(confins);
    }

    public static void cadastrarAeroporto() {
        Aeroporto aeroporto = new Aeroporto();

        System.out.print("Nome do Aeroporto: ");
        aeroporto.setNome(scanner.nextLine());

        String sigla;
        while (true) {
            System.out.print("Sigla (3 letras): ");
            sigla = scanner.nextLine().toUpperCase();
            if (sigla.length() == 3) {
                aeroporto.setSigla(sigla);
                break;
            } else {
                System.out.println("Sigla deve ter 3 letras.");
            }
        }

        System.out.print("Cidade: ");
        aeroporto.setCidade(scanner.nextLine());

        System.out.print("Estado: ");
        aeroporto.setEstado(scanner.nextLine());

        System.out.print("País: ");
        aeroporto.setPais(scanner.nextLine());

        aeroportos.add(aeroporto);
        System.out.println("\nAeroporto cadastrado com sucesso!");
    }

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

    public static Aeroporto buscarAeroporto(int indice) {
        if (indice >= 1 && indice <= aeroportos.size()) {
            return aeroportos.get(indice - 1);
        }
        return null;
    }

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
        
        MenuController.editarInformacoesAeroporto(aeroporto);
    }

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
