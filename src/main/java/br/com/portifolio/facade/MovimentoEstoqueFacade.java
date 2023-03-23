package br.com.portifolio.facade;

import br.com.portifolio.dao.IMovimentoEstoqueDao;
import br.com.portifolio.dao.IProdutoDao;
import br.com.portifolio.dao.MovimentoEstoqueDao;
import br.com.portifolio.dao.ProdutoDao;
import br.com.portifolio.entity.MovimentoEstoque;
import br.com.portifolio.entity.enums.TipoMovimentacao;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Logger;

public class MovimentoEstoqueFacade {
    private Logger logger = Logger.getLogger(String.valueOf(MovimentoEstoqueFacade.class));
    private IMovimentoEstoqueDao movimentoEstoqueDao;
    private IProdutoDao produtoDao;

    public MovimentoEstoqueFacade() {
        this.movimentoEstoqueDao = new MovimentoEstoqueDao();
        this.produtoDao = new ProdutoDao();
    }

    public void salvarOuEditarMovimentoEstoque(MovimentoEstoque movimentoEstoque) {
        if (TipoMovimentacao.ENTRADA.equals(movimentoEstoque.getTipoMovimentacao())) {
            BigDecimal quantidadAtualizada = movimentoEstoque.getProduto().getQuantidadeAtual().add(movimentoEstoque.getQuantidade());
            movimentoEstoque.getProduto().setQuantidadeAtual(quantidadAtualizada);
        } else {
            BigDecimal quantidadAtualizada = movimentoEstoque.getProduto().getQuantidadeAtual().subtract(movimentoEstoque.getQuantidade());
            if (movimentoEstoque.getProduto().getQuantidadeAtual().compareTo(movimentoEstoque.getQuantidade()) < 0) {
                throw new RuntimeException("Quantidade insuficiente em estoque");
            }
            if (movimentoEstoque.getProduto().getQuantidadeMinima().compareTo(quantidadAtualizada) < 0) {
                logger.info("Quantidade atual menor que a quantidade mínima");
            }
            movimentoEstoque.getProduto().setQuantidadeAtual(quantidadAtualizada);
        }
        produtoDao.salvarOuEditarProduto(movimentoEstoque.getProduto());
        movimentoEstoqueDao.salvarOuEditarMovimentoEstoque(movimentoEstoque);
    }

    public MovimentoEstoque getMovimentoEstoque(long id) {
        return movimentoEstoqueDao.getMovimentoEstoque(id);
    }

    public List<MovimentoEstoque> listarTodosMovimentosEstoque() {
        return movimentoEstoqueDao.listarTodosMovimentosEstoque();
    }
}
