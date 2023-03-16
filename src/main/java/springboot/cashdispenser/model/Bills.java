package springboot.cashdispenser.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "bills")
public class Bills implements Cloneable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer number;
    @ManyToOne
    private Par par;

    public Bills() {
    }

    public Bills(Integer number, Integer par) {
        this.number = number;
        this.par = new Par(par);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Par getPar() {
        return par;
    }

    public void setPar(Par par) {
        this.par = par;
    }

    @Override
    public Bills clone() {
        try {
            return (Bills) super.clone();
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
        Bills that = (Bills) o;
        return Objects.equals(id, that.id)
                && Objects.equals(number, that.number)
                && Objects.equals(par, that.par);
    }

    @Override
    public String toString() {
        return "Bills{"
                + "id=" + id
                + ", number=" + number
                + ", par=" + par
                + '}';
    }
}
