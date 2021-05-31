package entities;

import javax.persistence.*;

@Entity
@Table(name = "utilizadores", schema = "moveis")
@NamedQueries({
        @NamedQuery(name = "utilizadores.getUtilizadorNome", query = "SELECT u from UtilizadoresEntity u where u.nome = :nome"),
        @NamedQuery(name = "utilizadores.getUtilizadorNomeEPassword", query = "SELECT u from UtilizadoresEntity u where u.nome = :nome and u.password = :password")})

public class UtilizadoresEntity {
    private Long id;
    private String nome;
    private String password;
    private String tipo;

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
    @Column(name = "nome")
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "tipo")
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UtilizadoresEntity that = (UtilizadoresEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (nome != null ? !nome.equals(that.nome) : that.nome != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (tipo != null ? !tipo.equals(that.tipo) : that.tipo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (tipo != null ? tipo.hashCode() : 0);
        return result;
    }
}
