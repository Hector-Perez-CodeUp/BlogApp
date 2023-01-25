package com.codeup.spring.repositories;
// Imports
import com.codeup.spring.models.User;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //Utilized in UserDetailsLoader.java
    User findByUsername(String username);

}
