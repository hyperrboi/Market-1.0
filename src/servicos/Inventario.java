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
}
