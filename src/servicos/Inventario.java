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

        if (inventario.containsKey(id)) {
            inventario.put(id, quantidade);

        } else {
            Produto p = c.encontrarPorId(id);
            if (p == null) {
                return "Erro ao adicionar ao estoque, item precisa ser catalogado";
            }
            inventario.put(id, quantidade);
        }

        return "Item adicionado";
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
