package io.com.performance.service;

import io.com.performance.domain.Address;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AddressService {

    List<Address> getAddressesByCustomerId(Long customerId);

}
