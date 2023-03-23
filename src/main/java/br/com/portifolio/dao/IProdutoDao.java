package br.com.portifolio.dao;

import br.com.portifolio.entity.Produto;

import java.util.List;

public interface IProdutoDao {
    void salvarOuEditarProduto(Produto produto);
    Produto buscarProdutoPorId(long id);
    List<Produto> listarTodosProdutos();
}
