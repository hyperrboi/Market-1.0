package servicos;

import modelos.CaixaTotalArquivo;
import modelos.Compras;
import modelos.Produto;
import modelos.Vendas;

import java.io.IOException;
import java.util.ArrayList;

public class Movimentacoes {
    private static double caixaTotal;
    private ArrayList<Vendas> vendas;
    private ArrayList<Compras> compras;
    private CaixaTotalArquivo arquivoC;

    public Movimentacoes() throws IOException {
        this.arquivoC = new CaixaTotalArquivo();
        this.vendas = new ArrayList<Vendas>();
        this.compras = new ArrayList<Compras>();
        caixaTotal = arquivoC.carregar();
    }

    public boolean novaCompra(String data, Produto p, int quantidade) throws IOException {
        Compras c = new Compras(data, p, quantidade);
        compras.add(c);
        caixaTotal -= c.getValorTotal();
        arquivoC.salvar(caixaTotal);
        return true;
    }

    public boolean novaVenda(String data, Produto p, int quantidade) throws IOException {
        Vendas v = new Vendas(data, p, quantidade);
        vendas.add(v);
        caixaTotal += v.getValorTotal();
        arquivoC.salvar(caixaTotal);
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

    public static void setCaixaTotal(double caixaTotal) {
        Movimentacoes.caixaTotal = caixaTotal;
    }
}
