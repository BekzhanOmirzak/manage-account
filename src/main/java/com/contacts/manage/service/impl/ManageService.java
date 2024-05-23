package com.contacts.manage.service.impl;

import com.contacts.manage.model.entity.ContactItem;
import com.contacts.manage.model.request.ContactItemRequest;
import com.contacts.manage.model.response.ContactResponse;
import com.contacts.manage.repository.ContactRepository;
import com.contacts.manage.repository.UserRepository;
import com.contacts.manage.security.JwtTokenUtill;
import com.contacts.manage.service.IContactService;
import com.contacts.manage.util.PhoneNumberUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ManageService implements IContactService {

    private final JwtTokenUtill jwtTokenUtill;
    private final ContactRepository contactRepository;
    private final PhoneNumberUtils phoneNumberUtil;
    private final UserRepository userRepository;

    @Override
    public void saveMyContactsList(String token, List<ContactItemRequest> contactList) {
        var phoneNumber = jwtTokenUtill.getPhoneNumber(token.substring(7));
        var userId = jwtTokenUtill.getUserId(token.substring(7));
        var user = userRepository.findByUserid(userId).orElseThrow(() -> new RuntimeException("User not found"));
        var phoneNumberTrim = phoneNumberUtil.trimPhoneNumber(phoneNumber);

        for (ContactItemRequest contactItemRequest : contactList) {
            var contactPhoneNumber = phoneNumberUtil.trimPhoneNumber(contactItemRequest.phoneNumber());
            var userContact = phoneNumberTrim + "_" + contactPhoneNumber;

            var newContactItem = ContactItem.builder()
                    .userContact(userContact)
                    .user(user)
                    .savedName(contactItemRequest.name())
                    .phoneNumber(contactPhoneNumber)
                    .isHidden(true)
                    .build();

            if (!contactRepository.existsById(newContactItem.getUserContact())) {
                contactRepository.save(newContactItem);
            }
        }
    }

    @Override
    public ResponseEntity<List<String>> distinctNamesSavedByOthers(String jwt) {
        var phoneNumber = jwtTokenUtill.getPhoneNumber(jwt.substring(7));
        var trimPhoneNumber = phoneNumberUtil.trimPhoneNumber(phoneNumber);
        return ResponseEntity.ok(contactRepository.getContactNamesSavedDistinctlyByPhoneNumber(trimPhoneNumber));
    }

    @Override
    public ResponseEntity<List<ContactResponse>> getContactListByName(String jwt, String name) {
        var phoneNumber = jwtTokenUtill.getPhoneNumber(jwt.substring(7));
        var trimPhoneNumber = phoneNumberUtil.trimPhoneNumber(phoneNumber);
        var contactList = contactRepository.getContactsBySavedName(name, trimPhoneNumber).stream()
                .map((contactItem) -> new ContactResponse(contactItem.getSavedName(), contactItem.getPhoneNumber(), contactItem.isHidden())).toList();
        return ResponseEntity.ok(contactList);
    }

}
