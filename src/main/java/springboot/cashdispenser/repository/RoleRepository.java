package springboot.cashdispenser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.cashdispenser.model.Role;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findById(Long id);

    Optional<Role> findByRoleName(String roleName);
}
