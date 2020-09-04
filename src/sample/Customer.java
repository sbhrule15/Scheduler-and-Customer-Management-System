package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Customer {

    private Integer customerId;
    private String customerName;
    private Integer addressId;
    private String createDate;
    private String createdBy;
    private String phoneNumber;
    private String addressLine1;
    private String addressLine2;
    private Integer cityId;
    private Integer postalCode;
    private StringProperty name = new SimpleStringProperty();

    public Customer(Integer customerId, String customerName, Integer addressId, String createDate, String createdBy, String phoneNumber, String addressLine1, String addressLine2, Integer cityId, Integer postalCode) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.addressId = addressId;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.phoneNumber = phoneNumber;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.cityId = cityId;
        this.postalCode = postalCode;
        this.name.setValue(customerName);
    }

    public Customer(Integer customerId, String customerName) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.name.setValue(customerName);
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public String getCreateDate() {
        return createDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public Integer getCityId() {
        return cityId;
    }

    public Integer getPostalCode() {
        return postalCode;
    }

    public StringProperty nameProperty() {
        return name ;
    }




}
