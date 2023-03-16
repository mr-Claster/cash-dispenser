package springboot.cashdispenser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.cashdispenser.model.Par;

public interface ParRepository extends JpaRepository<Par, Long> {
    Par findParByValue(Integer value);
}
