package springboot.cashdispenser.service;

import java.util.List;
import springboot.cashdispenser.model.Bills;
import springboot.cashdispenser.model.Card;

public interface CashDispenserService {
    void putMoneyOnCard(String cardNumber, List<Bills> billsList);

    List<Bills> getMoneyFromCard(Card card, Integer amount);

    public void addListOfBills(List<Bills> billsList);
}
