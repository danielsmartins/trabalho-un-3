package br.com.projeto.app;

import br.com.projeto.annotation.InfoAutor;
import br.com.projeto.estruturas.MergeSort;
import br.com.projeto.estruturas.RepositorioHash;
import br.com.projeto.exception.*;
import br.com.projeto.interfaces.Ordenador;
import br.com.projeto.model.Eletronico;
import br.com.projeto.model.Livro;
import br.com.projeto.model.Produto;
import br.com.projeto.model.Vestuario;

import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    // Configuração do Scanner e do Repositório (banco de dados em memória)
    private static final Scanner scanner = new Scanner(System.in);
    private static final RepositorioHash<Produto> repo = new RepositorioHash<>();

    public static void main(String[] args) {
        System.out.println("SISTEMA DE ESTOQUE");

        // Popula o sistema com dados de teste
        inicializarDados();

        //Reflection: Lê as anotações personalizadas das classes de modelo
        System.out.println("\n>>> Verificando Assinatura do Sistema:");
        lerAnnotation(Eletronico.class);
        lerAnnotation(Livro.class);
        lerAnnotation(Vestuario.class);

        // Mantém o menu rodando até o usuário escolher sair
        boolean rodando = true;
        while (rodando) {
            exibirMenu();
            try {
                int opcao = scanner.nextInt();
                scanner.nextLine(); // Limpar buffer do enter
                switch (opcao) {
                    case 1: adicionarProdutoInterativo(); break;
                    case 2: buscarProdutoPorId(); break;
                    case 3: removerProduto(); break;
                    case 4: listarArvorePorId(); break;
                    case 5: listarOrdenado(); break;
                    case 6: buscarProdutoPorNome(); break;
                    case 7: exibirEstatisticas(); break;
                    case 0:
                        rodando = false;
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (InputMismatchException e) {
                // Tratamento para quando digita letra no lugar de número
                System.out.println("Erro: Por favor, digite apenas números inteiros.");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage());
            }
        }
        scanner.close();
    }

    //  métodos auxiliares

    private static void exibirMenu() {
        System.out.println("\n----------------------------------");
        System.out.println("          MENU PRINCIPAL          ");
        System.out.println("----------------------------------");
        System.out.println("1. Adicionar Novo Produto");
        System.out.println("2. Buscar Produto (por ID)");
        System.out.println("3. Remover Produto");
        System.out.println("4. Relatório: Árvore (Ordenado por ID)");
        System.out.println("5. Relatório: Lista Ordenada (Nome/Preço)");
        System.out.println("6. Buscar Produto (por Nome)");
        System.out.println("7. Dashboard (Estatísticas)");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    // Pré-carrega produtos para facilitar testes sem digitação manual
    private static void inicializarDados() {
        System.out.println("Carregando dados iniciais...");
        try {
            // Eletrônicos
            repo.adicionar(new Eletronico(10, "Notebook Dell", 4500.00, "Wi-Fi/Bivolt"));
            repo.adicionar(new Eletronico(15, "Mouse Logitech", 80.00, "USB 3.0"));
            repo.adicionar(new Eletronico(8, "Monitor LG 24", 900.00, "HDMI/VGA"));

            // Livros
            repo.adicionar(new Livro(5, "Clean Code", 120.00, "Robert C. Martin"));
            repo.adicionar(new Livro(2, "O Senhor dos Anéis", 250.00, "Tolkien"));

            // Vestuário
            repo.adicionar(new Vestuario(30, "Camiseta Básica", 45.00, "M", "Preta"));
            repo.adicionar(new Vestuario(31, "Tênis Nike Run", 350.00, "41", "Azul/Branco"));
            repo.adicionar(new Vestuario(32, "Calça Jeans", 120.00, "42", "Azul Escuro"));

            System.out.println(" [OK] Produtos carregados.");
        } catch (NegocioException e) {
            System.out.println("Erro na carga inicial: " + e.getMessage());
        }
    }

    // Lógica de UI para capturar dados do usuário e instanciar o objeto correto
    private static void adicionarProdutoInterativo() {
        try {
            System.out.println("\n--- Novo Produto");
            System.out.print("Tipo (1-Eletrônico, 2-Livro, 3-Vestuário): ");
            int tipo = scanner.nextInt();

            System.out.print("ID (Número único): ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Nome: ");
            String nome = scanner.nextLine();

            System.out.print("Preço: ");
            double preco = scanner.nextDouble();
            scanner.nextLine();

            // Polimorfismo: Instancia subclasses baseadas na escolha
            if (tipo == 1) {
                System.out.print("Conectividade (ex: USB, Wi-Fi): ");
                String conectividade = scanner.nextLine();
                repo.adicionar(new Eletronico(id, nome, preco, conectividade));
            } else if (tipo == 2) {
                System.out.print("Autor: ");
                String autor = scanner.nextLine();
                repo.adicionar(new Livro(id, nome, preco, autor));
            } else if (tipo == 3) {
                System.out.print("Tamanho (ex: P, M, 42, 38): ");
                String tamanho = scanner.nextLine();
                System.out.print("Cor: ");
                String cor = scanner.nextLine();
                repo.adicionar(new Vestuario(id, nome, preco, tamanho, cor));
            } else {
                System.out.println("Tipo inválido. Operação cancelada.");
            }
        } catch (ProdutoJaCadastradoException e) {
            System.out.println(" [X] operação recusada: " + e.getMessage());
        } catch (DadoInvalidoException e) {
            System.out.println(" [X] erro de validação: " + e.getMessage());
        } catch (NegocioException e) {
            System.out.println(e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println(" [!] erro de digitação: Você digitou um texto onde era número?");
            System.out.println("     DICA: Se for preço, use VÍRGULA (ex: 10,50) e não ponto.");
            scanner.nextLine();
        }
    }

    // Busca rápida usando HashMap (O(1))
    private static void buscarProdutoPorId() {
        System.out.print("Digite o ID para buscar: ");
        int id = scanner.nextInt();
        Produto p = repo.buscarPorId(id);
        if (p != null) {
            System.out.println("Encontrado: " + p.getDescricaoCompleta());
        } else {
            System.out.println("Produto com ID " + id + " não encontrado.");
        }
    }

    // Busca linear por string parcial
    private static void buscarProdutoPorNome() {
        System.out.print("Digite parte do nome para buscar: ");
        String termo = scanner.nextLine();
        List<Produto> resultados = repo.buscarPorNome(termo);

        if (resultados.isEmpty()) {
            System.out.println("Nenhum produto encontrado com: " + termo);
        } else {
            System.out.println("\n--- Resultados Encontrados");
            for (Produto p : resultados) {
                System.out.println(p.getDescricaoCompleta());
            }
        }
    }

    // Remove do repositório com tratamento de exceção específica
    private static void removerProduto() {
        System.out.print("Digite o ID para remover: ");
        try {
            int id = scanner.nextInt();
            repo.remover(id);
        } catch (ProdutoNaoEncontradoException e) {
            System.out.println(" [?] Atenção: " + e.getMessage());
        } catch (NegocioException e) {
            System.out.println(" Erro: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println(" Erro: O ID deve ser um número inteiro.");
            scanner.nextLine();
        }
    }

    // Percorre a Árvore Binária em ordem (In-Order Traversal)
    private static void listarArvorePorId() {
        System.out.println("\n Estrutura de Árvore (Ordenação Natural: ID) ");
        repo.imprimirArvore();
    }

    // Usa MergeSort para ordenar lista extraída do HashMap
    private static void listarOrdenado() {
        List<Produto> lista = repo.listarTodos();
        Ordenador<Produto> mergeSort = new MergeSort<>();

        System.out.println("\nEscolha o critério de ordenação:");
        System.out.println("1. Por Preço (Crescente)");
        System.out.println("2. Por Nome (A-Z)");
        int op = scanner.nextInt();

        if (op == 1) {
            mergeSort.ordenar(lista, (p1, p2) -> Double.compare(p1.getPreco(), p2.getPreco()));
        } else {
            mergeSort.ordenar(lista, Comparator.comparing(Produto::getNome));
        }

        System.out.println("\n--- Lista Ordenada ");
        for (Produto p : lista) {
            System.out.println(p.getDescricaoCompleta());
        }
    }

    // Gera estatísticas básicas percorrendo a lista de produtos
    private static void exibirEstatisticas() {
        List<Produto> lista = repo.listarTodos();
        if (lista.isEmpty()) {
            System.out.println("Estoque vazio. Nada para analisar.");
            return;
        }

        double valorTotal = 0;
        Produto maisCaro = lista.get(0);
        Produto maisBarato = lista.get(0);

        for (Produto p : lista) {
            valorTotal += p.getPreco();
            if (p.getPreco() > maisCaro.getPreco()) maisCaro = p;
            if (p.getPreco() < maisBarato.getPreco()) maisBarato = p;
        }

        System.out.println("\n=== DASHBOARD DE ESTOQUE ===");
        System.out.println(" Total de Itens: " + lista.size());
        System.out.println(String.format(" Valor Total em Estoque: R$ %.2f", valorTotal));
        System.out.println(String.format(" Média de Preço: R$ %.2f", (valorTotal / lista.size())));
        System.out.println("----------------------------");
        System.out.println(" Item Mais Caro:   " + maisCaro.getNome() + " (R$ " + maisCaro.getPreco() + ")");
        System.out.println(" Item Mais Barato: " + maisBarato.getNome() + " (R$ " + maisBarato.getPreco() + ")");
    }

    // Utilitário para ler anotação via Reflection
    public static void lerAnnotation(Class<?> classe) {
        if (classe.isAnnotationPresent(InfoAutor.class)) {
            InfoAutor info = classe.getAnnotation(InfoAutor.class);
            System.out.println(" - Classe " + classe.getSimpleName() + ": Dev " + info.nome() + " (" + info.data() + ")");
        }
    }
}