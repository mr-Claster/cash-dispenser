package springboot.cashdispenser.service;

import springboot.cashdispenser.model.Card;
import springboot.cashdispenser.model.MoneyStack;
import java.math.BigDecimal;
import java.util.List;

public interface CashDispenserService {
    void putMoneyOnCard(String cardNumber, List<MoneyStack> bills);

    List<MoneyStack> getMoneyFromCard(Card card, Long amount);

    void transferMoneyToAnotherCard(Card senderCard, String acceptCard, BigDecimal amount);
}
