package com.expressionbesoins.restexpbesoin.repository;
/**
 * @autor abdelhadi mouzafir
 */
import com.expressionbesoins.restexpbesoin.model.Role;
import com.expressionbesoins.restexpbesoin.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {

    int deleteUserByUsername(String username);
    User findUserByUsername(String username);
    User findUserByEmail(String email);

}
