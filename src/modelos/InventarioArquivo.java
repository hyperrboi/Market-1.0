package modelos;

import servicos.Mercado;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class InventarioArquivo {

    private final Path caminho;

    public InventarioArquivo() throws IOException {
       caminho = Paths.get("dados", "inventario.txt");

        Files.createDirectories(caminho.getParent());

        if (Files.notExists(caminho)) {
            Files.createFile(caminho);
        }
    }

    public void carregar(Mercado m) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(caminho.toFile()))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                if (linha.isBlank()) continue;

                String[] dados = linha.split(";");
                int id = Integer.parseInt(dados[0]);
                int quantidade = Integer.parseInt(dados[1]);

                m.carregarEstoque(id, quantidade);
            }
        }
    }

    public void salvar(HashMap<Integer, Integer> inventario) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminho.toFile()))) {

            for (var item : inventario.entrySet()) {
                bw.write(item.getKey() + ";" + item.getValue());
                bw.newLine();
            }
        }
    }
}
