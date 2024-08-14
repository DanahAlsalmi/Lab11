package com.example.lab11.Repository;

import com.example.lab11.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    @Query("select u from User u where u.userId=?1")
    User findUserByUserId(Integer userId);

    User findUserByUsernameAndPassword(String username, String password);

    User findUserByEmail(String email);


}
