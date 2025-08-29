package io.com.performance.repository;

import io.com.performance.domain.Address;
import io.com.performance.domain.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository<T extends Address> {

    List<Address> getAddressByCustomerId(Long customerId);

}
