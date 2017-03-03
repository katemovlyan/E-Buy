package ua.com.codefire.ecommerce.data.jpa_repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.com.codefire.ecommerce.data.entity.User;

/**
 * Created by ankys on 11.02.2017.
 */
@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    User findByUsername(String userName);
//
//    @Query("SELECT COUNT(user.id) FROM User user")
//    int getUserAmount();

}
