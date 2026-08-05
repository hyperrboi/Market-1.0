package servicos;

import modelos.Produto;

import java.util.ArrayList;

public class Catalogo {
    private ArrayList<Produto> catalogo;

    public Catalogo() {
        catalogo = new ArrayList<Produto>();
    }

    public boolean adicionarAoCatalogo(Produto produto) {
        if (encontrarNome(produto)) {
            catalogo.add(produto);
            return true;
        } else {
            return false;
        }
    }

    public String removerProduto(int id) {
        Produto p = encontrarPorId(id);

        if (p == null) {
            return "Produto não está no catalógo";
        }

        catalogo.remove(p);
        return "Produto removido";
    }

    public boolean encontrarNome(Produto produto) {
        for (Produto p: catalogo) {
            if (produto.getNome().equals(p.getNome())) {
                return false;
            }
        }
        return true;
    }

    public Produto encontrarPorId(int id) {
        for (Produto produto: catalogo) {
            if (produto.getId() == id) {
                return produto;
            }
        }

        return null;
    }

    public ArrayList<Produto> getCatalogo() {
        return catalogo;
    }

    @Override
    public String toString() {
        if (catalogo.isEmpty()) {
            return "Catalógo vazio";
        }

        StringBuilder sb = new StringBuilder();

        for (Produto p: catalogo) {
            sb.append(p).append("\n");
        }

        return sb.toString();
    }
}
