package servicos;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuConsole {
    private final Scanner scanner = new Scanner(System.in);
    private Mercado mercado;

    public MenuConsole(Mercado mercado) {
        this.mercado = mercado;
    }

    public void menuPrincipal() {
        int option;

        do {
            option = mostrarMenuPrincipal();

            switch (option) {
                case 1 -> menuProdutos();
                case 2 -> menuEstoque();
                //case 3 -> menuCOmpras();
                //case 4 -> menuVendas();
                //case 5 -> menuMovimentacoes();
                case 6 -> System.out.println("Obrigado por usar nosso sistema!");
                default -> System.out.println("Digite novamente sua opção");
            }

        } while (option != 6);
    }

    public int mostrarMenuPrincipal() {
        System.out.println("MENU do Mercado: ");
        System.out.println("1 - PRODUTOS");
        System.out.println("2 - ESTOQUE");
        System.out.println("3 - COMPRAS");
        System.out.println("4 - VENDAS");
        System.out.println("5 - MOVIMENTAÇÕES");
        System.out.println("6 - SAIR");
        return readPositiveInteger();
    }

    public void menuProdutos() {
        int option;

        do {
            System.out.println("1 - Cadastrar produto");
            System.out.println("2 - Mostrar catalógo");
            System.out.println("3 - Buscar produto");
            System.out.println("4 - Remover produto");
            System.out.println("5 - SAIR");

            option = readPositiveInteger();

            switch (option) {
                case 1 -> cadastrarProduto();
                case 2 -> System.out.println(mercado.getCatalogo());
                case 3 -> buscarProduto();
                case 4 -> removerProduto();
                case 5 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Digite sua opção novamente");
            }
        } while (option != 5);
    }

    public void menuEstoque() {
        int option;
        do {
            System.out.println("1 - Estocar produto");
            System.out.println("2 - Registar venda de estoque");
            System.out.println("3 - Mostrar estoque");
            System.out.println("4 - SAIR");

            option = readPositiveInteger();

            switch (option) {
                case 1 -> estocarProduto();
                case 2 -> venderEstoque();
                case 3 -> System.out.println(mercado.getInventario());
                case 4 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Digite novamente");
            }

        } while (option != 4);
    }

    public void menuCompras() {
        int option;
        do {
            System.out.println("1 - Mostrar histórico de compras");
            System.out.println("2 - Pesquisar compras por dia");
            System.out.println("3 - Extornar compra");
        }
    }

    public void cadastrarProduto() {
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

    public void buscarProduto() {
        scanner.nextLine();
        System.out.println("Primeiro, informe o nome do produto");
        String nome = readNonEmptyName();
        System.out.println(mercado.buscarProdutoPorNome(nome));
    }

    private void removerProduto() {
        scanner.nextLine();
        System.out.println("Informe o id do produto a ser removido: ");
        int id = readPositiveInteger();

        try {
            System.out.println(mercado.removerProduto(id));
        } catch (IOException e) {
            System.out.println("Erro ao acessar o arquivo catalogo.txt");
        }
    }

    private  void estocarProduto() {
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

    private  void venderEstoque() {
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
    }

    private String readNonEmptyName() {
        while (true) {

            System.out.print("Digite o nome: ");
            String nome = scanner.nextLine().toUpperCase().trim().replace(" ", "-");

            if (!nome.isBlank()) {
                return nome;
            }

            System.out.println("Nome não pode ser vazio!");
        }
    }

    private int readPositiveInteger() {
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

    private double readPositiveDouble() {
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
