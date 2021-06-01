package beans;

import dao.ProdutoDao;
import entities.ProdutoEntity;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Named(value = "produtoBean")
@RequestScoped
public class ProdutoBean {

    @EJB
    private ProdutoDao produtoDao;


    public String getCategoriaSelectionada() {
        return categoriaSelectionada;
    }

    public void setCategoriaSelectionada(String categoriaSelectionada) {
        this.categoriaSelectionada = categoriaSelectionada;
        this.produtos = produtoDao.getProdutosCategoria(categoriaSelectionada);
        System.out.println("Produtos encontrados " + produtos.size());
    }

    String categoriaSelectionada;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
        findAndSetProduto(query);
    }

    private void findAndSetProduto(String query) {
        query = query.trim();
        produtos.clear();
        ProdutoEntity temp = produtoDao.getProdutoId(query);
        if (temp == null) {
            temp = produtoDao.getProdutoModelo(query);
            if (temp == null) {
                //TODO: ERROR
            } else {
                this.produtos.add(temp);
            }

        } else {
            this.produtos.add(temp);
        }

    }

    String query;

    List<ProdutoEntity> produtos = new ArrayList<>();

    public List<ProdutoEntity> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ProdutoEntity> produtos) {
        this.produtos = produtos;
    }

    public List<String> getCategoriasDisponiveis() {
        return produtoDao.getCategoriasDisponiveis();
    }


    ProdutoEntity novoProduto = new ProdutoEntity();

    public ProdutoEntity getNovoProduto() {
        return novoProduto;
    }

    public void setNovoProduto(ProdutoEntity produtoEntity) {
        this.novoProduto = produtoEntity;
    }

    public String alterarProduto() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params =
                fc.getExternalContext().getRequestParameterMap();
        String id = params.get("bean_id");
        System.out.println("A ALTERAR ID: " + id);
        return "procurar_cliente";

    }

    public String criarProduto() {
        produtoDao.criarProduto(novoProduto);
        return "ler_produto";
    }

}
