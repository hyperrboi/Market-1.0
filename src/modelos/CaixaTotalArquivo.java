package modelos;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;

public class CaixaTotalArquivo {
    private final Path caminho;

    public CaixaTotalArquivo() throws IOException {
        caminho = Paths.get("dados", "caixatotal.txt");

        Files.createDirectories(caminho.getParent());

        if (Files.notExists(caminho)) {
            Files.createFile(caminho);
        }
    }

    public double carregar() throws IOException {
        double caixa = 0.0;
        try (BufferedReader br = new BufferedReader(new FileReader(caminho.toFile()))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                if (linha.isBlank()) {
                    continue;
                }

                caixa = Double.parseDouble(linha.replace(",", "."));
            }
        }
        return caixa;
    }

    public void salvar(double caixa) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminho.toFile()))) {
            bw.write(String.format(Locale.US, "%.2f", caixa));
            bw.newLine();
        }
    }
}
