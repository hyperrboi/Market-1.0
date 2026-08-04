package modelos;

import servicos.Mercado;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class CatalogoArquivo {

    private final Path caminho;

    public CatalogoArquivo() throws IOException {
        caminho = Paths.get("dados", "catalogo.txt");

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

                String dados[] = linha.split(";");
                int id = Integer.parseInt(dados[0]);
                String nome = dados[1];
                int unidades = Integer.parseInt(dados[2]);
                double precoCaixa = Double.parseDouble(dados[3]);
                double margemLucro = Double.parseDouble(dados[4]);

                m.carregarCatalogo(id, nome, unidades, precoCaixa, margemLucro);

            }
        }
    }

    public void salvar(ArrayList<Produto> catalogo, Mercado m) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminho.toFile()))) {
            for (Produto p: catalogo) {
                bw.write(p.getId() + ";" + p.getNome() + ";" + p.getUnidades()
                        + ";" + p.getPrecoCaixa() + ";" + m.getMargemLucro());
                bw.newLine();
            }
        }
    }
}
