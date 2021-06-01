package beans;

import dao.UtilizadoresDao;
import entities.UtilizadoresEntity;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
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
                novoUtilizador = utilizadoresDao.getUtilizadorPorNome(query);
            }
        }
    }
}

