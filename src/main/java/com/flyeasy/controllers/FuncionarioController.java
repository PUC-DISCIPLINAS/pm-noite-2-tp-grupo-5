package com.flyeasy.controllers;

import com.flyeasy.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FuncionarioController {
    private static List<Funcionario> funcionarios = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void cadastrarFuncionario() {
        System.out.println("Cadastro de Funcionário:");

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        System.out.print("E-mail: ");
        String email = scanner.nextLine();

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = LoginController.readPassword();

        Funcionario funcionario = new Funcionario();
        funcionario.setNome(nome);
        funcionario.setCpf(cpf);
        funcionario.setEmail(email);
        funcionario.setUsername(username);
        funcionario.setSenha(senha);

        funcionarios.add(funcionario);

        Usuario usuario = new Usuario();
        usuario.setUsername(username); 
        usuario.setSenha(senha); 
        UsuarioController.adicionarUsuario(usuario); 

        System.out.println("Funcionário cadastrado com sucesso e usuário associado criado!");
    }

    public static void listarFuncionarios() {
        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado.");
            return;
        }

        System.out.println("Funcionários cadastrados:");
        for (int i = 0; i < funcionarios.size(); i++) {
            Funcionario funcionario = funcionarios.get(i);
            System.out.printf("%d. Nome: %s, CPF: %s, E-mail: %s, Username: %s%n",
                    (i + 1), funcionario.getNome(),
                    funcionario.getCpf(), funcionario.getEmail(),
                    funcionario.getUsername());
        }
    }

    public static void editarFuncionario() {
        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionário disponível para editar.");
            return;
        }

        listarFuncionarios();
        System.out.print("\nDigite o número do funcionário que deseja editar: ");
        int escolha = scanner.nextInt();
        scanner.nextLine();

        if (escolha < 1 || escolha > funcionarios.size()) {
            System.out.println("Escolha inválida. Voltando ao menu principal.");
            return;
        }

        Funcionario funcionario = funcionarios.get(escolha - 1);
        MenuController.editarInformacoesFuncionario(funcionario);
    }

    public static void deletarFuncionario() {
        listarFuncionarios();
        System.out.print("Digite o número do funcionário que deseja deletar: ");
        int escolha = scanner.nextInt();
        scanner.nextLine();

        if (escolha < 1 || escolha > funcionarios.size()) {
            System.out.println("Escolha inválida.");
            return;
        }

        Funcionario funcionario = funcionarios.get(escolha - 1);
        
        Usuario usuario = UsuarioController.buscarUsuario(funcionario.getUsername());
        if (usuario != null) {
            UsuarioController.deletarUsuario(usuario);
        }

        funcionarios.remove(escolha - 1);
        System.out.println("Funcionário e usuário deletados com sucesso!");
    }
}
