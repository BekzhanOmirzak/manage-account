package com.contacts.manage.model.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "contacts")
public class ContactItem {
    @Id
    @Column(name = "user_contact")
    private String userContact;
    @Column(name = "saved_name")
    private String savedName;
    @Column(name = "is_hidden")
    private boolean isHidden;
}
