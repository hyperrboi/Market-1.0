package modelos;

public class Compras {
    private double valorTotal;
    private String data;
    private Produto p;
    private int quantidade;

    public Compras (String data, Produto p, int quantidade) {
        this.data = data;
        this.p = p;
        this.quantidade = quantidade;
        this.valorTotal = (p.getPrecoCaixa() * quantidade);
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public String getData() {
        return data;
    }

    public Produto getProduto() {
        return p;
    }

    public int getQuantidade() {
        return quantidade;
    }

    @Override
    public String toString() {
        return ("DATA: %s/ NOME: %s/ " +
                    "QUANTIDADE-CAIXAS: %d/ VALOR: R$%.2f").
                        formatted(this.data, this.p.getNome(),
                            this.quantidade, this.valorTotal);
    }
}
