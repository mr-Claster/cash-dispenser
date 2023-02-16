package springboot.cashdispenser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.cashdispenser.model.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
