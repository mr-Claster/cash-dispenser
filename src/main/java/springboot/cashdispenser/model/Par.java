package springboot.cashdispenser.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "pars")
public class Par implements Cloneable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private Integer par;

    public Par() {
    }

    public Par(Integer par) {
        this.par = par;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPar() {
        return par;
    }

    public void setPar(Integer par) {
        this.par = par;
    }

    @Override
    public Par clone() {
        try {
            return (Par) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Par par1 = (Par) o;
        return Objects.equals(id, par1.id)
                && Objects.equals(par, par1.par);
    }

    @Override
    public String toString() {
        return "Par{"
                + "id=" + id
                + ", par=" + par
                + '}';
    }
}
