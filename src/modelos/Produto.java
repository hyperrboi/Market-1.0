package modelos;

public class Produto {
    private String nome;
    private int id, unidades;
    private double precoCaixa, precoUnitario;

    public Produto(String nome, int id, int unidades, double precoCaixa, double margemLucro) {
        this.nome = nome;
        this.id = id;
        this.precoCaixa = precoCaixa;
        this.unidades = unidades;
        this.precoUnitario = calcularPreco(unidades, precoCaixa, margemLucro);
    }

    private double calcularPreco(int unidades, double precoCaixa, double margemLucro) {
        return (this.precoCaixa / this.unidades ) /  ( (100 - margemLucro) / 100) ;
    }

    public int getId() {
        return id;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "ID: %d/ NOME: %s/ UNIDADES: %d/ PRECO/CAIXA: R$%.2f/ PRECO/UNITARIO: R$%.2f".formatted(this.id,
                this.nome, this.unidades, this.precoCaixa, this.precoUnitario);
    }
}
