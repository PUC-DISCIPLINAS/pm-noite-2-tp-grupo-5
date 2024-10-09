package com.flyeasy.controllers;

import com.flyeasy.models.*;

import java.io.Console;
import java.util.Scanner;

public class LoginController {
    private static String loggedUsername;
    private static Console console = System.console();
    private static Scanner scanner = new Scanner(System.in);

    public static boolean realizarLogin() {
        System.out.print("\nDigite o nome de usuário para login: ");
        String username = scanner.nextLine();

        System.out.print("Digite a senha: ");
        String senha = readPassword();

        Usuario usuario = UsuarioController.buscarUsuario(username);
        if (usuario != null && usuario.verificarSenha(senha)) {
            loggedUsername = username;
            System.out.println("\nLogin realizado com sucesso! Bem-vindo, " + username);
            return true;
        }

        System.out.println("\nUsuário ou senha incorretos.");
        return false;
    }

    public static String getLoggedUsername() {
        return loggedUsername;
    }

    public static String readPassword() {
        if (console != null) {
            char[] passwordArray = console.readPassword("Digite a senha: ");
            return new String(passwordArray);
        } else {
            return scanner.nextLine();
        }
    }
}
