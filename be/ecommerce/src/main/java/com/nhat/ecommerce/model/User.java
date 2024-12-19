package com.nhat.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;



import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@SuppressWarnings("JpaAttributeTypeInspection")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String firstname;
    private String lastname;
    private String password;
    private String email;
    private String role;
    private String mobile;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Address> address = new ArrayList<>();

//    @Embedded
    @ElementCollection
    @CollectionTable(name = "payment_information", joinColumns = @JoinColumn(name = "user_id"))
    private List<PaymentInformation> paymentInformation = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Rating> ratings = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

    private LocalDateTime createAt;

    public User(){

    }

    public User(long id, String firstname, String lastname, String password, String email, String role, List<Address> address, String mobile, List<PaymentInformation> paymentInformation, List<Rating> ratings, List<Review> reviews, LocalDateTime createAt) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.email = email;
        this.role = role;
        this.address = address;
        this.mobile = mobile;
        this.paymentInformation = paymentInformation;
        this.ratings = ratings;
        this.reviews = reviews;
        this.createAt = createAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String   getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }

    public List<PaymentInformation> getPaymentInformation() {
        return paymentInformation;
    }

    public void setPaymentInformation(List<PaymentInformation> paymentInformation) {
        this.paymentInformation = paymentInformation;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }
}
