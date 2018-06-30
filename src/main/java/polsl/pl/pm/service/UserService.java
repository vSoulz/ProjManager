package polsl.pl.pm.service;

import polsl.pl.pm.model.User;
import polsl.pl.pm.model.UserDto;

import java.util.List;

public interface UserService {

    User save(UserDto user);
    List<User> findAll();
    void delete(long id);
    User findOne(String username);

    User findById(Long id);
}
