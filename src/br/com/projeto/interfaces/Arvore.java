package br.com.projeto.interfaces;

public interface Arvore<T extends Comparable<T>> {
    void inserir(T elemento);
    T buscar(T elemento);
    void emOrdem();
}