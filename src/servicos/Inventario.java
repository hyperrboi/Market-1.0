package servicos;

import modelos.Produto;

import java.util.HashMap;

public class Inventario {
    private HashMap<Integer, Integer> inventario;
    private Catalogo c;

    public Inventario(Catalogo c) {
        inventario = new HashMap<>();
        this.c = c;
    }

    public String adicionarEstoque(int id, int quantidade) {
        Produto p = c.encontrarPorId(id);

        if (p == null) {
            return "Erro: Item precisa ser catalogado";
        }

        inventario.put(id, inventario.getOrDefault(id, 0) + quantidade);

        return "Item adicionado";
    }

    public String removerEstoque(int id, int quantidade) {
        Integer quantidadeEstoque = inventario.get(id);

        if (quantidadeEstoque == null) {
            return "Erro: Item não catalogado";
        } else if (quantidadeEstoque < quantidade) {
            return "Erro: Estoque insuficiente a quantidade a ser removida";
        } else if (quantidadeEstoque == quantidade) {
            inventario.remove(id);
            return "Item e estoque removidos do inventário";
        }

        inventario.replace(id, inventario.get(id) - quantidade);
        return "Estoque removido";
    }

    public HashMap<Integer, Integer> getInventario() {
        return inventario;
    }

    @Override
    public String toString() {
        if (inventario.isEmpty()) {
            return "Estoque vazio";
        }

        StringBuilder sb = new StringBuilder();

        for (Integer id: inventario.keySet()) {
            Produto p = c.encontrarPorId(id);
            sb.append("ID: %d/ NOME: %s/ UNIDADES/EM/ESTOQUE: %d/ PRECO/UNITARIO: R$%.2f".formatted
                    (id, p.getNome(), inventario.get(id), p.getPrecoUnitario())).append(System.lineSeparator());
        }

        return sb.toString();
    }
}
