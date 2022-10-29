package com.thesis.fixable.search;

import com.thesis.fixable.technician.TechnicianResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    SearchService service;

    @GetMapping(params = {"lat", "lng", "profession"})
    public ResponseEntity<List<TechnicianResponse>> findTechnician(@RequestParam("lat") Double lat,
                                                                   @RequestParam("lng") Double lng,
                                                                   @RequestParam("profession") String profession) {
        List<TechnicianResponse> responseBody = service.findAllTechnicianNearBy(lat, lng, profession)
                .stream()
                .map(TechnicianResponse::fromTechnicianEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responseBody);

    }

    @GetMapping(params = {"lat", "lng"})
    public ResponseEntity<List<TechnicianResponse>> findTechnician(@RequestParam("lat") Double lat,
                                                                   @RequestParam("lng") Double lng) {
        List<TechnicianResponse> responseBody = service.findAllTechnicianNearBy(lat, lng)
                .stream()
                .map(TechnicianResponse::fromTechnicianEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responseBody);
    }
}
