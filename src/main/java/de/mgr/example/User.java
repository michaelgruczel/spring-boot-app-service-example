package de.mgr.example;

public class User {

    private String mail;
    private String password;
    private String lastName;
    private String name;
    private String address;

    public User() {
        // default constructor for json construction
    }

    public User(String mail, String password, String name, String lastName, String address) {
        this.mail = mail;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.address = address;

    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
