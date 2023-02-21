package springboot.cashdispenser.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import springboot.cashdispenser.model.Bills;

public interface BillsRepository extends JpaRepository<Bills, Long> {
    @Query("SELECT b FROM Bills b "
            + "JOIN Par p "
            + "ORDER BY p.par DESC")
    List<Bills> findAll();
}
