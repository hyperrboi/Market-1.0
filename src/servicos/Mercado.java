package servicos;

import modelos.CatalogoArquivo;
import modelos.InventarioArquivo;
import modelos.Produto;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class Mercado {
    private Catalogo catalogo;
    private Inventario inventario;
    private Movimentacoes movimentacao;

    private double margemLucro = 15;
    private static int id = 1;
    private String data;

    private InventarioArquivo arquivoI;
    private CatalogoArquivo arquivoC;

    public Mercado() throws IOException {
        catalogo = new Catalogo();
        inventario = new Inventario(catalogo);
        movimentacao = new Movimentacoes();
        arquivoI = new InventarioArquivo();
        arquivoC = new CatalogoArquivo();
        LocalDate today = LocalDate.now();
        data = String.valueOf(today);
    }

    public void carregarCatalogo(int id, String nome, int unidades, double precoCaixa, double margemLucro) {
        Produto p = new Produto(nome, id, unidades, precoCaixa, margemLucro);
        catalogo.adicionarAoCatalogo(p);

        this.id = Math.max(this.id, id + 1);
    }

    public boolean cadastrarProduto(String nome, int unidades, double precoCaixa) throws IOException {
        if (nome.isBlank() || (unidades <= 0) || (precoCaixa <= 0)) {
            return false;
        }

        Produto p = new Produto(nome, (this.id++), unidades, precoCaixa, this.margemLucro);
        boolean status = catalogo.adicionarAoCatalogo(p);

        if (status) arquivoC.salvar(catalogo.getCatalogo(), this);

        return status;
    }

    public String removerProduto(int id) throws IOException {
        if (id <= 0) {
            return "ID precisa ser maior que 0";
        }

        String mensagem = catalogo.removerProduto(id);
        if (mensagem.equals("Produto removido")) {
            arquivoC.salvar(catalogo.getCatalogo(), this);
        }

        return mensagem;
    }

    public void carregarEstoque(int id, int quantidade) {
        inventario.adicionarEstoque(id, quantidade);
    }

    public String adicionarEstoque(int id, int quantidade) throws IOException {
        if (id <= 0 || quantidade <= 0) {
            return "Erro ao adicionar ao estoque, id ou quantidade precisam ser maiores que 0";
        }

        String mensagem = inventario.adicionarEstoque(id, quantidade);
        boolean status = movimentacao.novaCompra(this.data, catalogo.encontrarPorId(id), quantidade);

        if (!status) return "Erro ao finalizar compra";

        if (mensagem.equals("Item adicionado")) arquivoI.salvar(inventario.getInventario());

        return mensagem;
    }

    public String removerEstoque(int id, int quantidade) throws IOException {
        if (id <= 0 || quantidade <= 0) {
            return "Erro ao remover quantidade do estoque, id ou quantidade precisam ser maiores que 0";
        }

        String mensagem = inventario.removerEstoque(id, quantidade);
        boolean status = movimentacao.novaVenda(this.data, catalogo.encontrarPorId(id), quantidade);

        if (!status) return "Erro ao finalizar venda";

        if (mensagem.equals("Estoque removido") ||
                mensagem.equals("Estoque e item removidos do inventário")) arquivoI.salvar(inventario.getInventario());

        return mensagem;
    }

    public double getMargemLucro() {
        return margemLucro;
    }

    public Catalogo getCatalogo() {
        return catalogo;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public InventarioArquivo getArquivoI() {
        return arquivoI;
    }

    public CatalogoArquivo getArquivoC() {
        return arquivoC;
    }

    public String getVendas() {
        return movimentacao.getVendas();
    }

    public String getCompras() {
        return movimentacao.getCompras();
    }

    public double getCaixa() {
        return movimentacao.getCaixaTotal();
    }
}
