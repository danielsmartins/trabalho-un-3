package br.com.projeto.exception;

public class DadoInvalidoException extends NegocioException {
    public DadoInvalidoException(String dado, String motivo) {
        super("dado invalido (" + dado + "): " + motivo);
    }
}