package com.example.nehabeautysalon;

public class UserHelperClass {

    String firstname, lastname, contactno, email, password;


    public UserHelperClass() {
    }

    public UserHelperClass(String firstname, String lastname, String contactno, String email, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.contactno = contactno;
        this.email = email;
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getContactno() {
        return contactno;
    }

    public void setContactno(String contactno) {
        this.contactno = contactno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
