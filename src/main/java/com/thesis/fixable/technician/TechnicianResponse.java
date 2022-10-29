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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TechnicianResponse)) return false;

        TechnicianResponse response = (TechnicianResponse) o;

        if (getId() != null ? !getId().equals(response.getId()) : response.getId() != null) return false;
        if (getFirstName() != null ? !getFirstName().equals(response.getFirstName()) : response.getFirstName() != null)
            return false;
        if (getLastName() != null ? !getLastName().equals(response.getLastName()) : response.getLastName() != null)
            return false;
        if (getPhoneNumber() != null ? !getPhoneNumber().equals(response.getPhoneNumber()) : response.getPhoneNumber() != null)
            return false;
        if (getEmail() != null ? !getEmail().equals(response.getEmail()) : response.getEmail() != null) return false;
        if (getAvatar() != null ? !getAvatar().equals(response.getAvatar()) : response.getAvatar() != null)
            return false;
        if (getProfession() != response.getProfession()) return false;
        if (getCountry() != null ? !getCountry().equals(response.getCountry()) : response.getCountry() != null)
            return false;
        return getRegion() != null ? getRegion().equals(response.getRegion()) : response.getRegion() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getFirstName() != null ? getFirstName().hashCode() : 0);
        result = 31 * result + (getLastName() != null ? getLastName().hashCode() : 0);
        result = 31 * result + (getPhoneNumber() != null ? getPhoneNumber().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 31 * result + (getAvatar() != null ? getAvatar().hashCode() : 0);
        result = 31 * result + (getProfession() != null ? getProfession().hashCode() : 0);
        result = 31 * result + (getCountry() != null ? getCountry().hashCode() : 0);
        result = 31 * result + (getRegion() != null ? getRegion().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TechnicianResponse{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", avatar='" + avatar + '\'' +
                ", profession=" + profession +
                ", country='" + country + '\'' +
                ", region='" + region + '\'' +
                '}';
    }
}