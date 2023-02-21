package springboot.cashdispenser.service.impl;

import jakarta.annotation.PostConstruct;
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

    @PostConstruct
    public void init() {
        Role admin = new Role();
        admin.setRoleName("ADMIN");
        Role user = new Role();
        user.setRoleName("USER");
        save(admin);
        save(user);
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
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
