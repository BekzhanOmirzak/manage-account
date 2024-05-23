package com.contacts.manage.model.entity;


import jakarta.persistence.*;
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
    @Column(name = "phone_number")
    private String phoneNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
