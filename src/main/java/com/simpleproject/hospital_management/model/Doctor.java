package com.simpleproject.hospital_management.model;

import lombok.Data;

@Data // it will add getter and setter internally, we do not need to add it manually.
public class Doctor {

    private int id;
    private String name;
    private int age;
    private String email;
    private String specialization;
}
