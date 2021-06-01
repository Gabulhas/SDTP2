package entities;

import javax.persistence.*;

@Entity
@Table(name = "produto", schema = "moveis")
@NamedQuery(name = "Produto.findAll", query = "SELECT p FROM ProdutoEntity p")
@NamedQuery(name = "Produto.findById", query = "SELECT p FROM ProdutoEntity p where p.id = :id")
@NamedQuery(name = "Produto.findByModelo", query = "SELECT p FROM ProdutoEntity p where p.modelo= :modelo")
@NamedQuery(name = "Produto.findAllCategory", query = "SELECT p FROM ProdutoEntity p WHERE p.categoria = :categoria")
public class ProdutoEntity {
    private int id;
    private String categoria;
    private double precoCompra;
    private double precoVenda;
    private int stock;
    private int stockMin;
    private String modelo;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
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
    public double getPrecoCompra() {
        return precoCompra;
    }

    public void setPrecoCompra(double precoCompra) {
        this.precoCompra = precoCompra;
    }

    @Basic
    @Column(name = "precoVenda")
    public double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(double precoVenda) {
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

    @Basic
    @Column(name = "modelo")
    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProdutoEntity that = (ProdutoEntity) o;

        if (id != that.id) return false;
        if (Double.compare(that.precoCompra, precoCompra) != 0) return false;
        if (Double.compare(that.precoVenda, precoVenda) != 0) return false;
        if (stock != that.stock) return false;
        if (stockMin != that.stockMin) return false;
        if (categoria != null ? !categoria.equals(that.categoria) : that.categoria != null) return false;
        if (modelo != null ? !modelo.equals(that.modelo) : that.modelo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (categoria != null ? categoria.hashCode() : 0);
        temp = Double.doubleToLongBits(precoCompra);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(precoVenda);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + stock;
        result = 31 * result + stockMin;
        result = 31 * result + (modelo != null ? modelo.hashCode() : 0);
        return result;
    }
}
