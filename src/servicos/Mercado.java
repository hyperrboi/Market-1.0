package servicos;

import modelos.Produto;

import java.util.ArrayList;

public class Mercado {
    private Catalogo catalogo;
    private Inventario inventario;
    private double margemLucro = 15;
    private static int id = 1;

    public Mercado() {
        catalogo = new Catalogo();
        inventario = new Inventario();
    }

    public boolean cadastrarProduto(String nome, int unidades, double precoCaixa) {
        if (nome.isBlank() || (unidades <= 0) || (precoCaixa <= 0)) {
            return false;
        }
        Produto p = new Produto(nome, (this.id++), unidades, precoCaixa, this.margemLucro);
        return catalogo.adicionarAoCatalogo(p);
    }

    public Catalogo getCatalogo() {
        return catalogo;
    }
}
