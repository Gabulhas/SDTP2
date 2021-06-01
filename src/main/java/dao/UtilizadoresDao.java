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

    public UtilizadoresEntity getUtilizadorPorId(String id) {
        UtilizadoresEntity temp = (UtilizadoresEntity) em.createQuery("select u from UtilizadoresEntity u where u.id = :id").setParameter("id", id).getSingleResult();
        return temp;
    }

    public UtilizadoresEntity getUtilizadorPorEmail(String email) {
        UtilizadoresEntity temp = (UtilizadoresEntity) em.createQuery("select u from UtilizadoresEntity u where u.email= :email").setParameter("email", email).getSingleResult();
        return temp;
    }

    public UtilizadoresEntity getUtilizadorPorNome(String nome) {
        UtilizadoresEntity temp = (UtilizadoresEntity) em.createQuery("select u from UtilizadoresEntity u where u.nome = :nome").setParameter("nome", nome).getSingleResult();
        return temp;
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
