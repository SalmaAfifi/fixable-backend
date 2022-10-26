package com.thesis.fixable.technician;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/technician")
public class TechnicianController {
    @Autowired
    private TechnicianService service;


    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@Valid @RequestBody TechnicianDTO dto) {
        service.addNewTechnician(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TechnicianResponse> getTechnician(@PathVariable Long id) {
        TechnicianEntity entity = service.getTechnicianById(id);
        TechnicianResponse responseBody = TechnicianResponse.fromTechnicianEntity(entity);
        return ResponseEntity.ok(responseBody);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TechnicianResponse> updateTechnician(@PathVariable Long id, @Valid @RequestBody TechnicianDTO dto) {
        //Todo currently noop as there is no security validation, can be replaced by either make customer and user have the same id
        //Or replace all the current logic to use user id not the customer id
        TechnicianEntity entity = service.updateTechnician(id, dto);
        TechnicianResponse responseBody = TechnicianResponse.fromTechnicianEntity(entity);
        return ResponseEntity.ok(responseBody);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTechnician(@PathVariable Long id) {
        service.deleteTechnician(id);
        return ResponseEntity.ok().build();
    }
}
