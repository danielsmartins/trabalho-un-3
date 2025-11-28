package br.com.projeto.exception;

public class ProdutoJaCadastradoException extends NegocioException {
    public ProdutoJaCadastradoException(int id) {
        super("erro de duplicidade: O produto com ID " + id + " já está no sistema.");
    }
}