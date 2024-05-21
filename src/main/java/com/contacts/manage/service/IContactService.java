package com.contacts.manage.service;

import com.contacts.manage.model.request.ContactItemRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IContactService {

    ResponseEntity<List<String>> retrieveMyNamesSavedList(String jwt);

    ResponseEntity<Void> saveMyContactsList(String jwtToken, List<ContactItemRequest> contactList);

}
