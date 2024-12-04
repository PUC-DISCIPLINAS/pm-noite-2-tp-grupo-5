package com.flyeasy.controllers;
import com.flyeasy.models.*;

import java.util.ArrayList;
import java.util.Scanner;

public class UsuarioController {
    private static ArrayList<Usuario> usuarios = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void inicializarUsuarios() {
        Usuario adminUsuario = new Usuario();
        adminUsuario.setUsername("admin");
        adminUsuario.setSenha("admin");  // A senha será automaticamente criptografada
        usuarios.add(adminUsuario);
    }

    public static void cadastrarUsuario() {
        System.out.print("Digite o nome para o login: ");
        String username = scanner.nextLine();

        System.out.print("Digite a senha: ");
        String senha = LoginController.readPassword();

        Usuario usuario = new Usuario();
        usuario.setUsername(username);
        usuario.setSenha(senha);  // A senha é automaticamente criptografada
        usuarios.add(usuario);
        System.out.println("\nUsuário cadastrado com sucesso!");
    }

    public static void listarUsuarios() {
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
        } else {
            System.out.println("\nLista de Usuários:");
            for (Usuario usuario : usuarios) {
                System.out.println("Usuário: " + usuario.getUsername() + ", Senha: [PROTEGIDA]");
            }
        }
    }

    public static Usuario buscarUsuario(String username) {
        return usuarios.stream().filter(u -> u.getUsername().equals(username)).findFirst().orElse(null);
    }

    public static void editarUsuario() {
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário disponível para editar.");
            return;
        }

        System.out.println("Lista de Usuários:");
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario usuario = usuarios.get(i);
            System.out.println((i + 1) + ". " + usuario.getUsername());
        }

        System.out.print("\nDigite o número do usuário que deseja editar: ");
        int escolha = scanner.nextInt();
        scanner.nextLine();
        System.out.print("\n");

        if (escolha < 1 || escolha > usuarios.size()) {
            System.out.println("Escolha inválida. Voltando ao menu principal.");
            return;
        }

        Usuario usuario = usuarios.get(escolha - 1);
        MenuController.editarInformacoesUsuario(usuario);
    }

    public static void deletarUsuario() {
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário disponível para deletar.");
            return;
        }

        System.out.println("Lista de Usuários:");
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario usuario = usuarios.get(i);
            System.out.println((i + 1) + ". " + usuario.getUsername());
        }

        System.out.print("\nDigite o número do usuário que deseja deletar: ");
        int escolha = scanner.nextInt();
        scanner.nextLine();
        System.out.print("\n");

        if (escolha < 1 || escolha > usuarios.size()) {
            System.out.println("Escolha inválida. Voltando ao menu principal.");
            return;
        }

        usuarios.remove(escolha - 1);
        System.out.println("Usuário deletado com sucesso!");
    }

    public static void adicionarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public static ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public static void deletarUsuario(Usuario usuario) {
        usuarios.remove(usuario);
        System.out.println("Usuário deletado com sucesso!");
    }
}
