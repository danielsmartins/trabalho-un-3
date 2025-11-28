package br.com.projeto.exception;

//base para as outras
public class NegocioException extends Exception {
    public NegocioException(String mensagem) {
        super(mensagem);
    }
}