package com.thesis.fixable.technician;

import com.thesis.fixable.auth.user.Role;
import com.thesis.fixable.auth.user.UserEntity;

public class DtoUtil {

    public static final String TECHNICIAN_DTO_JSON = String.join(System.lineSeparator(),
            "{" +
                    "  \"email\": \"valid@email.com\"," +
                    "  \"password\": \"strongpassword\"," +
                    "  \"firstName\": \"first name\"," +
                    "  \"lastName\": \"last name\"," +
                    "  \"avatar\": \"avatar/url\"," +
                    "  \"phoneNumber\": \"+1010253698\"," +
                    "  \"latitude\": 80.3000," +
                    "  \"longitude\": 100.359," +
                    "  \"country\": \"Hungary\"," +
                    "  \"region\": \"random region\"," +
                    "  \"profession\": \"plumber\"" +
                    "}");

    public static final TechnicianDTO TECHNICIAN_DTO = new TechnicianDTO(
            "valid@email.com",
            "strongpassword",
            "first name",
            "last name",
            "avatar/url",
            "+1010253698",
            80.3000,
            100.359,
            "Hungary",
            "random region",
            Profession.PLUMBER
    );

    public static final TechnicianEntity TECHNICIAN_ENTITY= new TechnicianEntity(
            "first name",
            "last name",
            new UserEntity(
                    "valid@email.com",
                    "strongpassword",
                    Role.TECHNICIAN
            ),
            "+1010253698",
            "avatar/url",
            Profession.PLUMBER,
            "Hungary",
            "random region",
            WKTUtil.createPoint(80.3000, 100.359)
    );
}
