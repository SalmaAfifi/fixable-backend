package com.thesis.fixable.technician;

import com.vividsolutions.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

public interface TechnicianRepository extends JpaRepository<TechnicianEntity, Long> {

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query(value = "SELECT * " +
            "FROM technicians " +
            "WHERE ST_DISTANCE_SPHERE(technicians.region, :point) < (:distanceKm * 1000)  "
            , nativeQuery = true)
    List<TechnicianEntity> findNearWithinDistance(@Param("point") Point point, @Param("distanceKm") double distance);

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query(value = "SELECT * " +
            "FROM technicians " +
            "WHERE ST_DISTANCE_SPHERE(technicians.region, :point) < (:distanceKm * 1000)  " +
            "AND profession LIKE :profession"
            , nativeQuery = true)
    List<TechnicianEntity> findNearWithinDistanceAndProfession(@Param("point") Point point,
                                                               @Param("distanceKm") double distance,
                                                               @Param("profession") String profession);

    Optional<TechnicianEntity> findByUser_EmailIgnoreCase(@NonNull String email);


    boolean existsByUser_EmailIgnoreCase(@NonNull String email);


}
