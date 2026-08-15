package app;

import servicos.MenuConsole;
import servicos.Mercado;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static Mercado mercado;
    private static MenuConsole menuConsole;

    static {
        try {
            mercado = new Mercado();
            menuConsole = new MenuConsole(mercado);
            mercado.getArquivoC().carregar(mercado);
            mercado.getArquivoI().carregar(mercado);
            mercado.getMovimentacao().getArquivoC().carregar(mercado.getMovimentacao(), mercado);
        } catch (IOException e) {
            System.out.println("Erro ao carregar os arquivos");
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {

        System.out.println("-------------------------");
        System.out.println("Bem vindo ao Mercado Java");
        System.out.println("-------------------------");

        menuConsole.menuPrincipal();

        scanner.close();
    }


        /*int option = readPositiveInteger();
        System.out.println("1 - Cadastrar produto");
        System.out.println("2 - Mostrar catalógo");
        System.out.println("3 - Remover produto do catalógo");
        System.out.println("4 - Estocar produto");
        System.out.println("5 - Remover estoque");
        System.out.println("6 - Mostrar estoque");
        System.out.println("7 - Mostrar histórico de compras");
        System.out.println("8 - Mostrar histórico de vendas");
        System.out.println("9 - Mostrar valor do caixa");
        System.out.println("10 - Sair");
        return readPositiveInteger();
    }


    private static void removerProduto() {
        scanner.nextLine();
        System.out.println("Informe o id do produto a ser removido: ");
        int id = readPositiveInteger();

        try {
            System.out.println(mercado.removerProduto(id));
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

    private static void removerEstoque() {
        scanner.nextLine();
        System.out.println("Informe o ID do produto");
        int id = readPositiveInteger();
        System.out.println("Informe a quantidade a ser removida: ");
        int quantidade = readPositiveInteger();

        try {
            System.out.println(mercado.removerEstoque(id, quantidade));
        } catch (IOException e) {
            System.out.println("Erro ao remover itens do arquivo inventario.txt");
        }
    }*/
}