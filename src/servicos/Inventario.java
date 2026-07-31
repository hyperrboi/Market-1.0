package servicos;

import modelos.ItemInventario;

import java.util.HashMap;

public class Inventario {
    private ItemInventario item;
    private HashMap<Integer, Integer> inventario;

    public Inventario() {
        inventario = new HashMap<>();
    }
}
