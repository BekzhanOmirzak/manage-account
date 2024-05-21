package com.contacts.manage.repository;

import com.contacts.manage.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByUserid(String userid);

    Optional<User> findByPhonenumber(String phonenumber);

}
