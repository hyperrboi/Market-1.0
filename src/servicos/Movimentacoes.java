package servicos;

import modelos.*;

import java.io.IOException;
import java.util.ArrayList;

public class Movimentacoes {
    private static double caixaTotal;
    private ArrayList<Vendas> vendas;
    private ArrayList<Compras> compras;

    private CaixaTotalArquivo arquivoCT;
    private ComprasArquivo arquivoC;

    public Movimentacoes(Mercado m) throws IOException {
        this.vendas = new ArrayList<Vendas>();
        this.compras = new ArrayList<Compras>();

        arquivoC = new ComprasArquivo();

        this.arquivoCT = new CaixaTotalArquivo();
        caixaTotal = arquivoCT.carregar();
    }

    public boolean novaCompra(String data, Produto p, int quantidade) throws IOException {
        Compras c = new Compras(data, p, quantidade);
        compras.add(c);
        caixaTotal -= c.getValorTotal();
        arquivoCT.salvar(caixaTotal);
        arquivoC.salvar(this);
        return true;
    }

    public boolean novaVenda(String data, Produto p, int quantidade) throws IOException {
        Vendas v = new Vendas(data, p, quantidade);
        vendas.add(v);
        caixaTotal += v.getValorTotal();
        arquivoCT.salvar(caixaTotal);
        return true;
    }

    public ArrayList<Compras> getListaCompras() {
        return compras;
    }

    public String comprasDia(String data) {
        StringBuilder sb = new StringBuilder();

        for (Compras c : compras) {
            if (c.getData().equals(data)) {
                sb.append(c).append(System.lineSeparator());
            }
        }

        if (sb.isEmpty()) {
            return "Não tem históricos de compras feitas nesse dia";
        }

        return sb.toString();
    }

    public String vendasDia(String data) {
        StringBuilder sb = new StringBuilder();

        for (Vendas v : vendas) {
            if (v.getData().equals(data)) {
                sb.append(v).append(System.lineSeparator());
            }
        }

        if (sb.isEmpty()) {
            return "Não tem históricos de vendas feitas nesse dia";
        }

        return sb.toString();
    }

    public double getCaixaTotal() {
        return caixaTotal;
    }

    public String getVendas() {
        StringBuilder sb = new StringBuilder();

        if (vendas.isEmpty()) {
            return "Histórico de vendas vazio";
        }

        for (Vendas v : vendas) {
            sb.append(v).append(System.lineSeparator());
        }

        return sb.toString();
    }

    public String getCompras() {
        StringBuilder sb = new StringBuilder();

        if (compras.isEmpty()) {
            return "Histórico de vendas vazio";
        }

        for (Compras c : compras) {
            sb.append(c).append(System.lineSeparator());
        }

        return sb.toString();
    }

    public ComprasArquivo getArquivoC() {
        return arquivoC;
    }
}

