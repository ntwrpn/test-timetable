package com.java.payload;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class RegistrationRequest {
    @NotBlank
    @Size(min=3, max = 60)
    private String username;
 
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
    
    @NotBlank
    @Size(min = 3, max = 16)
    private String name;
    
    @NotBlank
    @Size(min = 3, max = 16)
    private String surname;
    
    @NotBlank
    @Size(min = 3, max = 16)
    private String patronymic;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }


    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }
    

    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
}