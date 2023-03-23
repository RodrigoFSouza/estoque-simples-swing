package br.com.portifolio.dao;


import br.com.portifolio.entity.Produto;
import br.com.portifolio.exception.ProdutoNaoEncontradoException;
import br.com.portifolio.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ProdutoDao implements IProdutoDao {

    public List<Produto> listarTodosProdutos() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            String hql = "FROM Produto ORDER BY id, descricao";
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

        throw new ProdutoNaoEncontradoException("Produtos não encontrados");
    }

    public Produto buscarProdutoPorId(long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Produto produto = session.get(Produto.class, id);

            transaction.commit();

            return produto;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        throw new ProdutoNaoEncontradoException("Produto não encontrado");
    }

    public void salvarOuEditarProduto(Produto produto) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            if (produto.getId() == null) {
                session.saveOrUpdate(produto);
            } else {
                Produto produtoBanco = session.get(Produto.class, produto.getId());
                produtoBanco.setId(produto.getId());
                produtoBanco.setValor(produto.getValor());
                produtoBanco.setQuantidadeAtual(produto.getQuantidadeAtual());
                produtoBanco.setDescricao(produto.getDescricao());
                produtoBanco.setQuantidadeMinima(produto.getQuantidadeMinima());
                session.saveOrUpdate(produtoBanco);
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
