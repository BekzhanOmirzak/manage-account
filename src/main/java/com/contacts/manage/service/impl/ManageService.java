package com.contacts.manage.service.impl;

import com.contacts.manage.model.request.ContactItemRequest;
import com.contacts.manage.repository.ContactRepository;
import com.contacts.manage.security.JwtTokenUtill;
import com.contacts.manage.service.IContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ManageService implements IContactService {

    private final JwtTokenUtill jwtTokenUtill;
    private final ContactRepository contactRepository;

    @Override
    public ResponseEntity<Void> saveMyContactsList(String jwtToken, List<ContactItemRequest> contactList) {
        var phoneNumber = jwtTokenUtill.getPhoneNumber(jwtToken);
        return null;
    }

    @Override
    public ResponseEntity<List<String>> retrieveMyNamesSavedList(String jwt) {
        return null;
    }


}
