package br.com.projeto.estruturas;

import br.com.projeto.exception.NegocioException;
import br.com.projeto.interfaces.Arvore;
import br.com.projeto.model.Produto;
import br.com.projeto.exception.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RepositorioHash<T extends Produto> {

    private Map<Integer, T> bancoDeDados;
    private Arvore<Produto> indiceArvore;

    public RepositorioHash() {
        this.bancoDeDados = new HashMap<>();
        this.indiceArvore = new ArvoreBinariaBusca<>();
    }

    public void adicionar(T item) throws NegocioException {
        if (bancoDeDados.containsKey(item.getId())) {
            throw new ProdutoJaCadastradoException(item.getId());
        }
        if (item.getPreco() < 0) {
            throw new DadoInvalidoException("Preço", "O valor não pode ser negativo (" + item.getPreco() + ").");
        }

        if (item.getNome() == null || item.getNome().trim().isEmpty()) {
            throw new DadoInvalidoException("Nome", "O nome do produto não pode estar em branco.");
        }

        bancoDeDados.put(item.getId(), item);
        indiceArvore.inserir(item);
        System.out.println(" ID " + item.getId() + ": " + item.getNome() + " adicionado.");
    }

    public void remover(int id) throws NegocioException {
        if (!bancoDeDados.containsKey(id)) {

            throw new ProdutoNaoEncontradoException(id);
        }
        bancoDeDados.remove(id);
        System.out.println("Produto removido do HashMap.");
    }

    public List<T> buscarPorNome(String termo) {
        List<T> resultados = new ArrayList<>();
        for (T item : bancoDeDados.values()) {
            if (item.getNome().toLowerCase().contains(termo.toLowerCase())) {
                resultados.add(item);
            }
        }
        return resultados;
    }

    public List<T> listarTodos() {
        return new ArrayList<>(bancoDeDados.values());
    }

    public void imprimirArvore() {
        indiceArvore.emOrdem();
    }
    public T buscarPorId(int id) {
        return bancoDeDados.get(id);
    }
}