package app;

import servicos.Mercado;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static Mercado mercado;

    static {
        try {
            mercado = new Mercado();
        } catch (IOException e) {
            System.out.println("Erro ao carregar os arquivos");
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        int option;

        System.out.println("-------------------------");
        System.out.println("Bem vindo ao Mercado Java");
        System.out.println("-------------------------");

        do {
            option = mostrarMenu();

            switch (option) {
                case 1 -> cadastrarP();
                case 2 -> System.out.println(mercado.getCatalogo());
                case 3 -> estocarP();
                case 5 -> System.out.println(mercado.getInventario());
                case 6 -> System.out.println("Obrigado por usar o nosso sistema!");
                default -> System.out.println("Digite novamente");
            }

        } while (option != 6);

        scanner.close();
    }

    private static int mostrarMenu() {
        System.out.println("MENU do Mercado: ");
        System.out.println("1 - Cadastrar produto");
        System.out.println("2 - Mostrar catalógo");
        System.out.println("3 - Estocar produto");
        System.out.println("4 - Vender produto");
        System.out.println("5 - Mostrar estoque");
        System.out.println("6 - Sair");
        return readPositiveInteger();
    }

    private static void cadastrarP() {

        scanner.nextLine();
        System.out.println("Primeiro informe o nome do produto");
        String nome = readNonEmptyName();
        System.out.println("Informe a quantidade de unidades que vem em cada caixa");
        int unidade = readPositiveInteger();
        System.out.println("Informe o preço de cada caixa");
        double preco = readPositiveDouble();

        try {
            System.out.println((mercado.cadastrarProduto(nome, unidade, preco) ?
                    ("Produto cadastrado com sucesso") :
                    ("Erro ao cadastrar o produto")));
        } catch (IOException e) {
            System.out.println("Erro ao acessar o arquivo catalogo.txt");
        }
    }

    private static void estocarP() {
        scanner.nextLine();
        System.out.println("Informe o ID do produto");
        int id = readPositiveInteger();
        System.out.println("Informe a quantidade a ser estocada: ");
        int quantidade = readPositiveInteger();

        try {
            System.out.println(mercado.adicionarEstoque(id, quantidade));
        } catch (IOException e) {
            System.out.println("Erro ao adicionar item ao arquivo inventario.txt");
            throw new RuntimeException(e);
        }

    }

    private static String readNonEmptyName() {
        while (true) {

            System.out.print("Digite o nome: ");
            String nome = scanner.nextLine().toUpperCase().trim().replace(" ", "");

            if (!nome.isBlank()) {
                return nome;
            }

            System.out.println("Nome não pode ser vazio!");
        }
    }

    private static int readPositiveInteger() {
        while (true) {

            System.out.print("Digite o número: ");

            try {
                int number = scanner.nextInt();

                if (number > 0) {
                    return number;
                }

                System.out.println("Número tem que ser maior que zero");

            } catch (InputMismatchException e) {
                System.out.println("Escolha inválida! Esperava um número inteiro");
                scanner.next();
            }
        }
    }

    private static double readPositiveDouble() {
        while (true) {

            System.out.print("Digite o valor: R$");

            try {
                double numero = scanner.nextDouble();

                if (numero > 0) {
                    return numero;
                }

                System.out.println("Valor precisa ser positivo");
            } catch (InputMismatchException e) {
                System.out.println("Valor inválido! Esperava um número decimal");
                scanner.next();
            }
        }
    }

}