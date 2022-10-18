package com.thesis.fixable.customer;

import com.thesis.fixable.auth.user.Role;
import com.thesis.fixable.auth.user.UserEntity;
import com.thesis.fixable.exceptionshandling.exceptions.EmailAlreadyExistException;
import com.thesis.fixable.exceptionshandling.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerEntity addNewCustomer(CustomerDTO dto) {
        if (customerRepository.existsByUser_EmailIgnoreCase(dto.getEmail())) {
            throw new EmailAlreadyExistException(String.format("Provide email %s already exist", dto.getEmail()));
        }
        UserEntity user = new UserEntity(
                dto.getEmail(),
                dto.getPassword(),
                Role.CUSTOMER);

        return customerRepository.save(
                new CustomerEntity(
                        dto.getFirstName(),
                        dto.getLastName(),
                        user,
                        dto.getPhoneNumber(),
                        dto.getAvatar()
                )
        );
    }

    public void deleteCustomer(Long id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
        }
        throw new EntityNotFoundException("Customer Does Not Exist");
    }

    public CustomerEntity getCustomerById(Long id) {
        return customerRepository.
                findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer Does Not Exist"));
    }

    public CustomerEntity getCustomerByEmail(String email) {
        return customerRepository.
                findByUser_EmailIgnoreCase(email)
                .orElseThrow(() -> new EntityNotFoundException(String.format("No such user with this email: %s", email)));
    }

    public CustomerEntity updateCustomer(Long id, CustomerDTO dto) {
        CustomerEntity existingCustomer = customerRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer Does Not Exist"));

        existingCustomer.setAvatar(dto.getAvatar());
        existingCustomer.setFirstName(dto.getFirstName());
        existingCustomer.setLastName(dto.getLastName());
        existingCustomer.setPhoneNumber(dto.getPhoneNumber());
        existingCustomer.getUser().setPassword(dto.getPassword());

        return customerRepository.save(existingCustomer);
        //TODO provide dynamic mapping instead of mapping field one by one
    }

    public CustomerEntity resetPassword(Long id, String password) {
        CustomerEntity existingCustomer = customerRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer Does Not Exist"));

        existingCustomer.getUser().setPassword(password);
        return customerRepository.save(existingCustomer);
    }

}

