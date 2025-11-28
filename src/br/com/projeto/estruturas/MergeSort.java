package br.com.projeto.estruturas;

import br.com.projeto.interfaces.Ordenador;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MergeSort<T> implements Ordenador<T> {

    @Override
    public void ordenar(List<T> lista, Comparator<T> comparador) {
        if (lista.size() <= 1) return;

        int meio = lista.size() / 2;
        List<T> esquerda = new ArrayList<>(lista.subList(0, meio));
        List<T> direita = new ArrayList<>(lista.subList(meio, lista.size()));

        ordenar(esquerda, comparador);
        ordenar(direita, comparador);

        merge(lista, esquerda, direita, comparador);
    }

    private void merge(List<T> lista, List<T> esq, List<T> dir, Comparator<T> comp) {
        int i = 0, j = 0, k = 0;

        while (i < esq.size() && j < dir.size()) {
            if (comp.compare(esq.get(i), dir.get(j)) <= 0) {
                lista.set(k++, esq.get(i++));
            } else {
                lista.set(k++, dir.get(j++));
            }
        }

        while (i < esq.size()) lista.set(k++, esq.get(i++));
        while (j < dir.size()) lista.set(k++, dir.get(j++));
    }
}