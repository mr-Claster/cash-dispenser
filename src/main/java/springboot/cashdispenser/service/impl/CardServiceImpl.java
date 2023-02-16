package springboot.cashdispenser.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import springboot.cashdispenser.model.Card;
import springboot.cashdispenser.repository.CardRepository;
import springboot.cashdispenser.service.CardService;
import java.math.BigDecimal;

@Service
public class CardServiceImpl implements CardService {
    private final CardRepository cardRepository;
    private final PasswordEncoder passwordEncoder;

    public CardServiceImpl(CardRepository cardRepository,
                           PasswordEncoder passwordEncoder) {
        this.cardRepository = cardRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Card save(Card card) {
        card.setPIN(
                passwordEncoder.encode(card.getPIN()));
        return cardRepository.saveAndFlush(card);
    }

    @Override
    public Card update(Card card) {
        card.setPIN(
                passwordEncoder.encode(card.getPIN()));
        return cardRepository.saveAndFlush(card);
    }

    @Override
    public void checkCard(Card card) {
        Card byCardNumber = getByCardNumber(card.getCardNumber());
        if (!passwordEncoder.matches(card.getPIN(), byCardNumber.getPIN())) {
            throw new RuntimeException("incorrect PIN");
        }
        card.setPIN(byCardNumber.getPIN());
        card.setId(byCardNumber.getId());
        if (!card.equals(byCardNumber)) {
            throw new RuntimeException("incorrect card data");
        }
    }

    @Override
    public void checkAmountOnCard(Card card, BigDecimal amount) {
        if (card.getAmount().compareTo(amount) < 0) {
            throw new RuntimeException("insufficient funds on the card");
        }
    }

    @Override
    public Card getByCardNumber(String cardNumber) {
        return cardRepository.findByCardNumber(cardNumber)
                .orElseThrow(() -> new RuntimeException("can't find card by number: " + cardNumber));
    }
}
