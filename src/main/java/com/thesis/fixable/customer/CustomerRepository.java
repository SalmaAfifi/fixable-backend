package com.thesis.fixable.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    Optional<CustomerEntity> findByUser_EmailIgnoreCase(String email);

    boolean existsByUser_EmailIgnoreCase(@NonNull String email);


}
