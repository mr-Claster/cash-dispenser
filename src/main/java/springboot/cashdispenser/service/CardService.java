package springboot.cashdispenser.service;

import java.math.BigDecimal;
import springboot.cashdispenser.model.Card;

public interface CardService {
    Card save(Card card);

    Card update(Card card);

    void checkCard(Card card);

    void checkAmountOnCard(Card card, BigDecimal amount);

    Card getByCardNumber(String cardNumber);

    void transferMoneyToAnotherCard(Card senderCard,
                                    String numberOfAcceptCard,
                                    BigDecimal amount);
}
