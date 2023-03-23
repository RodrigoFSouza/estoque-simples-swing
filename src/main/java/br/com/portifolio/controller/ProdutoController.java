package br.com.portifolio.controller;

import br.com.portifolio.entity.Produto;
import br.com.portifolio.facade.ProdutoFacade;

import java.util.List;

public class ProdutoController {

    private ProdutoFacade facade;

    public ProdutoController() {
        this.facade = new ProdutoFacade();
    }

    public void salvarOuEditarProduto(Produto produto) {
        facade.salvarOuEditarProduto(produto);
    }

    public Produto buscarProdutoPorId(long id) {
        return facade.buscarProdutoPorId(id);
    }

    public List<Produto> listarTodosProdutos() {
        return facade.listarTodosProdutos();
    }
}
