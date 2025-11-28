package br.com.projeto.estruturas;

import br.com.projeto.interfaces.Arvore;

public class ArvoreBinariaBusca<T extends Comparable<T>> implements Arvore<T> {

    private class No {
        T valor;
        No esquerda, direita;
        No(T valor) { this.valor = valor; }
    }

    private No raiz;

    @Override
    public void inserir(T elemento) {
        raiz = inserirRecursivo(raiz, elemento);
    }

    private No inserirRecursivo(No atual, T elemento) {
        if (atual == null) return new No(elemento);

        if (elemento.compareTo(atual.valor) < 0) {
            atual.esquerda = inserirRecursivo(atual.esquerda, elemento);
        } else if (elemento.compareTo(atual.valor) > 0) {
            atual.direita = inserirRecursivo(atual.direita, elemento);
        }
        return atual;
    }

    @Override
    public void emOrdem() {
        System.out.println("--- Árvore (Em Ordem por ID) ---");
        emOrdemRecursivo(raiz);
        System.out.println("--------------------------------");
    }

    private void emOrdemRecursivo(No no) {
        if (no != null) {
            emOrdemRecursivo(no.esquerda);
            System.out.println(no.valor);
            emOrdemRecursivo(no.direita);
        }
    }

    @Override
    public T buscar(T elemento) {
        return buscarRecursivo(raiz, elemento);
    }

    private T buscarRecursivo(No no, T elemento) {
        if (no == null) return null;
        int cmp = elemento.compareTo(no.valor);
        if (cmp == 0) return no.valor;
        return cmp < 0 ? buscarRecursivo(no.esquerda, elemento) : buscarRecursivo(no.direita, elemento);
    }
}