package servicos;

import modelos.Produto;

public class Mercado {
    private Catalogo catalogo;
    private Inventario inventario;
    private double margemLucro = 15;
    private static int id = 1;

    public Mercado() {
        catalogo = new Catalogo();
        inventario = new Inventario(catalogo);
    }

    public boolean cadastrarProduto(String nome, int unidades, double precoCaixa) {
        if (nome.isBlank() || (unidades <= 0) || (precoCaixa <= 0)) {
            return false;
        }
        Produto p = new Produto(nome, (this.id++), unidades, precoCaixa, this.margemLucro);
        return catalogo.adicionarAoCatalogo(p);
    }

    public String adicionarEstoque(int id, int quantidade) {
        if (id <= 0 || quantidade <= 0) {
            return "Erro ao adicionar ao estoque, id ou quantidade precisam ser maiores que 0";
        }
        else return inventario.adicionarEstoque(id, quantidade);
    }

    public Catalogo getCatalogo() {
        return catalogo;
    }
}
