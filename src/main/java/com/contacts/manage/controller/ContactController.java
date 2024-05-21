package com.contacts.manage.controller;

import com.contacts.manage.model.request.ContactItemRequest;
import com.contacts.manage.service.IContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ContactController {

    private final IContactService iContactService;

    @GetMapping("/names/saved")
    public ResponseEntity<List<String>> savedMyNameByOthers(
            @RequestHeader(name = "Authorization") String token
    ) {
        return iContactService.retrieveMyNamesSavedList(token);
    }

    @PostMapping("/savemycontacts")
    public ResponseEntity<Void> savedMyContacts(
            @RequestHeader(name = "Authorization") String jwtToken,
            @RequestBody List<ContactItemRequest> contactList
    ) {
        return iContactService.saveMyContactsList(jwtToken, contactList);
    }
}
