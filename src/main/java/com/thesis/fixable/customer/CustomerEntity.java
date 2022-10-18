package com.thesis.fixable.customer;

import com.thesis.fixable.auth.user.UserEntity;

import javax.persistence.*;
import java.util.Objects;
//TODO replace one to one composition relation to inhretance so there is only one is for the same user and customer
@Entity
@Table(name = "customers")
public class CustomerEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String LastName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(nullable = false)
    private String phoneNumber;

    @Lob
    @Column(nullable = false)
    private String avatar;

    public CustomerEntity() {
    }

    public CustomerEntity(String firstName, String lastName, UserEntity user, String phoneNumber, String avatar) {
        this.firstName = firstName;
        LastName = lastName;
        this.user = user;
        this.phoneNumber = phoneNumber;
        this.avatar = avatar;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerEntity)) return false;
        CustomerEntity that = (CustomerEntity) o;
        return Objects.equals(getFirstName(), that.getFirstName()) && Objects.equals(getLastName(), that.getLastName()) && Objects.equals(getUser(), that.getUser()) && Objects.equals(getPhoneNumber(), that.getPhoneNumber()) && Objects.equals(getAvatar(), that.getAvatar());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getLastName(), getUser(), getPhoneNumber(), getAvatar());
    }

    @Override
    public String toString() {
        return "CustomerEntity{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", user=" + user +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
