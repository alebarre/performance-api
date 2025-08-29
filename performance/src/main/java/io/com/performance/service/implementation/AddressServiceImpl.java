package io.com.performance.service.implementation;

import io.com.performance.domain.Address;
import io.com.performance.repository.AddressRepository;
import io.com.performance.service.AddressService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    public List<Address> getAddressesByCustomerId(Long id) { return addressRepository.getAddressByCustomerId(id); }

}
