package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;
import java.time.ZonedDateTime;

public class Appointment {

    private Integer appointmentId;
    private Integer customerID;
    private Integer userID;
    private String title;
    private String description;
    private String location;
    private String contact;
    private String type;
    private ZonedDateTime start;
    private Integer duration;
    private StringProperty appointmentTitle = new SimpleStringProperty();

    public Appointment(Integer appointmentId, Integer customerID, Integer userID, String title, String description, String location, String contact, String type, ZonedDateTime start, Integer duration) {
        this.appointmentId = appointmentId;
        this.customerID = customerID;
        this.userID = userID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.contact = contact;
        this.type = type;
        this.start = start;
        this.duration = duration;
        this.appointmentTitle.setValue(title);
    }

    public Integer getAppointmentId() {
        return appointmentId;
    }

    public Integer getCustomerID() {
        return customerID;
    }

    public Integer getUserID() {
        return userID;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public String getContact() {
        return contact;
    }

    public String getType() {
        return type;
    }

    public Integer getDuration() {
        return duration;
    }

    public LocalDate getDate(){
        return start.toLocalDate();
    }

    public Integer getHour(){
        Integer h = start.getHour();

        if (h == 0){
            return 12;
        } else if (h > 12){
            return h-12;
        } else {
            return h;
        }
    }

    public Integer getMinute(){
        return start.getMinute();
    }

    public String getAMPM(){
        Integer h = start.getHour();

        if (h < 12) {
            return "AM";
        } else {
            return "PM";
        }
    }

    public StringProperty getAppointmentTitle(){
        return appointmentTitle;
    }

}
