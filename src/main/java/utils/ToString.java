package utils;

import entities.TransacaoEntity;
import entities.UtilizadoresEntity;

public class ToString {
    public static String transacaoToString(TransacaoEntity transacaoEntity) {
        return "Tipo:" + transacaoEntity.getTipo() + "\nID" + transacaoEntity.getId() + "\nUSERID:" + transacaoEntity.getUtilizadorId()
                + "\nData:" + transacaoEntity.getData() + "\nProdutoID:" + transacaoEntity.getProdutoId() + "\n" + transacaoEntity.getQuantidade();
    }

    public static String utilizadorToString(UtilizadoresEntity utilizadoresEntity) {
        return "Tipo:" + " " + utilizadoresEntity.getTipo() + " " + "\nID" + " " + utilizadoresEntity.getNome() + " " + utilizadoresEntity.getPassword() + " " + utilizadoresEntity.getEmail() + " " + utilizadoresEntity.getMorada();
    }
}
