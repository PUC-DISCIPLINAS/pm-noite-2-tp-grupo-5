package com.flyeasy.controllers;

import com.flyeasy.views.*;
import com.flyeasy.models.*;

import java.util.Scanner;

public class MenuController {
    private static Scanner scanner = new Scanner(System.in);

    public static void exibirMenu(String loggedUsername) {
        Usuario usuario = UsuarioController.buscarUsuario(loggedUsername);
        String menuContent;
        menuContent = "Escolha uma opção:\n" +
                      "1. Usuários\n" +
                      "2. Companhia Aérea\n" +
                      "3. Aeroporto\n" +
                      "4. Funcionário\n" +
                      "5. Sair";              

        TerminalView.displayMenu(menuContent);
    }

    public static boolean processarOpcao(int opcao, String loggedUsername) {
        Usuario usuario = UsuarioController.buscarUsuario(loggedUsername);
        switch (opcao) {
            case 1:
                exibirMenuUsuarios();
                break;
            case 2:
                exibirMenuCompanhiaAerea();
                break;
            case 3:
                exibirMenuAeroporto();
                break;
            case 4:
                exibirMenuFuncionario();
                break;
            case 5:
                return false; 
            default:
                TerminalView.displayMessage("Opção inválida. Tente novamente.");
        }
        return true;
    }

