package dao;

import entities.ProdutoEntity;
import entities.UtilizadoresEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Stateless
public class ProdutoDao {

    @PersistenceContext(unitName = "default")
    private EntityManager em;

    public List<ProdutoEntity> getProdutos() {
        return (List<ProdutoEntity>) em.createNamedQuery("Produto.findAll").getResultList();
    }

    public List<ProdutoEntity> getProdutosCategoria(String categoria) {
        return (List<ProdutoEntity>) em.createNamedQuery("Produto.findAllCategory").setParameter("categoria", categoria).getResultList();
    }

    public List<String> getCategoriasDisponiveis() {
        return (List<String>) em.createQuery("SELECT DISTINCT(p.categoria) FROM ProdutoEntity  p").getResultList();
    }

    public List<ProdutoEntity> getForaDeStock() {
        return (List<ProdutoEntity>) em.createNamedQuery("Produto.findForaDeStock").getResultList();
    }

    public boolean criarProduto(ProdutoEntity produtoEntity) {
        try {
            em.persist(produtoEntity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public ProdutoEntity mergirProduto(ProdutoEntity produtoEntity) {
        em.merge(produtoEntity);
        return produtoEntity;
    }

    public void apagerProdutoById(int id) {
        ProdutoEntity temp = this.getProdutoId(id);
        System.out.println(temp);
        em.remove(temp);
    }

    public ProdutoEntity getProdutoId(int id) {
        try {
            ProdutoEntity temp = (ProdutoEntity) em.createNamedQuery("Produto.findById").setParameter("id", id).getSingleResult();
            System.out.println("CEBOLA->" + temp.getId());
            return temp;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public ProdutoEntity getProdutoModelo(String modelo) {
        try {
            ProdutoEntity temp = (ProdutoEntity) em.createNamedQuery("Produto.findByModelo").setParameter("modelo", modelo).getSingleResult();
            return temp;
        } catch (Exception e) {
            return null;
        }

    }

}
