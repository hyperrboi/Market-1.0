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
