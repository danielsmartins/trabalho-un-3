package br.com.projeto.interfaces;

import java.util.Comparator;
import java.util.List;

public interface Ordenador<T> {
    void ordenar(List<T> lista, Comparator<T> comparador);
}