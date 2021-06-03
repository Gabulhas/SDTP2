package beans;

import dao.TransacaoDao;
import entities.TransacaoEntity;
import utils.SessionUtils;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named(value = "transacaoBean")
@RequestScoped
public class TransacaoBean {

    @EJB
    TransacaoDao transacaoDao;

    List<TransacaoEntity> transacaoEntityList = new ArrayList<>();

    public List<TransacaoEntity> getTodas() {
        return transacaoDao.getTodas();
    }

    public List<TransacaoEntity> getTodasJoin() {
        return transacaoDao.getTodasJoined();
    }

    public List<TransacaoEntity> getComprasDoProprio() {
        return transacaoDao.getTodasUtilizadorId(Integer.parseInt(SessionUtils.getLoggedID()));
    }

    public List<TransacaoEntity> getComprasDoProprioJoin() {
        return transacaoDao.getTodasJoinedUtilizadorId(Integer.parseInt(SessionUtils.getLoggedID()));
    }

    public String cancelarTransacao(TransacaoEntity transacaoEntity) {
        transacaoDao.cancelarTransacao(transacaoEntity);
        return "";
    }

    public String completarTransacao(TransacaoEntity transacaoEntity) {
        transacaoDao.completarTransacao(transacaoEntity);
        return "";
    }

}
