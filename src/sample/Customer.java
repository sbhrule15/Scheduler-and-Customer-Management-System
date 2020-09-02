package sample;

public class Customer {

    private Integer customerId;
    private String customerName;
    private Integer addressId;
    private String createDate;
    private String createdBy;
    private String email;
    private String phoneNumber;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String postalCode;

    public Customer(Integer customerId, String customerName, Integer addressId, String createDate, String createdBy, String email, String phoneNumber, String addressLine1, String addressLine2, String city, String postalCode) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.addressId = addressId;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.postalCode = postalCode;
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

    public String getEmail() {
        return email;
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

    public String getCity() {
        return city;
    }

    public String getPostalCode() {
        return postalCode;
    }




}
