package beans;

import dao.UtilizadoresDao;
import entities.UtilizadoresEntity;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.io.Serializable;

@ManagedBean(name = "utilizadorBean")
@SessionScoped
public class UtilizadorBean implements Serializable {
    private String nome;
    private String id;
    private String email;
    private String query;

    @EJB
    UtilizadoresDao utilizadoresDao;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
        procurarQuery();
    }

    public UtilizadoresEntity getNovoUtilizador() {
        return novoUtilizador;
    }

    public void setNovoUtilizador(UtilizadoresEntity novoUtilizador) {
        this.novoUtilizador = novoUtilizador;
    }

    UtilizadoresEntity novoUtilizador = new UtilizadoresEntity();

    public void procurarQuery() {
        if (query == null) {
            return;
        }

        if (this.query.contains("@")) {
            novoUtilizador = utilizadoresDao.getUtilizadorPorEmail(query);
        } else {
            try {
                Double.parseDouble(query);
                novoUtilizador = utilizadoresDao.getUtilizadorPorId(query);
            } catch (NumberFormatException nfe) {
                try {
                    novoUtilizador = utilizadoresDao.getUtilizadorPorNome(query);
                } catch (Exception e) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cliente Não Encontrado."));
                }
            }
        }
        if (novoUtilizador == null) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cliente Não Encontrado."));
        }
    }

    public String consultarUtilizador(UtilizadoresEntity utilizadoresEntity) {
        setQuery(utilizadoresEntity.getNome());
        return "procurar_cliente";
    }

}

