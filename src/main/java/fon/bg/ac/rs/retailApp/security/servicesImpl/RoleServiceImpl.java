package fon.bg.ac.rs.retailApp.security.servicesImpl;


import fon.bg.ac.rs.retailApp.models.User;
import fon.bg.ac.rs.retailApp.repositories.UserRepository;
import fon.bg.ac.rs.retailApp.security.models.Role;
import fon.bg.ac.rs.retailApp.security.repositories.RoleRepository;
import fon.bg.ac.rs.retailApp.security.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role saveRole(Role myRole) {
        return roleRepository.save(myRole);
    }

    @Override
    public Optional<Role> findById(int id) {
        return roleRepository.findById(id);
    }

    @Override
    public void deleteById(int id) {
        roleRepository.deleteById(id);
    }

    public void assignUserRole(Integer userId, Integer roleId){

        User user=  userRepository.findById(userId).orElse(null);
        Role role= roleRepository.findById(roleId).orElse(null);

        Set<Role> userRoles=user.getRoles();
        userRoles.add(role);

        user.setRoles(userRoles);
        userRepository.save(user);
    }

    public void unassignUserRole(Integer userId, Integer roleId){
        User user=  userRepository.findById(userId).orElse(null);
        Set<Role> userRoles=user.getRoles();
        userRoles.removeIf(x -> x.getId()==roleId);

        user.setRoles(userRoles);
        userRepository.save(user);

    }

    public Set<Role> getUserRoles(User user){
        return user.getRoles();
    }

    public Set<Role> getUserNotRoles(User user){
        return roleRepository.getUserNotRoles(user.getId());
    }
}
