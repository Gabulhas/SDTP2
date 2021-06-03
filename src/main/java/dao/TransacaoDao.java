package dao;

import entities.ProdutoEntity;
import entities.TransacaoEntity;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Stateless
public class TransacaoDao {
    @PersistenceContext(unitName = "default")
    private EntityManager em;
    EntityTransaction compraTransaction;


    public List<TransacaoEntity> getTodas() {
        return (List<TransacaoEntity>) em.createNamedQuery("transacao.findAll").getResultList();
    }

    public List<TransacaoEntity> getTodasUtilizadorId(int utilizadorId) {
        return (List<TransacaoEntity>) em.createNamedQuery("transacao.findAllUserID").setParameter("utilizadorId", utilizadorId).getResultList();
    }


    public boolean criarTransacao(TransacaoEntity transacaoEntity) {

        //Locked para evitar compras de algo que não existe
        ProdutoEntity produtoEntity = em.find(ProdutoEntity.class, transacaoEntity.getProdutoId(), LockModeType.PESSIMISTIC_READ);

        int novaQuantidadeEmStock = produtoEntity.getStock() - transacaoEntity.getQuantidade();
        //Caso não haja o total disponível
        if (novaQuantidadeEmStock < 0) {
            return false;
        } else {
            em.persist(transacaoEntity);
            produtoEntity.setStock(novaQuantidadeEmStock);
            em.merge(produtoEntity);
            return true;
        }

    }


}