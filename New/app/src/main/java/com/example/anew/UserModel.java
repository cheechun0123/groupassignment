package com.example.anew;

public class UserModel {

    private long id; // Unique user ID
    private String name;
    private String password;
    private String phoneNumber;
    private String carPlate;
    private String email;

    // Constructor with ID (useful for retrieving users from the database)
    public UserModel(long id, String name, String phoneNumber, String carPlate, String email, String password) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.carPlate = carPlate;
        this.email = email;
        this.password = password;
    }

    // Getters
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getCarPlate() {
        return carPlate;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    // Setters
    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setCarPlate(String carPlate) {
        this.carPlate = carPlate;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
