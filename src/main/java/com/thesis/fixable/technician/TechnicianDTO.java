package com.thesis.fixable.technician;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.validation.constraints.*;

public final class TechnicianDTO {

    @NotNull @NotEmpty@Email
    private String email;

    @NotNull @NotEmpty @Size(min = 8)
    private String password;

    @NotNull @NotEmpty
    private String firstName;

    @NotNull @NotEmpty
    private String LastName;

    @NotNull @NotEmpty //TODO add a pattern of the image rul to validate against
    private String avatar;

    @NotNull @NotEmpty @Pattern(regexp = "^\\+(?:[0-9] ?){6,14}[0-9]$")
    private String phoneNumber;

    @NotNull @Max(90) @Min(-90)
    private Double latitude;

    @NotNull @Max(180) @Min(-180)
    private Double longitude;

    @NotNull @NotEmpty
    private String country;

    @NotNull @NotEmpty
    private String region;

    @NotNull
    private Profession profession;

    public TechnicianDTO() {
    }

    public TechnicianDTO(String email, String password, String firstName, String lastName, String avatar, String phoneNumber, Double latitude, Double longitude, String country, String region, Profession profession) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        LastName = lastName;
        this.avatar = avatar;
        this.phoneNumber = phoneNumber;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.region = region;
        this.profession = profession;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
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

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TechnicianDTO)) return false;

        TechnicianDTO that = (TechnicianDTO) o;

        if (getEmail() != null ? !getEmail().equals(that.getEmail()) : that.getEmail() != null) return false;
        if (getPassword() != null ? !getPassword().equals(that.getPassword()) : that.getPassword() != null)
            return false;
        if (getFirstName() != null ? !getFirstName().equals(that.getFirstName()) : that.getFirstName() != null)
            return false;
        if (getLastName() != null ? !getLastName().equals(that.getLastName()) : that.getLastName() != null)
            return false;
        if (getAvatar() != null ? !getAvatar().equals(that.getAvatar()) : that.getAvatar() != null) return false;
        if (getPhoneNumber() != null ? !getPhoneNumber().equals(that.getPhoneNumber()) : that.getPhoneNumber() != null)
            return false;
        if (getLatitude() != null ? !getLatitude().equals(that.getLatitude()) : that.getLatitude() != null)
            return false;
        if (getLongitude() != null ? !getLongitude().equals(that.getLongitude()) : that.getLongitude() != null)
            return false;
        if (getCountry() != null ? !getCountry().equals(that.getCountry()) : that.getCountry() != null) return false;
        if (getRegion() != null ? !getRegion().equals(that.getRegion()) : that.getRegion() != null) return false;
        return getProfession() == that.getProfession();
    }

    @Override
    public int hashCode() {
        int result = getEmail() != null ? getEmail().hashCode() : 0;
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        result = 31 * result + (getFirstName() != null ? getFirstName().hashCode() : 0);
        result = 31 * result + (getLastName() != null ? getLastName().hashCode() : 0);
        result = 31 * result + (getAvatar() != null ? getAvatar().hashCode() : 0);
        result = 31 * result + (getPhoneNumber() != null ? getPhoneNumber().hashCode() : 0);
        result = 31 * result + (getLatitude() != null ? getLatitude().hashCode() : 0);
        result = 31 * result + (getLongitude() != null ? getLongitude().hashCode() : 0);
        result = 31 * result + (getCountry() != null ? getCountry().hashCode() : 0);
        result = 31 * result + (getRegion() != null ? getRegion().hashCode() : 0);
        result = 31 * result + (getProfession() != null ? getProfession().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TechnicianDTO{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", avatar='" + avatar + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", country='" + country + '\'' +
                ", region='" + region + '\'' +
                ", profession=" + profession +
                '}';
    }
}
