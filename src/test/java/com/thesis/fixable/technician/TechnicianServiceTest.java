package com.thesis.fixable.technician;

import com.thesis.fixable.exceptionshandling.exceptions.EmailAlreadyExistException;
import com.thesis.fixable.exceptionshandling.exceptions.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
class TechnicianServiceTest {
    @MockBean
    TechnicianRepository repo;

    @Autowired
    TechnicianService service;

    @Test
    void testAddNewTechnician() {
        when(repo.existsByUser_EmailIgnoreCase(DtoUtil.TECHNICIAN_DTO.getEmail())).thenReturn(false);
        when(repo.save(DtoUtil.TECHNICIAN_ENTITY)).thenReturn(DtoUtil.TECHNICIAN_ENTITY);
        assertEquals(
                DtoUtil.TECHNICIAN_ENTITY,
                service.addNewTechnician(DtoUtil.TECHNICIAN_DTO)
        );
    }

    @Test
    void testAddNewTechnician_WhenEmailAlreadyExist() {
        when(repo.existsByUser_EmailIgnoreCase(DtoUtil.TECHNICIAN_DTO.getEmail())).thenReturn(true);
        when(repo.save(DtoUtil.TECHNICIAN_ENTITY)).thenReturn(DtoUtil.TECHNICIAN_ENTITY);
        assertThrows(EmailAlreadyExistException.class,
                () -> service.addNewTechnician(DtoUtil.TECHNICIAN_DTO)
        );
    }

    @Test
    void testDeleteTechnician() {
        Long id = DtoUtil.TECHNICIAN_ENTITY.getId();
        when(repo.existsById(id)).thenReturn(true);
        doNothing().when(repo).deleteById(id);
        assertDoesNotThrow(() -> service.deleteTechnician(id));
    }

    @Test
    void testDeleteTechnician_WhenTechnicianDoesNotExist() {
        Long id = DtoUtil.TECHNICIAN_ENTITY.getId();
        when(repo.existsById(id)).thenReturn(false);
        assertThrows(EntityNotFoundException.class,
                () -> service.deleteTechnician(id));
    }
}