package com.contacts.manage.controller;

import com.contacts.manage.model.request.ContactItemRequest;
import com.contacts.manage.model.request.ShowContactRequest;
import com.contacts.manage.model.response.ContactResponse;
import com.contacts.manage.service.IContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ContactController {

    private final IContactService iContactService;

    @GetMapping("/my-names-saved")
    public ResponseEntity<List<String>> savedMyNameByOthers(
            @RequestHeader(name = "Authorization") String token
    ) {
        return iContactService.distinctNamesSavedByOthers(token);
    }

    @PostMapping("/save-contacts")
    public void saveMyContacts(
            @RequestHeader(name = "Authorization") String token,
            @RequestBody List<ContactItemRequest> contactList
    ) {
        iContactService.saveMyContactsList(token, contactList);
    }

    @GetMapping("/contacts-by-name/{name}")
    public ResponseEntity<List<ContactResponse>> getContactListByName(
            @RequestHeader(name = "Authorization") String jwt,
            @PathVariable("name") String name
    ) {
        return iContactService.getContactListByName(jwt, name);
    }

    @PostMapping("/show-contact-name")
    public void makeContactVisible(
            @RequestHeader(name = "Authorization") String jwt,
            @RequestBody ShowContactRequest showRequest) {
        iContactService.makeContactVisible(jwt, showRequest);
    }

}
