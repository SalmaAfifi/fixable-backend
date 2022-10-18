package com.thesis.fixable.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService service;


    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@Valid @RequestBody CustomerDTO dto) {
        service.addNewCustomer(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getCustomer(@PathVariable Long id) {
        CustomerEntity entity = service.getCustomerById(id);
        CustomerResponse responseBody = CustomerResponse.fromCustomerEntity(entity);
        return ResponseEntity.ok(responseBody);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponse> updateCustomer(@PathVariable Long id, @Valid @RequestBody CustomerDTO dto) {
        //Todo currently noop as there is no security validation, can be replaced by either make customer and user have the same id
        //Or replace all the current logic to use user id not the customer id
        CustomerEntity entity = service.updateCustomer(id, dto);
        CustomerResponse responseBody = CustomerResponse.fromCustomerEntity(entity);
        return ResponseEntity.ok(responseBody);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        service.deleteCustomer(id);
        return ResponseEntity.ok().build();
    }
}
