package br.com.projeto.model;

import br.com.projeto.annotation.InfoAutor;

@InfoAutor(nome = "Daniel Martins", data = "27/11/2025")
public class Eletronico extends Produto {

    private String conectividade;

    public Eletronico(int id, String nome, double preco, String conectividade) {
        super(id, nome, preco);
        this.conectividade = conectividade;
    }

    @Override
    public String getDescricaoCompleta() {
        return "ELETRÔNICO - " + super.toString() + " | Conectividade: " + conectividade;
    }
    public String getConectividade() {
        return conectividade;
    }
}