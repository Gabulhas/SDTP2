package beans;

import dao.UtilizadoresDao;
import entities.UtilizadoresEntity;
import utils.SessionUtils;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
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
    private String morada;
    private String email;

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


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

        if (temp != null) {

            //Definir sessão do utilizador
            HttpSession session = SessionUtils.getSession();
            temp.setPassword(null);
            session.setAttribute("user_in_session", temp);

            //apagamos a password por questões de segurança
            //TODO: Diferenciar entre os tipos de utilizador
            if (temp.getTipo().equals("admin")) {
                return "/admin/admin_index";
            } else {
                return "/user/user_index";
            }

        }

        //TODO: Caso o utilizador não consiga dar login terá que dar erro

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Conta Inválida."));
        return "login_form";
    }

    public String register() {
        UtilizadoresEntity temp = new UtilizadoresEntity();
        temp.setPassword(this.password);
        temp.setNome(this.nome);
        temp.setTipo("normal");
        temp.setEmail(this.email);
        temp.setMorada(this.morada);
        if (utilizadoresDao.registarUtilizador(temp)) {
            return validate();
        }
        //TODO:Mostrar erro
        return "/index";
    }

    public String logout() {
        HttpSession session = SessionUtils.getSession();
        session.invalidate();
        return "/index?faces-redirect=true";
    }

    public String getLoggedName() {
        return SessionUtils.getLoggedName();
    }

    public String getLoggedID() {
        return SessionUtils.getLoggedID();
    }

    public String getLoggedType() {
        return SessionUtils.getLoggedType();
    }

}
