package controllers;

import beans.ProdutoSessionBean;
import entities.ProdutoEntity;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named(value = "produtoController")
@RequestScoped
public class ProdutoController {

    @EJB
    private ProdutoSessionBean produtoSessionBean;

    List<ProdutoEntity> produtos = new ArrayList<>();

    public List<ProdutoEntity> getProdutos() {
        produtos = produtoSessionBean.getProdutos();
        return produtos;
    }

    public List<ProdutoEntity> getProdutosCategoria(String categoria) {
        produtos = produtoSessionBean.getProdutos();
        return produtoSessionBean.getProdutosCategoria(categoria);
    }

    public List<String> getCategoriasDisponiveis(){
        return produtoSessionBean.getCategoriasDisponiveis();
    }


}
