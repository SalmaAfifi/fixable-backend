package com.thesis.fixable.technician;

import com.thesis.fixable.auth.user.Role;
import com.thesis.fixable.auth.user.UserEntity;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.ParseException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TechnicianRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    TechnicianRepository repo;

    @Test
    void testSaveTechnician() throws ParseException {
        TechnicianEntity entity = new TechnicianEntity(
                "Budapest",
                "User",
                new UserEntity("email@email.com", "password", Role.TECHNICIAN),
                "+36 20 2322",
                "avatarurl",
                Profession.PLUMBER,
                "Hungary",
                "Budapest",
                WKTUtil.createPoint(2, 5)
        );

        entityManager.persistAndFlush(entity);
        assertTrue(repo.existsById(entity.getId()));
    }

    @Test
    void testFindBudapestUser_ByDistance() throws ParseException {
        Technicians technicians = new Technicians();
        addTechnician(technicians);
        Point budapest = WKTUtil.createPoint(47.510337, 19.056079);
        List<TechnicianEntity> result = repo.findNearWithinDistance(budapest, 100);
        assertNotNull(result);
        assertEquals(4, result.size());
        assertEquals(
                Set.of(
                        technicians.getBudapestCarpenter(),
                        technicians.getBudapestCleaningPerson(),
                        technicians.getBudapestPlumber(),
                        technicians.getBudapestElectrician()
                ),
                Set.copyOf(result)
        );
    }

    @Test
    void testFindBudapestUser_ByDistance_AndProfession() throws ParseException {
        Technicians technicians = new Technicians();
        addTechnician(technicians);
        Point budapest = WKTUtil.createPoint(47.510337, 19.056079);
        List<TechnicianEntity> result = repo.findNearWithinDistanceAndProfession(budapest, 100, Profession.PLUMBER.name());
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(technicians.getBudapestPlumber(), result.get(0));
    }

    @Test
    void testFindBudapestUser_ByDistance_AndProfession_CaseInsensitive() throws ParseException {
        Technicians technicians = new Technicians();
        addTechnician(technicians);
        Point budapest = WKTUtil.createPoint(47.510337, 19.056079);
        List<TechnicianEntity> result = repo.findNearWithinDistanceAndProfession(budapest, 100, "Plumber");
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(technicians.getBudapestPlumber(), result.get(0));
    }

    @Test
    void testFindBudapestUser_ByDistance_AndProfession_WhenProfessionNotMatch() throws ParseException {
        Technicians technicians = new Technicians();
        addTechnician(technicians);
        Point budapest = WKTUtil.createPoint(47.510337, 19.056079);
        List<TechnicianEntity> result = repo.findNearWithinDistanceAndProfession(budapest, 100, "Non existing profession");
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    void testFindBudapestUser_ByDistance_WhenNoMatchingFound() throws ParseException {
        addTechnician(new Technicians());
        Point japan = WKTUtil.createPoint(36.204824, 138.252924);
        List<TechnicianEntity> result = repo.findNearWithinDistanceAndProfession(japan, 100, Profession.PLUMBER.name());
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    void testExistByEmail() {
        TechnicianEntity entity = new TechnicianEntity(
                "Budapest",
                "User",
                new UserEntity("email@email.com", "password", Role.TECHNICIAN),
                "+36 20 2322",
                "avatarurl",
                Profession.PLUMBER,
                "Hungary",
                "Budapest",
                WKTUtil.createPoint(2, 5)
        );

        entityManager.persistAndFlush(entity);
        assertTrue(repo.existsByUser_EmailIgnoreCase("email@email.com"));
    }

    @Test
    void testFindByEmail() {
        TechnicianEntity entity = new TechnicianEntity(
                "Budapest",
                "User",
                new UserEntity("email@email.com", "password", Role.TECHNICIAN),
                "+36 20 2322",
                "avatarurl",
                Profession.PLUMBER,
                "Hungary",
                "Budapest",
                WKTUtil.createPoint(2, 5)
        );

        entityManager.persistAndFlush(entity);
        assertTrue(repo.findByUser_EmailIgnoreCase("email@email.com").isPresent());
        assertEquals(entity, repo.findByUser_EmailIgnoreCase("email@email.com").get());
    }

    void addTechnician(Technicians technicians) {
        entityManager.persistAndFlush(technicians.getBudapestCarpenter());
        entityManager.persistAndFlush(technicians.getBudapestCleaningPerson());
        entityManager.persistAndFlush(technicians.getBudapestElectrician());
        entityManager.persistAndFlush(technicians.getBudapestPlumber());
        entityManager.persistAndFlush(technicians.getDebrecenCarpenter());
        entityManager.persistAndFlush(technicians.getEgyptUser());
        entityManager.persistAndFlush(technicians.getUsaUser());
    }
}