package io.com.performance.repository.implementation;

import io.com.performance.DTO.UserDTO;
import io.com.performance.domain.Address;
import io.com.performance.domain.Role;
import io.com.performance.domain.User;
import io.com.performance.domain.UserPrincipal;
import io.com.performance.enumeration.VerificationType;
import io.com.performance.excecption.ApiException;
import io.com.performance.form.UpdateForm;
import io.com.performance.repository.AddressRepository;
import io.com.performance.repository.RoleRepository;
import io.com.performance.repository.UserRepository;
import io.com.performance.rowmapper.AddressRowMapper;
import io.com.performance.rowmapper.UserRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static io.com.performance.constant.Constants.DATE_FORMAT;
import static io.com.performance.enumeration.RoleType.ROLE_USER;
import static io.com.performance.enumeration.VerificationType.ACCOUNT;
import static io.com.performance.enumeration.VerificationType.PASSWORD;
import static io.com.performance.query.UserQuery.*;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static java.util.Map.of;
import static java.util.Objects.requireNonNull;
import static net.sf.jsqlparser.util.validation.metadata.NamedObject.user;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.time.DateFormatUtils.format;
import static org.apache.commons.lang3.time.DateUtils.addDays;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentContextPath;

@Repository
@RequiredArgsConstructor
@Slf4j
public class AddressRepositoryImpl implements AddressRepository<Address> {

    private final NamedParameterJdbcTemplate jdbc;

    public Collection<User> list(int page, int pageSize) {
        return List.of();
    }

    @Override
    public List<Address> getAddressByCustomerId(Long customerId) {
        try {
            return jdbc.query(
                    SELECT_ADDRESS_BY_CUSTOMER_ID,
                    of("id", customerId),
                    new AddressRowMapper()
            );
        } catch (EmptyResultDataAccessException exception) {
            throw new ApiException("No Address found for customer id: " + customerId);
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            throw new ApiException("An error occurred. Please try again.");
        }
    }
}