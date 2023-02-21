package springboot.cashdispenser.service;

import springboot.cashdispenser.model.Role;

public interface RoleService {
    Role save(Role role);

    Role getById(Long id);

    Role getByName(String role);
}
