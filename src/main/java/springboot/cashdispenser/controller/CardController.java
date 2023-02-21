package springboot.cashdispenser.controller;

import java.math.BigDecimal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springboot.cashdispenser.model.Card;
import springboot.cashdispenser.service.CardService;

@RestController
@RequestMapping("/cards")
public class CardController {
    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping
    public void transferMoneyToAnotherCard(Card senderCard, String acceptCard, BigDecimal amount) {
        cardService.transferMoneyToAnotherCard(senderCard, acceptCard, amount);
    }
}
