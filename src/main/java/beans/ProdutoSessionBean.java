package beans;

import dao.ProdutoDao;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named(value = "produtoSessionBean")
@SessionScoped
public class ProdutoSessionBean implements Serializable {
    @EJB
    private ProdutoDao produtoDao;

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
