package springboot.cashdispenser.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import springboot.cashdispenser.model.Card;
import springboot.cashdispenser.model.MoneyStack;
import springboot.cashdispenser.service.CardService;
import springboot.cashdispenser.service.CashDispenserService;
import springboot.cashdispenser.service.MoneyStackService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CashDispenserServiceImpl implements CashDispenserService {
    private static final List<Integer> ACCEPTABLE_PARS = List.of(100, 200, 500);
    private final CardService cardService;
    private final MoneyStackService moneyStackService;

    public CashDispenserServiceImpl(CardService cardService,
                                    MoneyStackService moneyStackService) {
        this.cardService = cardService;
        this.moneyStackService = moneyStackService;
    }

    @Override
    @Transactional
    public void putMoneyOnCard(String cardNumber, List<MoneyStack> bills) {
        Card card = cardService.getByCardNumber(cardNumber);
        long sum = bills.stream()
                .mapToLong(bill -> {
                    if (ACCEPTABLE_PARS.contains(bill.getPar())) {
                       return bill.getPar() * bill.getNumber();
                    } else {
                        throw new RuntimeException("unsuitable bill\n  suitable bills: " + ACCEPTABLE_PARS);
                    }
                }).sum();
        card.setAmount(card.getAmount().add(BigDecimal.valueOf(sum)));
        moneyStackService.addMoney(bills);
    }

    @Override
    @Transactional
    public List<MoneyStack> getMoneyFromCard(Card inputCard, Long amount) {
        cardService.checkCard(inputCard);
        cardService.checkAmountOnCard(inputCard, BigDecimal.valueOf(amount));
        List<MoneyStack> moneyStacks = moneyStackService.getAll();
        List<MoneyStack> output = new ArrayList<>();
        for (MoneyStack moneyStack : moneyStacks) {
            long numberBillsOfOnePar = amount / moneyStack.getPar();
            if (moneyStack.getNumber() >= numberBillsOfOnePar) {
                amount -= numberBillsOfOnePar * moneyStack.getPar();
                MoneyStack outputMoneyStack
                        = new MoneyStack(numberBillsOfOnePar, moneyStack.getPar());
                output.add(outputMoneyStack);
                moneyStack.setNumber(moneyStack.getNumber() - numberBillsOfOnePar);
            } else {
                amount -= moneyStack.getNumber() * moneyStack.getPar();
                output.add(moneyStack.clone());
                moneyStack.setNumber(0L);
            }
            if (amount == 0) {
                break;
            }
        }
        if (amount > 0) {
            throw new RuntimeException("there is not enough money in the ATM");
        }
        moneyStacks.forEach(moneyStackService::update);
        return output;
    }

    @Override
    @Transactional
    public void transferMoneyToAnotherCard(Card senderCard, String NumberOfAcceptCard, BigDecimal amount) {
        cardService.checkCard(senderCard);
        cardService.checkAmountOnCard(senderCard, amount);
        senderCard.setAmount(senderCard.getAmount().subtract(amount));
        Card acceptCard = cardService.getByCardNumber(NumberOfAcceptCard);
        acceptCard.setAmount(acceptCard.getAmount().add(amount));
        cardService.update(senderCard);
        cardService.update(acceptCard);
    }
}
