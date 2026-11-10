package app;

import exception.LivroJaEmprestadoException;
import exception.LivroNaoEmprestadoException;
import exception.LivroNaoEncontradoException;
import model.Livro;
import service.BibliotecaService;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final BibliotecaService biblioteca = new BibliotecaService();

    public static void main(String[] args) {
        int opcao;

        do {
            exibirMenu();
            opcao = lerInteiro("Escolha uma opção: ");

            try {
                switch (opcao) {
                    case 1 -> cadastrarLivro();
                    case 2 -> listarLivros();
                    case 3 -> buscarLivro();
                    case 4 -> emprestarLivro();
                    case 5 -> devolverLivro();
                    case 6 -> carregarLivrosExemplo();
                    case 7 -> executarTestesObrigatorios();
                    case 0 -> System.out.println("Encerrando o sistema...");
                    default -> System.out.println("⚠ Opção inválida!");
                }
            } catch (LivroNaoEncontradoException e) {
                System.out.println("❌ Erro: " + e.getMessage());
            } catch (LivroJaEmprestadoException e) {
                System.out.println("❌ Erro: " + e.getMessage());
            } catch (LivroNaoEmprestadoException e) {
                System.out.println("❌ Erro: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("❌ Erro inesperado: " + e.getMessage());
            }
            System.out.println();
        } while (opcao != 0);

        scanner.close();
    }

    // ---------- MENU ----------
    private static void exibirMenu() {
        System.out.println("========= SISTEMA DE BIBLIOTECA =========");
        System.out.println("1 - Cadastrar livro");
        System.out.println("2 - Listar livros");
        System.out.println("3 - Buscar livro por código");
        System.out.println("4 - Emprestar livro");
        System.out.println("5 - Devolver livro");
        System.out.println("6 - Carregar 3 livros de exemplo");
        System.out.println("7 - Executar testes obrigatórios");
        System.out.println("0 - Sair");
        System.out.println("=========================================");
    }

    // ---------- FUNCIONALIDADES ----------
    private static void cadastrarLivro() {
        System.out.print("Código: ");
        String codigo = scanner.nextLine();
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Autor:  ");
        String autor = scanner.nextLine();

        biblioteca.cadastrar(new Livro(codigo, titulo, autor));
        System.out.println("✔ Livro cadastrado com sucesso!");
    }

    private static void listarLivros() {
        List<Livro> livros = biblioteca.listar();
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado.");
            return;
        }
        System.out.println("--- Livros cadastrados (" + livros.size() + ") ---");
        livros.forEach(System.out::println);
    }

    private static void buscarLivro() throws LivroNaoEncontradoException {
        System.out.print("Código do livro: ");
        String codigo = scanner.nextLine();
        Livro livro = biblioteca.buscar(codigo);
        System.out.println("✔ Livro encontrado: " + livro);
    }

    private static void emprestarLivro()
            throws LivroNaoEncontradoException, LivroJaEmprestadoException {
        System.out.print("Código do livro: ");
        String codigo = scanner.nextLine();
        biblioteca.emprestar(codigo);
        System.out.println("✔ Empréstimo realizado com sucesso!");
    }

    private static void devolverLivro()
            throws LivroNaoEncontradoException, LivroNaoEmprestadoException {
        System.out.print("Código do livro: ");
        String codigo = scanner.nextLine();
        biblioteca.devolver(codigo);
        System.out.println("✔ Devolução realizada com sucesso!");
    }

    private static void carregarLivrosExemplo() {
        biblioteca.cadastrar(new Livro("L001", "Dom Casmurro", "Machado de Assis"));
        biblioteca.cadastrar(new Livro("L002", "O Cortiço", "Aluísio Azevedo"));
        biblioteca.cadastrar(new Livro("L003", "Grande Sertão: Veredas", "Guimarães Rosa"));
        System.out.println("✔ 3 livros de exemplo cadastrados com sucesso!");
    }

    // ---------- TESTES OBRIGATÓRIOS ----------
    private static void executarTestesObrigatorios() {
        System.out.println("\n===== EXECUTANDO TESTES OBRIGATÓRIOS =====\n");

        // 1 - Cadastrar 3 livros
        System.out.println(">> [1] Cadastrar 3 livros");
        biblioteca.cadastrar(new Livro("T001", "Livro A", "Autor A"));
        biblioteca.cadastrar(new Livro("T002", "Livro B", "Autor B"));
        biblioteca.cadastrar(new Livro("T003", "Livro C", "Autor C"));
        System.out.println("   ✓ 3 livros cadastrados.\n");

        // 2 - Listar
        System.out.println(">> [2] Listar livros cadastrados");
        biblioteca.listar().forEach(l -> System.out.println("   " + l));
        System.out.println();

        // 3 - Buscar existente
        System.out.println(">> [3] Buscar livro T001");
        try {
            System.out.println("   ✓ Encontrado: " + biblioteca.buscar("T001"));
        } catch (LivroNaoEncontradoException e) {
            System.out.println("   ❌ " + e.getMessage());
        }
        System.out.println();

        // 4 - Emprestar
        System.out.println(">> [4] Emprestar livro T001");
        try {
            biblioteca.emprestar("T001");
            System.out.println("   ✓ Empréstimo realizado.");
        } catch (Exception e) {
            System.out.println("   ❌ " + e.getMessage());
        }
        System.out.println();

        // 5 - Emprestar novamente (deve falhar)
        System.out.println(">> [5] Tentar emprestar novamente T001 (deve falhar)");
        try {
            biblioteca.emprestar("T001");
            System.out.println("   ⚠ ERRO: não deveria ter emprestado!");
        } catch (LivroJaEmprestadoException e) {
            System.out.println("   ✓ Exceção tratada: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("   ❌ " + e.getMessage());
        }
        System.out.println();

        // 6 - Devolver
        System.out.println(">> [6] Devolver livro T001");
        try {
            biblioteca.devolver("T001");
            System.out.println("   ✓ Devolução realizada.");
        } catch (Exception e) {
            System.out.println("   ❌ " + e.getMessage());
        }
        System.out.println();

        // 7 - Buscar inexistente (deve lançar exceção)
        System.out.println(">> [7] Buscar livro inexistente T999 (deve falhar)");
        try {
            biblioteca.buscar("T999");
            System.out.println("   ⚠ ERRO: não deveria ter encontrado!");
        } catch (LivroNaoEncontradoException e) {
            System.out.println("   ✓ Exceção tratada: " + e.getMessage());
        }
        System.out.println("\n===== FIM DOS TESTES =====\n");
    }

    // ---------- UTILITÁRIO ----------
    private static int lerInteiro(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("⚠ Entrada inválida. Digite um número inteiro.");
            }
        }
    }
}
