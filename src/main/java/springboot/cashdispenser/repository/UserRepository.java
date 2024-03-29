package springboot.cashdispenser.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import springboot.cashdispenser.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT DISTINCT u FROM User u "
            + "LEFT JOIN FETCH u.roles r "
            + "WHERE u.username=:username")
    Optional<User> findByUsername(String username);
}
