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

        UtilizadoresEntity temp = (UtilizadoresEntity) em.createNamedQuery("utilizadores.getUtilizadorNome").setParameter("nome", this.nome).getSingleResult();

        if (temp.getPassword().equals(this.password)) {

            //apagamos a password por questões de segurança
            temp.setPassword(null);

            //TODO: Diferenciar entre os tipos de utilizador
            this.utilizadoresEntity = temp;
            return "catalogo";
        }

        //TODO: Caso o utilizador não consiga dar login terá que dar erro
        return null;
    }

    public String register() {
        int tamanhoLista = em.createQuery("SELECT U.nome from UtilizadoresEntity U where U.nome = :nome").setParameter("nome", this.nome).getResultList().size();


        //Se o user não está registado
        if (tamanhoLista == 0) {
            UtilizadoresEntity temp = new UtilizadoresEntity();
            temp.setPassword(this.password);
            temp.setNome(this.nome);
            temp.setTipo("normal");
            em.persist(temp);
            return "ler_produto";
        }
        return "index";

    }

}
