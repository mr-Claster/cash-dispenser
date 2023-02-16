package springboot.cashdispenser.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Objects;

@Entity
public class MoneyStack implements Cloneable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long number;
    private Integer par;

    public MoneyStack() {
    }

    public MoneyStack(Long number, Integer par) {
        this.number = number;
        this.par = par;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Integer getPar() {
        return par;
    }

    public void setPar(Integer par) {
        this.par = par;
    }

    @Override
    public MoneyStack clone() {
        try {
            return (MoneyStack) super.clone();
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
        MoneyStack moneyStack = (MoneyStack) o;
        return Objects.equals(id, moneyStack.id)
                && Objects.equals(number, moneyStack.number)
                && Objects.equals(par, moneyStack.par);
    }

    @Override
    public String toString() {
        return "OneDenominationBills{"
                + "id=" + id
                + ", number=" + number
                + ", par=" + par
                + '}';
    }
}
