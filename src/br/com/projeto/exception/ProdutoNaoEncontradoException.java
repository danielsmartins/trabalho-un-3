package br.com.projeto.exception;

public class ProdutoNaoEncontradoException extends NegocioException {
    public ProdutoNaoEncontradoException(int id) {
        super("busca falhou: O produto com ID " + id + " não foi localizado no estoque.");
    }
}