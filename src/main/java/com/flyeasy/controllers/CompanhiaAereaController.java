package com.flyeasy.controllers;

import com.flyeasy.views.*;
import com.flyeasy.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CompanhiaAereaController {
    private static List<CompanhiaAerea> companhiasAereas = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void inicializarCompanhiasAereas() {
        CompanhiaAerea exemplo = new CompanhiaAerea(
            "FlyEasy Airlines",       
            "FEA123",                 
            "FlyEasy Ltda",           
            "12.345.678/0001-99",    
            30.0,
            60.0   
        );
        companhiasAereas.add(exemplo);
    }

    public static void cadastrarCompanhiaAerea() {
        System.out.println("Cadastro de Companhia Aérea:");
        
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Código: ");
        String codigo = scanner.nextLine();

        System.out.print("Razão Social: ");
        String razaoSocial = scanner.nextLine();

        System.out.print("CNPJ: ");
        String cnpj = scanner.nextLine();

        System.out.print("Valor da Primeira Bagagem: ");
        double valorPrimeiraBagagem = scanner.nextDouble();
        scanner.nextLine(); 

        System.out.print("Valor das Bagagens Adicionais: ");
        double valorBagagemAdicional = scanner.nextDouble();
        scanner.nextLine(); 

        CompanhiaAerea companhia = new CompanhiaAerea(nome, codigo, razaoSocial, cnpj,
                                                       valorPrimeiraBagagem, valorBagagemAdicional);
        companhiasAereas.add(companhia);

        TerminalView.displayMessage("Companhia aérea cadastrada com sucesso!");
    }

    public static void listarCompanhiasAereas() {
        if (companhiasAereas.isEmpty()) {
            System.out.println("Nenhuma companhia aérea cadastrada.");
            return;
        }
        System.out.println("Companhias Aéreas cadastradas:");
        for (int i = 0; i < companhiasAereas.size(); i++) {
            CompanhiaAerea companhia = companhiasAereas.get(i);
            System.out.printf("%d. Nome: %s, Código: %s, Razão Social: %s, CNPJ: %s, " +
                              "Valor Primeira Bagagem: R$ %.2f, Valor Bagagem Adicional: R$ %.2f%n",
                            (i + 1), companhia.getNome(), companhia.getCodigo(),
                            companhia.getRazaoSocial(), companhia.getCnpj(),
                            companhia.getValorPrimeiraBagagem(), companhia.getValorBagagemAdicional());
        }
    }

    public static void editarCompanhiaAerea() {
        if (companhiasAereas.isEmpty()) {
            System.out.println("Nenhuma companhia aérea disponível para editar.");
            return;
        }

        listarCompanhiasAereas();
        System.out.print("\nDigite o número da companhia aérea que deseja editar: ");
        int escolha = scanner.nextInt();
        scanner.nextLine(); 

        if (escolha < 1 || escolha > companhiasAereas.size()) {
            System.out.println("Escolha inválida. Voltando ao menu principal.");
            return;
        }

        CompanhiaAerea companhia = companhiasAereas.get(escolha - 1);
        MenuController.editarInformacoesCompanhiaAerea(companhia);
    }

    public static void deletarCompanhiaAerea() {
        listarCompanhiasAereas();
        System.out.print("Digite o número da companhia aérea que deseja deletar: ");
        int escolha = scanner.nextInt();
        scanner.nextLine();

        if (escolha < 1 || escolha > companhiasAereas.size()) {
            TerminalView.displayMessage("Escolha inválida.");
            return;
        }

        companhiasAereas.remove(escolha - 1);
        TerminalView.displayMessage("Companhia aérea deletada com sucesso!");
    }
}
