package com.dw.driverapp.repository;

import com.dw.driverapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByEmail(String email);
    Optional<List<User>> findByBirthdate(LocalDate birthdate);
    Optional<List<User>> findByRealName (String realName);
    Optional<List<User>> findByAuthority_AuthorityName(String authorityName);

    @Query("select u from User u where u.createdAt > :date")
    Optional<List<User>> createdAtoverdate(LocalDate date);
    @Query("select u from User u where u.createdAt < :date")
    Optional<List<User>> createdAtunderdate(LocalDate date);
    Optional<List<User>> findBycreatedAt (LocalDate date);
    @Query("select u from User u where  u.createdAt BETWEEN :date1 AND :date2")
    Optional<List<User>> createdAtbetweendate (LocalDate date1, LocalDate date2);

}
