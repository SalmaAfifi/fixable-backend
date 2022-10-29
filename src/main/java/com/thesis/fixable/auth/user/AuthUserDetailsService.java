package com.thesis.fixable.auth.user;

import com.thesis.fixable.customer.CustomerService;
import com.thesis.fixable.exceptionshandling.exceptions.EmailAlreadyExistException;
import com.thesis.fixable.exceptionshandling.exceptions.EntityNotFoundException;
import com.thesis.fixable.technician.TechnicianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class AuthUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final CustomerService customerService;
    private final TechnicianService technicianService;

    @Autowired
    public AuthUserDetailsService(UserRepository userRepository, CustomerService customerService, TechnicianService technicianService) {
        this.userRepository = userRepository;
        this.customerService = customerService;
        this.technicianService = technicianService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return loadUserByEmail(username);
    }

    public UserEntity loadUserByEmail(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(
                        () -> new UsernameNotFoundException(String.format("Email: %s does not exist", email))
                );
    }

    public UserEntity addUser(UserEntity userEntity) {
        if (userRepository.existsByEmail(userEntity.getEmail()))
            throw new EmailAlreadyExistException(String.format("Provide email %s already exist", userEntity.getEmail()));
        return userRepository.save(userEntity);
    }

    public UserEntity getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User Does Not Exist"));
    }

    public UserEntity updatePassword(Long id, String password) {
        if (userRepository.existsById(id)) {
            userRepository.updatePassword(id, password);
            return userRepository.findById(id).get();
        }
        throw new EntityNotFoundException("User Does Not Exist");
    }

    public void deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return;
        }
        throw new EntityNotFoundException("User Does Not Exist");
    }

    public Long getIdByAuthentication(Authentication auth) {
        UserEntity userEntity = (UserEntity) auth.getPrincipal();
        if (userEntity.getRole() == Role.CUSTOMER) {
            return customerService.getCustomerByEmail(userEntity.getEmail()).getId();
        } else if (userEntity.getRole() == Role.TECHNICIAN){
            return technicianService.getTechnicianByEmail(userEntity.getEmail()).getId();
        }
            return null;
    }
    public Role getRoleByAuthentication(Authentication auth) {
        UserEntity userEntity = (UserEntity) auth.getPrincipal();
        return userEntity.getRole();
    }
}
