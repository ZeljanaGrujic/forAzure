package fon.bg.ac.rs.retailApp.security.services;

import fon.bg.ac.rs.retailApp.security.models.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    List<Role> getRoles();

    Role saveRole(Role role);

    Optional<Role> findById(int id);

    void deleteById(int id);
//    public void assignUserRole(Integer userId, Integer roleId);
//    public void unassignUserRole(Integer userId, Integer roleId);
//
//    public Set<Role> getUserRoles(User user);
//
//    public Set<Role> getUserNotRoles(User user);
}
