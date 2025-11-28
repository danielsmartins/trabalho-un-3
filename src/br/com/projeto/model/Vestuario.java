package br.com.projeto.model;

import br.com.projeto.annotation.InfoAutor;

@InfoAutor(nome = "Daniel Martins", data = "27/11/2025")
public class Vestuario extends Produto {
    private String tamanho;
    private String cor;

    public Vestuario(int id, String nome, double preco, String tamanho, String cor) {
        super(id, nome, preco);
        this.tamanho = tamanho;
        this.cor = cor;
    }

    @Override
    public String getDescricaoCompleta() {
        return String.format("VESTUÁRIO  - %s | Tam: %-4s | Cor: %s",
                super.toString(), tamanho, cor);
    }

    // Getters
    public String getTamanho() { return tamanho; }
    public String getCor() { return cor; }
}