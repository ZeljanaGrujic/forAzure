package fon.bg.ac.rs.retailApp.servicesImpl;

import fon.bg.ac.rs.retailApp.models.User;
import fon.bg.ac.rs.retailApp.models.UserPrincipal;
import fon.bg.ac.rs.retailApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findByUsername(username);
        //this user is user created from my model package
        //in spring security we are returning UserPrincipal
        //so we are going to build principal user with this our user
        if(user==null){
            throw new UsernameNotFoundException("Korisnik nije pronadjen");
        }

        return new UserPrincipal(user);
    }
}
