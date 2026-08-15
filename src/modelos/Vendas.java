package modelos;

public class Vendas {
    private double valorTotal;
    private String data;
    private Produto p;
    private int quantidade;

    public Vendas (String data, Produto p, int quantidade) {
        this.data = data;
        this.p = p;
        this.quantidade = quantidade;
        this.valorTotal = (p.getPrecoUnitario() * quantidade);
    }

    public String getData() {
        return data;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    @Override
    public String toString() {
        return ("DATA: %s/ NOME: %s/ " +
                    "QUANTIDADE-UNIDADES: %d/ VALOR: R$%.2f").
                        formatted(this.data, this.p.getNome(),
                            this.quantidade, this.valorTotal);
    }
}
