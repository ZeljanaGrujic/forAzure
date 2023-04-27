package fon.bg.ac.rs.retailApp.models;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

//import jakarta.persistence.*;
import javax.persistence.*;

import fon.bg.ac.rs.retailApp.security.models.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
@Data
//generisace nam getere i setere pomocu ove anotacije, lombok
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    private String firstname;
    private String lastname;
    private String username;
    private String password;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(name="user_role", joinColumns = {@JoinColumn(name = "user_id")},
    inverseJoinColumns = {@JoinColumn (name = "role_id")})
//            set ne dozvoljava ponavljanja, dok bih u listu mogla da ubacim isti element vise puta
            //user moze da ima vise rola, a rola moze biti dodeljena ka vise user-a
            //imacemo zajednicku tabelu user_role koja ce da sadrzi samo kombinaciju tipova
            //fetchovacemo sve role usera pre nego sto mu dozvolimo negde da pristupi zato ce biti eager fetc
            //kad obrisem roditelja cascadesType.All ce mu pobrisati svu decu, ali to necu tamo koristiti
    Set<Role> roles= new HashSet<>();
}

