package com.thesis.fixable.technician;

public class TechnicianResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String avatar;
    private Profession profession;
    private String country;
    private String region;

    public TechnicianResponse() {
    }

    public TechnicianResponse(Long id, String firstName, String lastName, String phoneNumber, String email, String avatar, Profession profession, String country, String region) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.avatar = avatar;
        this.profession = profession;
        this.country = country;
        this.region = region;
    }

    public static TechnicianResponse fromTechnicianEntity(TechnicianEntity entity) {
        return new TechnicianResponse(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getPhoneNumber(),
                entity.getUser().getEmail(),
                entity.getAvatar(),
                entity.getProfession(),
                entity.getCountry(),
                entity.getRegionName()
        );
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
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}