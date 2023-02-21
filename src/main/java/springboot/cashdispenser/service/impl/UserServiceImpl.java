package springboot.cashdispenser.service.impl;

import jakarta.annotation.PostConstruct;
import java.util.List;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import springboot.cashdispenser.model.User;
import springboot.cashdispenser.repository.UserRepository;
import springboot.cashdispenser.service.CardService;
import springboot.cashdispenser.service.RoleService;
import springboot.cashdispenser.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final CardService cardService;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           RoleService roleService,
                           CardService cardService) {
      this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
        this.cardService = cardService;
    }

    @PostConstruct
    public void init() {
        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setRoles(List.of(roleService.getByName("ADMIN")));
        //admin.setCards(List.of(cardService.getByCardNumber("1")));
        save(admin);
    }

    @Override
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.saveAndFlush(user);
    }

    @Override
    public User update(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "cant find user by username: " + username));
    }
}
