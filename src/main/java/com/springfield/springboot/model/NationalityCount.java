package com.springfield.springboot.model;

public class NationalityCount {
    public String nationality;
    public Long count;
    public NationalityCount(String n, Long c){
        this.nationality = n;
        this.count =  c;
    }
}
