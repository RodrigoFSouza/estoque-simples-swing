package br.com.portifolio.dao;

import br.com.portifolio.entity.MovimentoEstoque;

import java.util.List;

public interface IMovimentoEstoqueDao {
    void salvarOuEditarMovimentoEstoque(MovimentoEstoque movimentoEstoque);
    MovimentoEstoque getMovimentoEstoque(long id);

    List<MovimentoEstoque> listarTodosMovimentosEstoque();
}
