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

}
