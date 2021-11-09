package com.example.msaproj;

public class Users {
    //private String id;
    public String first_name;
    public String last_name;
    public String hash_pass;
    public String email;

    public Users() {
    }
    public Users(String first_name, String last_name,  String hash_pass, String email ) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.hash_pass = hash_pass;
        this.email = email;
    }

}
