package springboot.cashdispenser.service.impl;

import org.springframework.stereotype.Service;
import springboot.cashdispenser.model.Role;
import springboot.cashdispenser.repository.RoleRepository;
import springboot.cashdispenser.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Can't find role by id: " + id));
    }

    @Override
    public Role getByName(String role) {
        return roleRepository.findByRoleName(role)
                .orElseThrow(() -> new RuntimeException("Can't find role by role name: " + role));
    }
}
