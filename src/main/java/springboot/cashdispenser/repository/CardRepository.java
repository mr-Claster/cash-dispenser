package springboot.cashdispenser.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import springboot.cashdispenser.model.Card;

public interface CardRepository extends JpaRepository<Card, Long> {
    Optional<Card> findByCardNumber(String cardNumber);
}
