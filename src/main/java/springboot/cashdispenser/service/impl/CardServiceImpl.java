package springboot.cashdispenser.service.impl;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import springboot.cashdispenser.model.Card;
import springboot.cashdispenser.repository.CardRepository;
import springboot.cashdispenser.service.CardService;

@Service
public class CardServiceImpl implements CardService {
    private final CardRepository cardRepository;
    private final PasswordEncoder passwordEncoder;

    public CardServiceImpl(CardRepository cardRepository,
                           PasswordEncoder passwordEncoder) {
        this.cardRepository = cardRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        Card card = new Card();
        card.setAmount(BigDecimal.valueOf(5000));
        card.setCardNumber("1");
        card.setExpirationDate(LocalDate.MAX);
        card.setCvv("111");
        card.setPin("1111");
        save(card);
    }

    @Override
    @Transactional
    public void transferMoneyToAnotherCard(Card senderCard,
                                           String numberOfAcceptCard,
                                           BigDecimal amount) {
        checkCard(senderCard);
        checkAmountOnCard(senderCard, amount);
        senderCard.setAmount(senderCard.getAmount().subtract(amount));
        Card acceptCard = getByCardNumber(numberOfAcceptCard);
        acceptCard.setAmount(acceptCard.getAmount().add(amount));
        update(senderCard);
        update(acceptCard);
    }

    @Override
    public Card save(Card card) {
        card.setPin(
                passwordEncoder.encode(card.getPin()));
        return cardRepository.saveAndFlush(card);
    }

    @Override
    public Card update(Card card) {
        card.setPin(
                passwordEncoder.encode(card.getPin()));
        return cardRepository.saveAndFlush(card);
    }

    @Override
    public void checkCard(Card card) {
        Card byCardNumber = getByCardNumber(card.getCardNumber());
        if (!passwordEncoder.matches(card.getPin(), byCardNumber.getPin())) {
            throw new RuntimeException("incorrect PIN");
        }
        card.setPin(byCardNumber.getPin());
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
                .orElseThrow(() -> new RuntimeException("can't find card by number: "
                        + cardNumber));
    }
}
