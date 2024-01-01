package com.jubayer.socialmedia;

public class UserModel {
    String uid, name, userName, Email, phoneNumber, Password, image;

    public UserModel() {
    }

    public UserModel(String uid,String name, String userName, String userEmail, String phoneNumber, String password, String image) {
        this.uid = uid;
        this.name = name;
        this.userName = userName;
        this.Email = userEmail;
        this.phoneNumber = phoneNumber;
        this.Password = password;
        this.image = image;
    }

    public String getuid() {
        return uid;
    }

    public void setuid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return Email;
    }

    public void setUserEmail(String Email) {
        this.Email = Email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getimage() {
        return image;
    }

    public void setImage(String image) {
        Password = image;
    }
}
