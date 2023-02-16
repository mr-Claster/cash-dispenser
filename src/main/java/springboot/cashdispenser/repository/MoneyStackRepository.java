package springboot.cashdispenser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import springboot.cashdispenser.model.MoneyStack;
import java.util.List;

public interface MoneyStackRepository extends JpaRepository<MoneyStack, Long> {
    @Query("SELECT c "
            + "FROM MoneyStack c "
            + "ORDER BY c.par DESC")
    List<MoneyStack> findAll();
}
