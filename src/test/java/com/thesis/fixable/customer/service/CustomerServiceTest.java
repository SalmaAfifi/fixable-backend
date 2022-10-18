package com.thesis.fixable.customer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thesis.fixable.customer.CustomerDTO;
import com.thesis.fixable.customer.CustomerEntity;
import com.thesis.fixable.customer.CustomerService;
import com.thesis.fixable.exceptionshandling.exceptions.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class CustomerServiceTest {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    private CustomerService service;

    @Test
    void testAddNewCustomer() {
        CustomerEntity actualEntity = service.addNewCustomer(TestCustomerUtil.DTO);
        assertNotNull(actualEntity);
        assertEquals(TestCustomerUtil.ENTITY, actualEntity);
    }

    @Test
    void testAddNewCustomer_FromJson() throws JsonProcessingException {
        CustomerDTO dto = OBJECT_MAPPER.readValue(TestCustomerUtil.DTO_JSON, CustomerDTO.class);
        CustomerEntity actualEntity = service.addNewCustomer(dto);
        assertNotNull(actualEntity);
        assertEquals(TestCustomerUtil.ENTITY, actualEntity);
    }

    @Test
    void testGetCustomerById() {
        CustomerEntity expectedCustomer = service.addNewCustomer(TestCustomerUtil.DTO);
        CustomerEntity actualCustomer = service.getCustomerById(expectedCustomer.getId());
        assertEquals(expectedCustomer.getId(), expectedCustomer.getId());
        assertEquals(expectedCustomer, actualCustomer);
    }

    @Test
    void testGetCustomerById_WhenIdNotExist() {
        CustomerEntity expectedCustomer = service.addNewCustomer(TestCustomerUtil.DTO);
        assertThrows(EntityNotFoundException.class, () -> service.getCustomerById(expectedCustomer.getId() + 50));
    }

    @Test
    void testGetCustomerByEmail() {
        CustomerEntity expectedCustomer = service.addNewCustomer(TestCustomerUtil.DTO);
        CustomerEntity actualCustomer = service.getCustomerByEmail(expectedCustomer.getUser().getEmail());
        assertEquals(expectedCustomer.getId(), expectedCustomer.getId());
        assertEquals(expectedCustomer, actualCustomer);
    }

    @Test
    void testGetCustomerByEmail_WhenEmailNotExist() {
        service.addNewCustomer(TestCustomerUtil.DTO);
        assertThrows(EntityNotFoundException.class, () -> service.getCustomerByEmail("NonExisting@email.com"));
    }

    @Test
    void testRestPassword() {
        CustomerEntity originalEntity = service.addNewCustomer(TestCustomerUtil.DTO);
        CustomerEntity updatedCustomer = service.resetPassword(originalEntity.getId(), "new password");
        assertEquals(updatedCustomer.getId(), originalEntity.getId());
        assertEquals("new password", updatedCustomer.getUser().getPassword());
    }
}
