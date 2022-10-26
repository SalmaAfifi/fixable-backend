package com.thesis.fixable.technician;

import com.thesis.fixable.auth.user.Role;
import com.thesis.fixable.auth.user.UserEntity;
import com.thesis.fixable.exceptionshandling.exceptions.EmailAlreadyExistException;
import com.thesis.fixable.exceptionshandling.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechnicianService {

    @Autowired
    private TechnicianRepository repository;

    public TechnicianEntity addNewTechnician(TechnicianDTO dto) {
        if (repository.existsByUser_EmailIgnoreCase(dto.getEmail())) {
            throw new EmailAlreadyExistException(String.format("Provide email %s already exist", dto.getEmail()));
        }

        UserEntity user = new UserEntity(
                dto.getEmail(),
                dto.getPassword(),
                Role.TECHNICIAN);

        return repository.save(
                new TechnicianEntity(
                        dto.getFirstName(),
                        dto.getLastName(),
                        user,
                        dto.getPhoneNumber(),
                        dto.getAvatar(),
                        dto.getProfession(),
                        dto.getCountry(),
                        dto.getRegion(),
                        WKTUtil.createPoint(dto.getLatitude(), dto.getLongitude())
                )
        );
    }

    public void deleteTechnician(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Technician Does Not Exist");
        }
        repository.deleteById(id);
    }

    public TechnicianEntity getTechnicianById(Long id) {
        return repository.
                findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Technician Does Not Exist"));
    }

    public TechnicianEntity getTechnicianByEmail(String email) {
        return repository.
                findByUser_EmailIgnoreCase(email)
                .orElseThrow(() -> new EntityNotFoundException(String.format("No such user with this email: %s", email)));
    }


    public TechnicianEntity resetPassword(Long id, String password) {
        TechnicianEntity existingTechnician = repository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Technician Does Not Exist"));

        existingTechnician.getUser().setPassword(password);
        return repository.save(existingTechnician);
    }

    public TechnicianEntity updateTechnician(Long id, TechnicianDTO dto) {
        TechnicianEntity existingTechnician = repository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Technician Does Not Exist"));

        existingTechnician.setAvatar(dto.getAvatar());
        existingTechnician.setFirstName(dto.getFirstName());
        existingTechnician.setLastName(dto.getLastName());
        existingTechnician.setPhoneNumber(dto.getPhoneNumber());
        existingTechnician.getUser().setPassword(dto.getPassword());
        existingTechnician.setCountry(dto.getCountry());
        existingTechnician.setRegionName(dto.getRegion());
        existingTechnician.setProfession(dto.getProfession());
        existingTechnician.setRegion(WKTUtil.createPoint(dto.getLatitude(), dto.getLongitude()));

        return repository.save(existingTechnician);
        //TODO provide dynamic mapping instead of mapping field one by one
    }

    public List<TechnicianEntity> searchTechniciansByLocation(Double latitude, Double longitude) {
        return repository.findNearWithinDistance(
                WKTUtil.createPoint(latitude, longitude),
                100
        );
    }

    public List<TechnicianEntity> searchTechniciansByLocationAndProfession(Double latitude, Double longitude, String profession) {
        return repository.findNearWithinDistanceAndProfession(
                WKTUtil.createPoint(latitude, longitude),
                100,
                profession
        );
    }
}
