package fon.bg.ac.rs.retailApp.services;

import fon.bg.ac.rs.retailApp.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User saveUser(User user);

    User findByUsername(String username);

    User findByFirstnameAndLastname(String firstname, String lastname);

    Optional<User> findById(int id);

    void deleteById(int id);

    List<User> getUsers();
}
