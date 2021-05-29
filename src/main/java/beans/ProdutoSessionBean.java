package beans;

import entities.ProdutoEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ProdutoSessionBean {

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


}
