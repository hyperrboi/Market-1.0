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

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "ID: %s/ NOME: %s/ UNIDADES: %d/ PRECOCAIXA: R$%.2f/ PRECOUNITARIO: R$%.2f".formatted(this.id,
                this.nome, this.unidades, this.precoCaixa, this.precoUnitario);
    }
}
