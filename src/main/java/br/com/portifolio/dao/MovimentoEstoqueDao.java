package br.com.portifolio.dao;


import br.com.portifolio.entity.MovimentoEstoque;
import br.com.portifolio.exception.ProdutoNaoEncontradoException;
import br.com.portifolio.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class MovimentoEstoqueDao implements IMovimentoEstoqueDao {

    public List<MovimentoEstoque> listarTodosMovimentosEstoque() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

        String hql = "FROM MovimentoEstoque me JOIN FETCH me.produto p ORDER BY me.id, p.descricao";
            Query query = session.createQuery(hql);
            List results = query.list();

            transaction.commit();

            return results;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        throw new ProdutoNaoEncontradoException("Movimentos estoque não encontrados");
    }

    public MovimentoEstoque getMovimentoEstoque(long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            MovimentoEstoque movimentoEstoque = session.get(MovimentoEstoque.class, id);

            transaction.commit();

            return movimentoEstoque;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        throw new ProdutoNaoEncontradoException("Movimento estoque não encontrado");
    }

    public void salvarOuEditarMovimentoEstoque(MovimentoEstoque movimentoEstoque) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            if(movimentoEstoque.getId() == null)
                session.saveOrUpdate(movimentoEstoque);
            else {
                MovimentoEstoque movimentoEstoqueBanco = session.get(MovimentoEstoque.class, movimentoEstoque.getId());
                movimentoEstoqueBanco.setId(movimentoEstoque.getId());
                movimentoEstoqueBanco.setQuantidade(movimentoEstoque.getQuantidade());
                movimentoEstoqueBanco.setTipoMovimentacao(movimentoEstoque.getTipoMovimentacao());
                movimentoEstoqueBanco.setProduto(movimentoEstoque.getProduto());
                movimentoEstoqueBanco.setDataMovimento(movimentoEstoque.getDataMovimento());
                movimentoEstoqueBanco.setQuantidade(movimentoEstoque.getQuantidade());
                session.saveOrUpdate(movimentoEstoque);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
