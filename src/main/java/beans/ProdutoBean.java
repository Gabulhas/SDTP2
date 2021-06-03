package beans;

import dao.ProdutoDao;
import dao.TransacaoDao;
import entities.ProdutoEntity;
import entities.TransacaoEntity;
import utils.SessionUtils;
import utils.ToString;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.Date;
import java.util.*;

@Named(value = "produtoBean")
@SessionScoped
public class ProdutoBean implements Serializable {

    @EJB
    private ProdutoDao produtoDao;

    @EJB
    private TransacaoDao transacaoDao;

    public ProdutoEntity getSelectionado() {
        return selectionado;
    }

    public void setSelectionado(ProdutoEntity selectionado) {
        this.selectionado = selectionado;
    }

    private ProdutoEntity selectionado;


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

        ProdutoEntity temp;
        try {
            temp = produtoDao.getProdutoId(Integer.parseInt(query));
            if (temp == null) {
                temp = produtoDao.getProdutoModelo(query);
                if (temp == null) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Produto Não Encontrado."));
                } else {
                    this.produtos.add(temp);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(""));
                }

            } else {
                this.produtos.add(temp);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(""));
            }
        } catch (NumberFormatException e) {
            temp = produtoDao.getProdutoModelo(query);
            if (temp == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Produto Não Encontrado."));
            } else {
                this.produtos.add(temp);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(""));
            }
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

    public String categoria(String id) {
        System.out.println(id);
        return "procurar_cliente";
    }

    public String criarProduto() {
        if(produtoDao.criarProduto(novoProduto)){
            return "ler_produto";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Não foi possível criar produto."));
            return "";
        }
    }

    public String mergirProduto() {
        produtoDao.mergirProduto(novoProduto);
        return "ler_produto";
    }

    public String apagarProduto() {
        produtoDao.apagerProdutoById(novoProduto.getId());
        apagarEstado();
        return "ler_produto";
    }

    public List<ProdutoEntity> getTodos() {
        return produtoDao.getProdutos();
    }

    public List<ProdutoEntity> getTodosForaDeStock() {
        return produtoDao.getForaDeStock();
    }

    public String escolher(ProdutoEntity produtoEntity) {

        this.novoProduto = produtoEntity;
        return "editar_produto";
    }

    public void apagarEstado() {
        this.novoProduto = new ProdutoEntity();
        this.produtos = new ArrayList<>();
        this.categoriaSelectionada = null;
        this.query = null;
        this.quantidadeDeCompra = 0;
        this.selectionado = null;

    }

    int quantidadeDeCompra;

    public int getQuantidadeDeCompra() {
        return quantidadeDeCompra;
    }

    public void setQuantidadeDeCompra(int quantidadeDeCompra) {
        this.quantidadeDeCompra = quantidadeDeCompra;
    }

    public String comprar(ProdutoEntity produtoEntity) {


        if (quantidadeDeCompra > 0 && quantidadeDeCompra <= produtoEntity.getStock()) {
            TransacaoEntity transacaoEntity = new TransacaoEntity();
            transacaoEntity.setProdutoId(produtoEntity.getId());
            transacaoEntity.setQuantidade(quantidadeDeCompra);
            transacaoEntity.setTipo("espera");
            long millis = System.currentTimeMillis();
            java.sql.Date date = new java.sql.Date(millis);
            transacaoEntity.setData(date);
            transacaoEntity.setUtilizadorId(Integer.parseInt(SessionUtils.getLoggedID()));
            System.out.println(ToString.transacaoToString(transacaoEntity));
            if (transacaoDao.criarTransacao(transacaoEntity)) {
                return "ver_compras";
            }
        }

        //TODO: Não foi possível fazer a transação
        System.out.println("Não foi Possível");
        return "ler_produto";

    }

    public String consultarProduto(ProdutoEntity produtoEntity) {
        setQuery(produtoEntity.getModelo());
        return "ler_produto";
    }

}
