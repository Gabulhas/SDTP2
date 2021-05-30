package beans;

import entities.UtilizadoresEntity;

import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;

@ManagedBean(name = "clientBean")
@SessionScoped
public class ClientBean implements Serializable {
    public UtilizadoresEntity getUtilizadoresEntity() {
        return utilizadoresEntity;
    }

    public void setUtilizadoresEntity(UtilizadoresEntity utilizadoresEntity) {
        this.utilizadoresEntity = utilizadoresEntity;
    }

    private String nome;
    private String password;
    private UtilizadoresEntity utilizadoresEntity;

    @PersistenceContext(unitName = "default")
    private EntityManager em;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String validate() {
        String tempPassword = (String) em.createQuery("SELECT U.password FROM UtilizadoresEntity U WHERE U.nome = :nome").setParameter("nome", this.nome).getSingleResult();
        if (tempPassword.equals(this.password)) {
            return "catalogo";
        }

        return null;
    }


}
