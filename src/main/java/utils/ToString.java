package utils;

import entities.TransacaoEntity;

public class ToString {
    public static String transacaoToString(TransacaoEntity transacaoEntity) {
        return "Tipo:" + transacaoEntity.getTipo() + "\nID" + transacaoEntity.getId() + "\nUSERID:" + transacaoEntity.getUtilizadorId()
                + "\nData:" + transacaoEntity.getData() + "\nProdutoID:" + transacaoEntity.getProdutoId() + "\n" + transacaoEntity.getQuantidade();
    }
}
