package beans;

import dao.UtilizadoresDao;
import entities.UtilizadoresEntity;
import utils.SessionUtils;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.io.Serializable;

@ManagedBean(name = "clientBean")
@SessionScoped
public class ClientBean implements Serializable {

    @EJB
    UtilizadoresDao utilizadoresDao;
    private String nome;
    private String password;


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

        UtilizadoresEntity temp = utilizadoresDao.getUtilizador(this.nome, this.password);

        if (temp.getPassword().equals(this.password)) {

            //apagamos a password por questões de segurança
            temp.setPassword(null);

            //TODO: Diferenciar entre os tipos de utilizador
            return "catalogo";
        }

        //TODO: Caso o utilizador não consiga dar login terá que dar erro
        return null;
    }

    public String register() {
        UtilizadoresEntity temp = new UtilizadoresEntity();
        temp.setPassword(this.password);
        temp.setNome(this.nome);
        temp.setTipo("normal");
        if (utilizadoresDao.registarUtilizador(temp)) {
            return "ler_produto";
        }
        return "index";

    }

    public String logout() {
        HttpSession session = SessionUtils.getSession();
        session.invalidate();
        return "login";
    }

}
