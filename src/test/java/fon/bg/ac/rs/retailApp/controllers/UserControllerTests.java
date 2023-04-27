package fon.bg.ac.rs.retailApp.controllers;

import fon.bg.ac.rs.retailApp.models.User;
import fon.bg.ac.rs.retailApp.servicesImpl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserControllerTests {

    @Mock
    private UserServiceImpl userServiceImpl;

    @Mock
    private Model model;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetUsersWhenNoUsersExist() {
        List<User> users = new ArrayList<>();
        when(userServiceImpl.getUsers()).thenReturn(users);

        String viewName = userController.getUsers(model);
        assertEquals("User", viewName);
        verify(model, times(1)).addAttribute(eq("users"), eq(null));
    }

    @Test
    void testGetUsersWhenUsersExist() {
        List<User> users = new ArrayList<>();
        User user = new User();
        user.setFirstname("Korisnik 1");
        user.setLastname("Korisnik 1");
        user.setUsername("Korisnicko ime 1");
        user.setPassword("Lozinka 1");
        user.setId(1);
        users.add(user);
        when(userServiceImpl.getUsers()).thenReturn(users);

        String viewName = userController.getUsers(model);
        assertEquals("User", viewName);
        verify(model, times(1)).addAttribute(eq("users"), eq(users));
    }

    @Test
    void testAddNew() {
        User user = new User();
        user.setFirstname("Korisnik 1");
        user.setLastname("Korisnik 1");
        user.setUsername("Korisnicko ime 1");
        user.setPassword("Lozinka 1");
        user.setId(1);
        when(userServiceImpl.saveUser(user)).thenReturn(user);

        RedirectView redirectView = userController.addNew(user);
        assertEquals("/login", redirectView.getUrl());
        verify(userServiceImpl, times(1)).saveUser(user);
    }

    @Test
    void testFindById() {
        int id = 1;
        User user = new User();
        user.setFirstname("Korisnik 1");
        user.setLastname("Korisnik 1");
        user.setUsername("Korisnicko ime 1");
        user.setPassword("Lozinka 1");
        user.setId(1);
        Optional<User> optionalUser = Optional.of(user);
        when(userServiceImpl.findById(id)).thenReturn(optionalUser);

        Optional<User> result = userController.findById(id);
        assertEquals(optionalUser, result);
        verify(userServiceImpl, times(2)).findById(id);
    }

    @Test
    void testUpdate() {
        User user = new User();
        user.setFirstname("Korisnik 1");
        user.setLastname("Korisnik 1");
        user.setUsername("Korisnicko ime 1");
        user.setPassword("Lozinka 1");
        user.setId(1);
        when(userServiceImpl.saveUser(user)).thenReturn(user);

        String viewName = userController.update(user);
        assertEquals("redirect:/users", viewName);
        verify(userServiceImpl, times(1)).saveUser(user);
    }

    @Test
    void testDeleteById() {
        int id = 1;

        String viewName = userController.deleteById(id);
        assertEquals("redirect:/users", viewName);
        verify(userServiceImpl, times(1)).deleteById(id);
    }
}