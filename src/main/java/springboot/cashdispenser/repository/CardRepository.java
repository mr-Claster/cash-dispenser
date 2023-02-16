package springboot.cashdispenser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.cashdispenser.model.Card;
import java.util.Optional;

public interface CardRepository extends JpaRepository<Card, Long> {
    Optional<Card> findByCardNumber(String cardNumber);
}
