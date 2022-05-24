package org.loose.fis.registration.example.model;

import org.loose.fis.registration.example.complaints.Complaints;

public class User {

    private String username;
    private String password;
    private String role;
    private String full_name;
    private String phone;
    public Complaints complaints;
    private int salary=0,hours=0;
    private String code=null;

    public User() {
    }
    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;

    }
    public User(String username, String password, String role,String full_name,String phone) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.full_name=full_name;
        this.phone=phone;

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFull_name(){return full_name;}

    public void setFull_name(String full_name){this.full_name=full_name;}

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getSalary(){return salary;}

    public void setSalary(int salary){this.salary=salary;}

    public int getHours(){return hours;}

    public void setHours(int hours){this.hours=hours;}

    public String getCode(){return code;}

    public void setCode(String code){this.code=code;}

    public Complaints getComplaints(){return complaints;}
    public void setComplaints(Complaints complaints){this.complaints=complaints;}


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!username.equals(user.username)) return false;
        if (!password.equals(user.password)) return false;
        return role.equals(user.role);
    }

    @Override
    public int hashCode() {
        int result = username.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + role.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", full name='" + full_name + '\'' +
                ", phone='" + phone + '\'' +
                ", salary='" + salary + '\'' +
                ", hours='" + hours + '\'' +
                ", complaints='" + complaints.toString() + '\'' +
                ", code='" + code + '\'' +
                '}';

    }
}
