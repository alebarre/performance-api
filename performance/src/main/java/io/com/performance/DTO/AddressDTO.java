package io.com.performance.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AddressDTO {

    private Long id;
    private String street;
    private String number;
    private String complement;
    private String city;
    private String state;
    private String zip;
    private String country;

}
