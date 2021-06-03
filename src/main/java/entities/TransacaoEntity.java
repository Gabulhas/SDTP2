package entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "transacao", schema = "moveis")
@NamedQueries({
        @NamedQuery(name = "transacao.findAll", query = "SELECT t from TransacaoEntity t"),
        @NamedQuery(name = "transacao.findAllUserID", query = "SELECT t from TransacaoEntity t WHERE t.utilizadorId = :utilizadorId"),
        @NamedQuery(name = "transacao.findAllJoin", query = "SELECT t FROM TransacaoEntity t INNER JOIN ProdutoEntity p ON t.produtoId = p.id INNER JOIN UtilizadoresEntity u on t.utilizadorId = u.id"),
        @NamedQuery(name = "transacao.findAllJoinByUserID", query = "SELECT t FROM TransacaoEntity t INNER JOIN ProdutoEntity p ON t.produtoId = p.id INNER JOIN UtilizadoresEntity u on t.utilizadorId = u.id AND u.id = :utilizadorID"),

})
public class TransacaoEntity {
    private String tipo;
    private int quantidade;
    private int produtoId;
    private int id;
    private int utilizadorId;
    private Date data;
    private ProdutoEntity produtoByProdutoId;
    private UtilizadoresEntity utilizadoresByUtilizadorId;

    @Basic
    @Column(name = "tipo")
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Basic
    @Column(name = "quantidade")
    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Basic
    @Column(name = "produtoId")
    public int getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(int produtoId) {
        this.produtoId = produtoId;
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "utilizadorId")
    public int getUtilizadorId() {
        return utilizadorId;
    }

    public void setUtilizadorId(int utilizadorId) {
        this.utilizadorId = utilizadorId;
    }

    @Basic
    @Column(name = "data")
    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TransacaoEntity that = (TransacaoEntity) o;

        if (quantidade != that.quantidade) return false;
        if (produtoId != that.produtoId) return false;
        if (id != that.id) return false;
        if (utilizadorId != that.utilizadorId) return false;
        if (tipo != null ? !tipo.equals(that.tipo) : that.tipo != null) return false;
        if (data != null ? !data.equals(that.data) : that.data != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tipo != null ? tipo.hashCode() : 0;
        result = 31 * result + quantidade;
        result = 31 * result + produtoId;
        result = 31 * result + id;
        result = 31 * result + utilizadorId;
        result = 31 * result + (data != null ? data.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "produtoId", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public ProdutoEntity getProdutoByProdutoId() {
        return produtoByProdutoId;
    }

    public void setProdutoByProdutoId(ProdutoEntity produtoByProdutoId) {
        this.produtoByProdutoId = produtoByProdutoId;
    }

    @ManyToOne
    @JoinColumn(name = "utilizadorId", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public UtilizadoresEntity getUtilizadoresByUtilizadorId() {
        return utilizadoresByUtilizadorId;
    }

    public void setUtilizadoresByUtilizadorId(UtilizadoresEntity utilizadoresByUtilizadorId) {
        this.utilizadoresByUtilizadorId = utilizadoresByUtilizadorId;
    }
}
