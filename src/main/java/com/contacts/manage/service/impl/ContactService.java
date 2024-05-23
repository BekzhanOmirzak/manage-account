package com.contacts.manage.service.impl;

import com.contacts.manage.model.entity.ContactItem;
import com.contacts.manage.model.request.ContactItemRequest;
import com.contacts.manage.model.request.ShowContactRequest;
import com.contacts.manage.model.response.ContactResponse;
import com.contacts.manage.repository.ContactRepository;
import com.contacts.manage.repository.UserRepository;
import com.contacts.manage.security.JwtTokenUtill;
import com.contacts.manage.service.IContactService;
import com.contacts.manage.util.GlobalConst;
import com.contacts.manage.util.PhoneNumberUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactService implements IContactService {

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
        var user_id = jwtTokenUtill.getUserId(jwt.substring(7));
        var contactList = contactRepository.getContactsBySavedNameUserId(name, user_id).stream()
                .map((contactItem) -> new ContactResponse(contactItem.getSavedName(), contactItem.getPhoneNumber(), contactItem.isHidden())).toList();
        return ResponseEntity.ok(contactList);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public void makeContactVisible(String jwt, ShowContactRequest request) {
        var userId = jwtTokenUtill.getUserId(jwt.substring(7));
        var user = userRepository.findByUserid(userId).orElseThrow(() -> new UsernameNotFoundException(("User is not found by user_id")));
        if (user.getScores() < GlobalConst.REQUIRED_SCORE) {
            throw new RuntimeException("You don't have enough score to make contact visible");
        }
        user.setScores(user.getScores() - GlobalConst.REQUIRED_SCORE);
        var trimPhoneNumber = phoneNumberUtil.trimPhoneNumber(request.phoneNumber());
        contactRepository.updateContactIsHiddenByUserId(false, userId, trimPhoneNumber);
    }

}
