package springboot.cashdispenser.service;

import springboot.cashdispenser.model.User;

public interface UserService {
    User save(User user);

    User update(User user);

    User getByUsername(String username);
}
