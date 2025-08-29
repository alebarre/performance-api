package io.com.performance.rowmapper;

import io.com.performance.domain.Address;
import io.com.performance.domain.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressRowMapper implements RowMapper<Address> {

    @Override
    public Address mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return Address.builder()
                .id(resultSet.getLong("id"))
                .street(resultSet.getString("street"))
                .number(resultSet.getInt("number"))
                .city(resultSet.getString("city"))
                .state(resultSet.getString("state"))
                .zip(resultSet.getString("zip"))
                .country(resultSet.getString("country"))
                .build();
    }

}
