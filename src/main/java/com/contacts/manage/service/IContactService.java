package com.contacts.manage.service;

import com.contacts.manage.model.request.ContactItemRequest;
import com.contacts.manage.model.request.ShowContactRequest;
import com.contacts.manage.model.response.ContactResponse;
import feign.Response;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IContactService {

    ResponseEntity<List<String>> distinctNamesSavedByOthers(String jwt);

    void saveMyContactsList(String jwtToken, List<ContactItemRequest> contactList);

    ResponseEntity<List<ContactResponse>> getContactListByName(String jwt, String name);

    void makeContactVisible(String jwt,ShowContactRequest request);

}
