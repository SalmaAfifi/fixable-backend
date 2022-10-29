package com.thesis.fixable.search;

import com.thesis.fixable.technician.TechnicianEntity;
import com.thesis.fixable.technician.TechnicianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {

    @Autowired
    TechnicianService technicianService;

    public List<TechnicianEntity> findAllTechnicianNearBy(Double latitude, Double longitude) {
        return technicianService.searchTechniciansByLocation(latitude, longitude);
    }

    public List<TechnicianEntity> findAllTechnicianNearBy(Double latitude, Double longitude, String profession) {
        return technicianService.searchTechniciansByLocationAndProfession(latitude, longitude, profession);
    }
}
