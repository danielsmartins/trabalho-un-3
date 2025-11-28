package br.com.projeto.model;

public abstract class Produto implements Comparable<Produto> {
    private int id;
    private String nome;
    private double preco;

    public Produto(int id, String nome, double preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }

    public abstract String getDescricaoCompleta();

    public int getId() { return id; }
    public String getNome() { return nome; }
    public double getPreco() { return preco; }

    @Override
    public int compareTo(Produto outro) {
        return Integer.compare(this.id, outro.id);
    }

    @Override
    public String toString() {
        return String.format("ID: %d | Nome: %-20s | Preço: R$ %.2f", id, nome, preco);
    }
}