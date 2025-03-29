package com.poc.back.repositories;

import com.poc.back.models.CustomerServiceModel;
import com.poc.back.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerServiceRepository extends JpaRepository<CustomerServiceModel,Long> {
    CustomerServiceModel findByCustomerservice(User user);

    CustomerServiceModel findByCustomerservice_Id(Long id);

}
