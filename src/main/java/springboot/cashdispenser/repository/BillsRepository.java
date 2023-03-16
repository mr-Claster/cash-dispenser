package springboot.cashdispenser.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import springboot.cashdispenser.model.Bills;

public interface BillsRepository extends JpaRepository<Bills, Long> {
    @Query("SELECT b FROM Bills b "
            + "JOIN FETCH Par p "
            + "ORDER BY p.value DESC")
    List<Bills> findAll();
}
