package com.thesis.fixable.technician;

import com.thesis.fixable.auth.user.UserEntity;
import com.vividsolutions.jts.geom.Point;

import javax.persistence.*;

@Entity
@Table(name = "technicians", indexes = {
        @Index(name = "idx_technicianentity_region", columnList = "region")
})
public class TechnicianEntity {
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

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Profession profession;

    //TODO figure out a way to validate that the provided coordinates
    //Matches country and the region
    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String regionName;

    @Column(nullable = false)
    private Point region;

    public TechnicianEntity() {
    }

    public TechnicianEntity(String firstName, String lastName, UserEntity user, String phoneNumber, String avatar, Profession profession, String country, String regionName, Point region) {
        this.firstName = firstName;
        LastName = lastName;
        this.user = user;
        this.phoneNumber = phoneNumber;
        this.avatar = avatar;
        this.profession = profession;
        this.country = country;
        this.regionName = regionName;
        this.region = region;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Point getRegion() {
        return region;
    }

    public void setRegion(Point region) {
        this.region = region;
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

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TechnicianEntity)) return false;

        TechnicianEntity entity = (TechnicianEntity) o;

        if (getFirstName() != null ? !getFirstName().equals(entity.getFirstName()) : entity.getFirstName() != null)
            return false;
        if (getLastName() != null ? !getLastName().equals(entity.getLastName()) : entity.getLastName() != null)
            return false;
        if (getUser() != null ? !getUser().equals(entity.getUser()) : entity.getUser() != null) return false;
        if (getPhoneNumber() != null ? !getPhoneNumber().equals(entity.getPhoneNumber()) : entity.getPhoneNumber() != null)
            return false;
        if (getAvatar() != null ? !getAvatar().equals(entity.getAvatar()) : entity.getAvatar() != null) return false;
        if (getProfession() != entity.getProfession()) return false;
        if (getCountry() != null ? !getCountry().equals(entity.getCountry()) : entity.getCountry() != null)
            return false;
        if (getRegionName() != null ? !getRegionName().equals(entity.getRegionName()) : entity.getRegionName() != null)
            return false;
        return getRegion() != null ? getRegion().equals(entity.getRegion()) : entity.getRegion() == null;
    }

    @Override
    public int hashCode() {
        int result = getFirstName() != null ? getFirstName().hashCode() : 0;
        result = 31 * result + (getLastName() != null ? getLastName().hashCode() : 0);
        result = 31 * result + (getUser() != null ? getUser().hashCode() : 0);
        result = 31 * result + (getPhoneNumber() != null ? getPhoneNumber().hashCode() : 0);
        result = 31 * result + (getAvatar() != null ? getAvatar().hashCode() : 0);
        result = 31 * result + (getProfession() != null ? getProfession().hashCode() : 0);
        result = 31 * result + (getCountry() != null ? getCountry().hashCode() : 0);
        result = 31 * result + (getRegionName() != null ? getRegionName().hashCode() : 0);
        result = 31 * result + (getRegion() != null ? getRegion().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TechnicianEntity{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", user=" + user +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", avatar='" + avatar + '\'' +
                ", profession=" + profession +
                ", country='" + country + '\'' +
                ", regionName='" + regionName + '\'' +
                ", region=" + region +
                '}';
    }
}
