package servicos;

import modelos.Compras;
import modelos.Produto;
import modelos.Vendas;

import java.util.ArrayList;

public class Movimentacoes {
    private static double caixaTotal;
    private ArrayList<Vendas> vendas;
    private ArrayList<Compras> compras;

    public Movimentacoes() {
        this.vendas = new ArrayList<Vendas>();
        this.compras = new ArrayList<Compras>();
    }

    public boolean novaCompra(String data, Produto p, int quantidade) {
        Compras c = new Compras(data, p, quantidade);
        compras.add(c);
        caixaTotal -= c.getValorTotal();
        return true;
    }

    public boolean novaVenda(String data, Produto p, int quantidade) {
        Vendas v = new Vendas(data, p, quantidade);
        vendas.add(v);
        caixaTotal += v.getValorTotal();
        return true;
    }

    public double getCaixaTotal() {
        return caixaTotal;
    }

    public String getVendas() {
        StringBuilder sb = new StringBuilder();

        if (vendas.isEmpty()) {
            return "Histórico de vendas vazio";
        }

        for (Vendas v: vendas) {
            sb.append(v).append(System.lineSeparator());
        }

        return sb.toString();
    }

    public String getCompras() {
        StringBuilder sb = new StringBuilder();

        if (compras.isEmpty()) {
            return "Histórico de vendas vazio";
        }

        for (Compras c: compras) {
            sb.append(c).append(System.lineSeparator());
        }

        return sb.toString();
    }
}
