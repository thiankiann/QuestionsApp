package com.stormit.springDataManipulation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select count(u) = 0 from User u " +
            "where u.id <> :#{#user.id} and u.username <> :#{#user.username}")
    boolean isUsernameUnique(User user);
}