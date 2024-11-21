package com.simpleproject.hospital_management.controller;

import com.simpleproject.hospital_management.model.Doctor;
import org.apache.catalina.LifecycleState;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/doctor/apis")
public class DoctorController {

    // here we use hashmap for database operations(CRUD)
    HashMap<Integer, Doctor> doctorHashMapDb = new HashMap<>();

    // 1->{1,ajay,23,ajay@gmail.com,cardio}
    // 2->{2,sanjay,23,sanjay@gmail.com,cardio}
    // 3->{3,vijay,23,vijay@gmail.com,cardio}


    // input to the spring boot is taken in the form of json -> Java object notation


    // @RequestBody -> takes the request or input from the ui/postman and assigns it to object
    @PostMapping("/save")
    public String saveDoctor(@RequestBody Doctor doctorRequest){
        // take the request and add it inside hashmap
        doctorHashMapDb.put(doctorRequest.getId(),doctorRequest);
        return "Doctor saved successfully";
    }

    @GetMapping("/findAll")
    public HashMap<Integer, Doctor> getAllDoctors(){
        return doctorHashMapDb;
    }

    // @PathVariable - it is the input sent in url path(endpoint)
    @GetMapping("/find/{docId}")
    public Doctor getDoctorById(@PathVariable("docId") int doctorId){
        Doctor doctor = doctorHashMapDb.get(doctorId);
        return doctor;
    }

}
