package com.thesis.fixable.technician;

import com.thesis.fixable.auth.user.Role;
import com.thesis.fixable.auth.user.UserEntity;

final class Technicians {

    private final TechnicianEntity budapestCarpenter = new TechnicianEntity(
            "Budapest",
            "User",
            new UserEntity("budapest@email.com", "password", Role.TECHNICIAN),
            "+36 20 2322",
            "avatarurl",
            Profession.CARPENTER,
            "Hungary",
            "Budapest",
            WKTUtil.createPoint(47.526106, 19.074669)
    );
    private final TechnicianEntity budapestPlumber = new TechnicianEntity(
            "Budapest",
            "User",
            new UserEntity("budapest2@email.com", "password", Role.TECHNICIAN),
            "+36 20 2322",
            "avatarurl",
            Profession.PLUMBER,
            "Hungary",
            "Budapest",
            WKTUtil.createPoint(47.500442, 19.083988)
    );
    private final TechnicianEntity budapestElectrician = new TechnicianEntity(
            "Budapest",
            "User",
            new UserEntity("budapest3@email.com", "password", Role.TECHNICIAN),
            "+36 20 2322",
            "avatarurl",
            Profession.ELECTRICIAN,
            "Hungary",
            "Budapest",
            WKTUtil.createPoint(47.496321, 19.069613)
    );
    private final TechnicianEntity budapestCleaningPerson = new TechnicianEntity(
            "Budapest",
            "User",
            new UserEntity("budapest4@email.com", "password", Role.TECHNICIAN),
            "+36 20 2322",
            "avatarurl",
            Profession.CLEANING_SERVICES,
            "Hungary",
            "Budapest",
            WKTUtil.createPoint(47.526106, 19.074669)
    );

    private final TechnicianEntity debrecenCarpenter = new TechnicianEntity(
            "Debrecen",
            "User",
            new UserEntity("debrecen1@email.com", "password", Role.TECHNICIAN),
            "+36 20 2322",
            "avatarurl",
            Profession.CARPENTER,
            "Hungary",
            "Debrecen",
            WKTUtil.createPoint(47.528888, 21.625449)
    );

    private final TechnicianEntity egyptUser = new TechnicianEntity(
            "Egypt",
            "User",
            new UserEntity("egypt@email.com", "password", Role.TECHNICIAN),
            "+36 20 2322",
            "avatarurl",
            Profession.CLEANING_SERVICES,
            "Egypt",
            "Cairo",
            WKTUtil.createPoint(30.04442, 31.235712)
    );

    private final TechnicianEntity usaUser = new TechnicianEntity(
            "Usa",
            "User",
            new UserEntity("usa@email.com", "password", Role.TECHNICIAN),
            "+36 20 2322",
            "avatarurl",
            Profession.ELECTRICIAN,
            "USA",
            "Random",
            WKTUtil.createPoint(40.058324, -74.405661)
    );

    public TechnicianEntity getBudapestCarpenter() {
        return budapestCarpenter;
    }

    public TechnicianEntity getBudapestPlumber() {
        return budapestPlumber;
    }

    public TechnicianEntity getBudapestElectrician() {
        return budapestElectrician;
    }

    public TechnicianEntity getBudapestCleaningPerson() {
        return budapestCleaningPerson;
    }

    public TechnicianEntity getDebrecenCarpenter() {
        return debrecenCarpenter;
    }

    public TechnicianEntity getEgyptUser() {
        return egyptUser;
    }

    public TechnicianEntity getUsaUser() {
        return usaUser;
    }
}
