package springboot.cashdispenser.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springboot.cashdispenser.model.Card;
import springboot.cashdispenser.model.MoneyStack;
import springboot.cashdispenser.service.CashDispenserService;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("")
public class CashDispenserController {
    private final CashDispenserService cashDispenserService;

    public CashDispenserController(CashDispenserService cashDispenserService) {
        this.cashDispenserService = cashDispenserService;
    }

    @PostMapping
    public void putMoneyOnCard(String cardNumber, List<MoneyStack> bills) {
        cashDispenserService.putMoneyOnCard(cardNumber, bills);
    }

    @PostMapping("/d")
    public List<MoneyStack> getMoneyFromCard(Card card, Long amount) {
        return cashDispenserService.getMoneyFromCard(card, amount);
    }

    @PostMapping("/g")
    public void transferMoneyToAnotherCard(Card senderCard, String acceptCard, BigDecimal amount) {
        cashDispenserService.transferMoneyToAnotherCard(senderCard, acceptCard, amount);
    }
}
