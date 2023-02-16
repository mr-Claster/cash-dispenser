package springboot.cashdispenser.service;

import springboot.cashdispenser.model.Card;
import java.math.BigDecimal;

public interface CardService {
    Card save(Card card);

    Card update(Card card);

    void checkCard(Card card);

    void checkAmountOnCard(Card card, BigDecimal amount);

    Card getByCardNumber(String cardNumber);
}
