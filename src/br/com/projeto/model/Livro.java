package br.com.projeto.model;

import br.com.projeto.annotation.InfoAutor;

@InfoAutor(nome = "Daniel", data = "27/11/2025")
public class Livro extends Produto {
    private String autor;

    public Livro(int id, String nome, double preco, String autor) {
        super(id, nome, preco);
        this.autor = autor;
    }

    @Override
    public String getDescricaoCompleta() {
        return "LIVRO      - " + super.toString() + " | Autor: " + autor;
    }
}