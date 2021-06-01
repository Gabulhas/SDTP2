package dao;

import entities.UtilizadoresEntity;

import javax.ejb.Stateless;
import javax.persistence.*;
import javax.transaction.Transactional;

@Transactional
@Stateless
public class UtilizadoresDao {

    @PersistenceContext(unitName = "default")
    private EntityManager em;

    public UtilizadoresEntity getUtilizador(String nome, String password) {
        try {
            UtilizadoresEntity temp = (UtilizadoresEntity) em.createNamedQuery("utilizadores.getUtilizadorNomeEPassword").setParameter("nome", nome).setParameter("password", password).getSingleResult();
            return temp;
        } catch (NoResultException e) {
            return null;
        }
    }

    public boolean registarUtilizador(UtilizadoresEntity utilizadoresEntity) {
        try {
            System.out.println(utilizadoresEntity.toString());
            System.out.println(em.toString());
            em.persist(utilizadoresEntity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
