package br.com.portifolio.facade;

import br.com.portifolio.dao.IProdutoDao;
import br.com.portifolio.dao.ProdutoDao;
import br.com.portifolio.entity.Produto;

import java.util.List;

public class ProdutoFacade {

    private IProdutoDao produtoDao;


    public ProdutoFacade() {
        this.produtoDao = new ProdutoDao();
    }

    public void salvarOuEditarProduto(Produto produto) {
        produtoDao.salvarOuEditarProduto(produto);
    }

    public Produto buscarProdutoPorId(long id) {
        return produtoDao.buscarProdutoPorId(id);
    }

    public List<Produto> listarTodosProdutos() {
        return produtoDao.listarTodosProdutos();
    }
}
