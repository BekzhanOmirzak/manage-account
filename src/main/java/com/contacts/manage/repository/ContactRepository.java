package com.contacts.manage.repository;

import com.contacts.manage.model.entity.ContactItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<ContactItem, String> {

    @Query(value = "SELECT DISTINCT(saved_name) FROM contacts WHERE phone_number=:phoneNumber", nativeQuery = true)
    List<String> getContactNamesSavedDistinctlyByPhoneNumber(@Param("phoneNumber") String phoneNumber);

    @Query(value = "SELECT * FROM contacts WHERE saved_name=:name AND user_contact LIKE :phone_number%", nativeQuery = true)
    List<ContactItem> getContactsBySavedNamePhoneNumber(@Param("name") String name, @Param("phone_number") String phone_number);

    @Query(value = "SELECT * FROM contacts WHERE saved_name=:name AND user_id=:user_id", nativeQuery = true)
    List<ContactItem> getContactsBySavedNameUserId(@Param("name") String name, @Param("user_id") String user_id);

    @Query(value = "UPDATE contacts SET isHidden=:isHidden WHERE user_id=:user_id AND phone_number=:phone_number", nativeQuery = true)
    void updateContactIsHiddenByUserId(@Param("isHidden") boolean isHidden, @Param("user_id") String user_id, @Param("phone_number") String phone_number);

}
