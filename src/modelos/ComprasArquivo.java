package modelos;

import servicos.Mercado;
import servicos.Movimentacoes;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ComprasArquivo {

    private final Path caminho;

    public ComprasArquivo() throws IOException {
        caminho = Paths.get("dados", "compras.txt");

        Files.createDirectories(caminho.getParent());

        if (Files.notExists(caminho)) {
            Files.createFile(caminho);
        }
    }

    public void carregar(Movimentacoes movimentacoes, Mercado mercado) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(caminho.toFile()))){
            String linha;

            while ((linha = br.readLine()) != null) {
                if (linha.isBlank()) continue;

                String[] dados = linha.split(";");
                String data = dados[0];
                String nome = dados[1];
                int qtdCaixas = Integer.parseInt(dados[2]);
                Produto p = mercado.encontrarProdutoPorNome(nome);

                movimentacoes.novaCompra(data, p, qtdCaixas);

            }
        }
    }

    public void salvar(Movimentacoes m) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminho.toFile()))) {
            for (Compras compras: m.getListaCompras()) {
                bw.write(compras.getData() + ";" +
                compras.getProduto().getNome() + ";" +
                compras.getQuantidade());
            }
        }
    }
}
