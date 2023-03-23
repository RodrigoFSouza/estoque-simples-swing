package br.com.portifolio.exception;

public class ProdutoNaoEncontradoException extends RuntimeException {

    public ProdutoNaoEncontradoException(String message) {
        super(message);
    }
}
