package entities;

import javax.persistence.*;

@Entity
@Table(name = "produto", schema = "moveis")
@NamedQuery(name = "Produto.findAll", query = "SELECT p FROM ProdutoEntity p")
public class ProdutoEntity {
    private Long id;
    private String categoria;
    private String precoCompra;
    private String precoVenda;
    private int stock;
    private int stockMin;

    @Id
    @GeneratedValue
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "categoria")
    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Basic
    @Column(name = "precoCompra")
    public String getPrecoCompra() {
        return precoCompra;
    }

    public void setPrecoCompra(String precoCompra) {
        this.precoCompra = precoCompra;
    }

    @Basic
    @Column(name = "precoVenda")
    public String getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(String precoVenda) {
        this.precoVenda = precoVenda;
    }

    @Basic
    @Column(name = "stock")
    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Basic
    @Column(name = "stockMin")
    public int getStockMin() {
        return stockMin;
    }

    public void setStockMin(int stockMin) {
        this.stockMin = stockMin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProdutoEntity that = (ProdutoEntity) o;

        if (stock != that.stock) return false;
        if (stockMin != that.stockMin) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (categoria != null ? !categoria.equals(that.categoria) : that.categoria != null) return false;
        if (precoCompra != null ? !precoCompra.equals(that.precoCompra) : that.precoCompra != null) return false;
        if (precoVenda != null ? !precoVenda.equals(that.precoVenda) : that.precoVenda != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (categoria != null ? categoria.hashCode() : 0);
        result = 31 * result + (precoCompra != null ? precoCompra.hashCode() : 0);
        result = 31 * result + (precoVenda != null ? precoVenda.hashCode() : 0);
        result = 31 * result + stock;
        result = 31 * result + stockMin;
        return result;
    }
}
