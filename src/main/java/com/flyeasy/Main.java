package com.flyeasy;

import com.flyeasy.controllers.*;
import com.flyeasy.views.*;
import com.flyeasy.models.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        UsuarioController.inicializarUsuarios();
        CompanhiaAereaController.inicializarCompanhiasAereas();
        PassagemController.inicializarPassagens();

        boolean continuar = true;

        while (continuar) {
            boolean loggedIn = false;
            String loggedUsername = null;

            while (!loggedIn) {
                TerminalView.displayHeader("\nBem-vindo ao FlyEasy!");
                System.out.println("1. Pesquisar Passagens Aéreas");
                System.out.println("2. Fazer login (Setor Administrativo)");
                System.out.println("3. Fechar terminal");
                System.out.print("Escolha uma opção dentro de nosso sistema: ");
                
                int escolha = scanner.nextInt();
                scanner.nextLine(); 

                switch (escolha) {
                    case 1:
                        pesquisarPassagens(scanner);
                        break;
                    case 2:
                        loggedIn = LoginController.realizarLogin();
                        if (loggedIn) {
                            loggedUsername = LoginController.getLoggedUsername();
                        }
                        break;
                    case 3:
                        TerminalView.displayMessage("Fechando o terminal...");
                        continuar = false;
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                        break;
                }
            }

            boolean menuContinuar = true;
            while (menuContinuar && loggedIn) {
                MenuController.exibirMenu(loggedUsername);
                System.out.print("\nEscolha uma opção: ");
                int opcao = scanner.nextInt();
                System.out.print("\n");
                scanner.nextLine(); 

                menuContinuar = MenuController.processarOpcao(opcao, loggedUsername);
            }

            TerminalView.displaySeparation();
            TerminalView.displayMessage("Você saiu do sistema. Até mais!");
            TerminalView.displaySeparation();
        }

        scanner.close(); 
    }

    private static void pesquisarPassagens(Scanner scanner) {
        System.out.println("Informe o aeroporto de origem:");
        Aeroporto aeroportoOrigem = selecionarAeroporto(scanner);

        System.out.println("Informe o aeroporto de destino:");
        Aeroporto aeroportoDestino = selecionarAeroporto(scanner);
        
        System.out.print("Informe a data do voo (formato: dd/MM/yyyy, ou pressione ENTER para ignorar): ");
        String dataInput = scanner.nextLine();
        Date data = null;

        if (!dataInput.isEmpty()) {
            try {
                data = new SimpleDateFormat("dd/MM/yyyy").parse(dataInput);
            } catch (ParseException e) {
                System.out.println("Data inválida. Pesquisa será realizada sem a data.");
            }
        }

        List<PassagemAerea> resultados = PassagemController.pesquisarPassagens(aeroportoOrigem, aeroportoDestino, data);
        if (resultados.isEmpty()) {
            System.out.println("Nenhuma passagem encontrada.");
        } else {
            System.out.println("Passagens encontradas:");
            for (PassagemAerea passagem : resultados) {
                System.out.println("Voo: " + passagem.getCodigoVoo() + ", Companhia: " + passagem.getCompanhiaAerea().getNome() +
                        ", Preço: " + passagem.getPreco() + " " + passagem.getMoeda() +
                        ", Preço com taxas: " + passagem.getPrecoComTaxas() + " " + passagem.getMoeda() +
                        ", Preço em Reais: " + passagem.getPrecoEmReais() + " BRL");
            }
        }
    }

    private static Aeroporto selecionarAeroporto(Scanner scanner) {
        AeroportoController.listarAeroportos();
        System.out.print("Digite o número do aeroporto desejado (ou pressione ENTER para ignorar): ");
        String escolha = scanner.nextLine();

        if (escolha.isEmpty()) {
            return null;
        } else {
            try {
                int indice = Integer.parseInt(escolha);
                return AeroportoController.buscarAeroporto(indice);
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida. Aeroporto não selecionado.");
                return null;
            }
        }
    }
}