    private static void exibirMenuUsuarios() {
        boolean continuar = true;
        while (continuar) {
            String menuContent = "Escolha uma opção:\n" +
                                 "1. Cadastrar Usuário\n" +
                                 "2. Listar Usuários\n" +
                                 "3. Editar Usuário\n" +
                                 "4. Deletar Usuário\n" +
                                 "5. Voltar ao Menu Principal";
            TerminalView.displayMenu(menuContent);

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    UsuarioController.cadastrarUsuario();
                    break;
                case 2:
                    UsuarioController.listarUsuarios();
                    break;
                case 3:
                    UsuarioController.editarUsuario();
                    break;
                case 4:
                    UsuarioController.deletarUsuario(); 
                    break;
                case 5:
                    continuar = false;
                    break;
                default:
                    TerminalView.displayMessage("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void exibirMenuCompanhiaAerea() {
        boolean continuar = true;
        while (continuar) {
            String menuContent = "Escolha uma opção:\n" +
                                 "1. Cadastrar Companhia Aérea\n" +
                                 "2. Listar Companhias Aéreas\n" +
                                 "3. Editar Companhia Aérea\n" +
                                 "4. Deletar Companhia Aérea\n" +
                                 "5. Voltar ao Menu Principal";
            TerminalView.displayMenu(menuContent);

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    CompanhiaAereaController.cadastrarCompanhiaAerea();
                    break;
                case 2:
                    CompanhiaAereaController.listarCompanhiasAereas();
                    break;
                case 3:
                    CompanhiaAereaController.editarCompanhiaAerea(); 
                    break;
                case 4:
                    CompanhiaAereaController.deletarCompanhiaAerea(); 
                    break;
                case 5:
                    continuar = false;
                    break;
                default:
                    TerminalView.displayMessage("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void exibirMenuAeroporto() {
        boolean continuar = true;
        while (continuar) {
            String menuContent = "Escolha uma opção:\n" +
                                 "1. Cadastrar Aeroporto\n" +
                                 "2. Listar Aeroportos\n" +
                                 "3. Editar Aeroporto\n" +
                                 "4. Deletar Aeroporto\n" +
                                 "5. Voltar ao Menu Principal";
            TerminalView.displayMenu(menuContent);

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    AeroportoController.cadastrarAeroporto(); 
                    break;
                case 2:
                    AeroportoController.listarAeroportos(); 
                    break;
                case 3:
                    AeroportoController.editarAeroporto(); 
                    break;
                case 4:
                    AeroportoController.deletarAeroporto(); 
                    break;
                case 5:
                    continuar = false;
                    break;
                default:
                    TerminalView.displayMessage("Opção inválida. Tente novamente.");
            }
        }
    }
    
    private static void exibirMenuFuncionario() {
        boolean continuar = true;
        while (continuar) {
            String menuContent = "Escolha uma opção:\n" +
                                 "1. Cadastrar Funcionário\n" +
                                 "2. Listar Funcionários\n" +
                                 "3. Editar Funcionário\n" +
                                 "4. Deletar Funcionário\n" +
                                 "5. Voltar ao Menu Principal";
            TerminalView.displayMenu(menuContent);

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    FuncionarioController.cadastrarFuncionario();
                    break;
                case 2:
                    FuncionarioController.listarFuncionarios();
                    break;
                case 3:
                    FuncionarioController.editarFuncionario();
                    break;
                case 4:
                    FuncionarioController.deletarFuncionario(); 
                    break;
                case 5:
                    continuar = false;
                    break;
                default:
                    TerminalView.displayMessage("Opção inválida. Tente novamente.");
            }
        }
    }

    public static void editarInformacoesUsuario(Usuario usuario) {
        boolean editing = true;

        while (editing) {
            TerminalView.displayMessage("Escolha o que deseja editar:");
            String editOptions = "1. Nome de Usuário\n" +
                                 "2. Senha\n" +
                                 "3. Voltar";
            TerminalView.displayMenu(editOptions);

            int escolha = scanner.nextInt();
            scanner.nextLine();
            System.out.print("\n");

            switch (escolha) {
                case 1:
                    System.out.print("Novo nome de usuário para login: ");
                    usuario.setUsername(scanner.nextLine());
                    break;
                case 2:
                    System.out.print("Nova senha: ");
                    char[] newPassword = System.console().readPassword(); 
                    usuario.setSenha(new String(newPassword));
                    break;
                case 3:
                    editing = false;
                    break;
                default:
                    TerminalView.displayMessage("Opção inválida.");
            }
        }
    }

   public static void editarInformacoesCompanhiaAerea(CompanhiaAerea companhia) {
        boolean editing = true;

        while (editing) {
            TerminalView.displayMessage("Escolha o que deseja editar:");
            String editOptions = "1. Nome da Companhia\n" +
                                 "2. Código\n" +
                                 "3. Razão Social\n" +
                                 "4. CNPJ\n" +
                                 "5. Valor da Primeira Bagagem\n" +
                                 "6. Valor das Bagagens Adicionais\n" +
                                 "7. Voltar";
            TerminalView.displayMenu(editOptions);

            int escolha = scanner.nextInt();
            scanner.nextLine();
            System.out.print("\n");

            switch (escolha) {
                case 1:
                    System.out.print("Novo nome da companhia: ");
                    companhia.setNome(scanner.nextLine());
                    break;
                case 2:
                    System.out.print("Novo código da companhia: ");
                    companhia.setCodigo(scanner.nextLine());
                    break;
                case 3:
                    System.out.print("Nova razão social da companhia: ");
                    companhia.setRazaoSocial(scanner.nextLine());
                    break;
                case 4:
                    System.out.print("Novo CNPJ da companhia: ");
                    companhia.setCnpj(scanner.nextLine());
                    break;
                case 5:
                    System.out.print("Novo valor da primeira bagagem: ");
                    double novoValorPrimeiraBagagem = scanner.nextDouble();
                    scanner.nextLine(); 
                    companhia.setValorPrimeiraBagagem(novoValorPrimeiraBagagem);
                    break;
                case 6:
                    System.out.print("Novo valor das bagagens adicionais: ");
                    double novoValorBagagemAdicional = scanner.nextDouble();
                    scanner.nextLine();
                    companhia.setValorBagagemAdicional(novoValorBagagemAdicional);
                    break;
                case 7:
                    editing = false;
                    break;
                default:
                    TerminalView.displayMessage("Opção inválida.");
            }
        }
    }

    public static void editarInformacoesAeroporto(Aeroporto aeroporto) {
        boolean editing = true;

        while (editing) {
            TerminalView.displayMessage("Escolha o que deseja editar:");
            String editOptions = "1. Nome do Aeroporto\n" +
                                 "2. Sigla\n" +
                                 "3. Cidade\n" +
                                 "4. Estado\n" +
                                 "5. País\n" +
                                 "6. Voltar";
            TerminalView.displayMenu(editOptions);

            int escolha = scanner.nextInt();
            scanner.nextLine();
            System.out.print("\n");

            switch (escolha) {
                case 1:
                    System.out.print("Novo nome do aeroporto: ");
                    aeroporto.setNome(scanner.nextLine());
                    break;
                case 2:
                    System.out.print("Nova sigla do aeroporto (3 letras): ");
                    String novaSigla = scanner.nextLine().toUpperCase();
                    if (novaSigla.length() == 3) {
                        aeroporto.setSigla(novaSigla);
                    } else {
                        TerminalView.displayMessage("A sigla deve ter 3 letras.");
                    }
                    break;
                case 3:
                    System.out.print("Nova cidade do aeroporto: ");
                    aeroporto.setCidade(scanner.nextLine());
                    break;
                case 4:
                    System.out.print("Novo estado do aeroporto: ");
                    aeroporto.setEstado(scanner.nextLine());
                    break;
                case 5:
                    System.out.print("Novo país do aeroporto: ");
                    aeroporto.setPais(scanner.nextLine());
                    break;
                case 6:
                    editing = false;
                    break;
                default:
                    TerminalView.displayMessage("Opção inválida.");
            }
        }
    }

  public static void editarInformacoesFuncionario(Funcionario funcionario) {
    boolean editing = true;

    while (editing) {
        TerminalView.displayMessage("Escolha o que deseja editar:");
        String editOptions = "1. Nome do Funcionário \n" +
                            "2. CPF\n" +
                            "3. E-mail\n" +
                            "4. Voltar";
        TerminalView.displayMenu(editOptions);

        int escolha = scanner.nextInt();
        scanner.nextLine();
        System.out.print("\n");

        switch (escolha) {
            case 1:
                System.out.print("Novo Nome: ");
                String novoNome = scanner.nextLine();
                if (!novoNome.isEmpty()) {
                    funcionario.setNome(novoNome);
                }
                break;
            case 2:
                System.out.print("Novo CPF: ");
                String novoCpf = scanner.nextLine();
                if (!novoCpf.isEmpty()) {
                    funcionario.setCpf(novoCpf);
                }
                break;
            case 3:
                System.out.print("Novo E-mail: ");
                String novoEmail = scanner.nextLine();
                if (!novoEmail.isEmpty()) {
                    funcionario.setEmail(novoEmail);
                }
                break;
            case 4:
                editing = false;
                break;
            default:
                TerminalView.displayMessage("Opção inválida.");
        }
    }

    System.out.println("Funcionário atualizado com sucesso!");
}

}
