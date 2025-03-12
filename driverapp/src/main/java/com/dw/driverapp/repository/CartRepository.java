package com.dw.driverapp.repository;

import com.dw.driverapp.model.Cart;
import com.dw.driverapp.model.Subject;
import com.dw.driverapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,Long> {
    Optional<List<Cart>> findByUserUserName(String username);
    Optional<Cart> findByUserUserNameAndSubjectId(String username, Long subjectId);
    List<Cart> findByUser_UserNameAndSubject_Id(String username,Long subjectId);
    void delete(Cart cart);
    List<Cart> findByUser_UserName(String username);
   List<Cart> findByUser_UserNameAndIdIn(String username,List<Long> cartIds);
List<Cart> findByUserUserNameAndIdIn (String username,List<Long> cartIds);

}
