package springboot.cashdispenser.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "cards")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, name = "card_number")
    private String cardNumber;
    private String CVV;
    private String PIN;
    @Column(name = "expiration_date")
    private LocalDate expirationDate;
    private BigDecimal amount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCVV() {
        return CVV;
    }

    public void setCVV(String cardVerificationValue) {
        this.CVV = cardVerificationValue;
    }

    public String getPIN() {
        return PIN;
    }

    public void setPIN(String personalIdentificationNumber) {
        this.PIN = personalIdentificationNumber;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Card card = (Card) o;
        return Objects.equals(id, card.id)
                && Objects.equals(cardNumber, card.cardNumber)
                && Objects.equals(CVV, card.CVV)
                && Objects.equals(PIN, card.PIN)
                && Objects.equals(expirationDate, card.expirationDate)
                && Objects.equals(amount.stripTrailingZeros(), card.amount.stripTrailingZeros());
    }
}
