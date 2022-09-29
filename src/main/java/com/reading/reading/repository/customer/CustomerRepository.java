package com.reading.reading.repository.customer;

import com.reading.reading.model.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    boolean existsByPhone(String phone);

    Optional<Customer> findOneByPhone(String phone);
}
