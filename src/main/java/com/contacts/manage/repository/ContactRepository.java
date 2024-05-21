package com.contacts.manage.repository;

import com.contacts.manage.model.entity.ContactItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<ContactItem, String> {

}
