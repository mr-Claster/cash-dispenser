package springboot.cashdispenser.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import springboot.cashdispenser.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findById(Long id);

    Optional<Role> findByRoleName(String roleName);
}
