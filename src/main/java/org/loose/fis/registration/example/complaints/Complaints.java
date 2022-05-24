package org.loose.fis.registration.example.complaints;

public class Complaints {
    private String message;

    public String getMessage() {
        return message;
    }
    public void setMessage( String message){
        this.message = message;
    }

    public Complaints(String message) {
        this.message = message;

    }
    public Complaints(){}


    @Override
    public String toString() {
        return "UserDTO{" +
                "message='" + message + '\'' +
                '}';

    }


}