package br.com.portifolio.controller;

import br.com.portifolio.entity.MovimentoEstoque;
import br.com.portifolio.facade.MovimentoEstoqueFacade;

import java.util.List;

public class MovimentoEstoqueController {
    private MovimentoEstoqueFacade facade;

    public MovimentoEstoqueController() {
        this.facade = new MovimentoEstoqueFacade();
    }

    public void salvarOuEditarMovimentoEstoque(MovimentoEstoque movimentoEstoque) {
        facade.salvarOuEditarMovimentoEstoque(movimentoEstoque);
    }

    public MovimentoEstoque getMovimentoEstoque(long id) {
        return facade.getMovimentoEstoque(id);
    }

    public List<MovimentoEstoque> listarTodosMovimentosEstoque() {
        return facade.listarTodosMovimentosEstoque();
    }
}
