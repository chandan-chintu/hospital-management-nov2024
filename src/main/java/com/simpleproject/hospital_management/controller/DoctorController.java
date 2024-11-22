package com.simpleproject.hospital_management.controller;

import com.simpleproject.hospital_management.model.Doctor;
import org.apache.catalina.LifecycleState;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    // @RequestParam - takes the input in the form of a request paramaeter as a query
    @GetMapping("/findByName")
    public Doctor getDoctorByName(@RequestParam("doctorname") String doctorName){
        for (Doctor doctor : doctorHashMapDb.values()){
            if(doctor.getName().equals(doctorName)){
                return doctor;
            }
        }
        return null;
    }

    @GetMapping("/findBySpecialization")
    public List<Doctor> getDoctorBySpecialization(@RequestParam String specialization){
        List<Doctor> doctorList = new ArrayList<>();
        for (Doctor doctor : doctorHashMapDb.values()){
            if(doctor.getSpecialization().equalsIgnoreCase(specialization)){
                doctorList.add(doctor);
            }
        }
        return doctorList;
    }

    @GetMapping("/findByAgeAndSpecialization")
    public List<Doctor> getDoctorByAgeAndSpecialization(@RequestParam int age,@RequestParam String specialization){
        List<Doctor> doctorList = new ArrayList<>();
        for(Doctor doctor : doctorHashMapDb.values()){
            if(doctor.getAge()==age && doctor.getSpecialization().equals(specialization)){
                doctorList.add(doctor);
            }
        }
        return doctorList;
    }

    @GetMapping("/findByAgeOrSpecialization")
    public List<Doctor> getDoctorByAgeOrSpecialization(@RequestParam int age,@RequestParam String specialization){
        List<Doctor> doctorList = new ArrayList<>();
        for(Doctor doctor : doctorHashMapDb.values()){
            if(doctor.getAge()==age || doctor.getSpecialization().equals(specialization)){
                doctorList.add(doctor);
            }
        }
        return doctorList;
    }

    @GetMapping("/findByAgeOrSpecializationOptional")
        public List<Doctor> getDoctorByAgeOrSpecializationOptional(@RequestParam(value = "age", required = false) Integer age,@RequestParam(required = false) String specialization){
        List<Doctor> doctorList = new ArrayList<>();
        for(Doctor doctor : doctorHashMapDb.values()){
            if(age!=null && doctor.getAge() == age) {
                doctorList.add(doctor);
            } else if(specialization!=null && doctor.getSpecialization().equals(specialization)){
                doctorList.add(doctor);
            }
        }
        return doctorList;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteDoctorById(@PathVariable int id){
        doctorHashMapDb.remove(id);
        return "Doctor with id :"+id+" is deleted successfully";
    }

    @PutMapping("/update/{id}")
    public String updateDoctor(@PathVariable int id, @RequestBody Doctor doctorRequest){
        // check if doctor id is present
        // if present update
        // else not
        Doctor doctor = doctorHashMapDb.get(id);
        if(doctor!=null){
            doctorHashMapDb.put(id,doctorRequest);
            return "Doctor updated successfully";
        } else{
            return "Dcotor not found with given id : "+id;
        }
    }

}
