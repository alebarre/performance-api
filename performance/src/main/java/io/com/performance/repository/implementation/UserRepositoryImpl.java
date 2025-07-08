package io.com.performance.repository.implementation;

import io.com.performance.domain.Role;
import io.com.performance.domain.User;
import io.com.performance.domain.UserPrincipal;
import io.com.performance.DTO.UserDTO;
import io.com.performance.enumeration.VerificationType;
import io.com.performance.excecption.ApiException;
import io.com.performance.repository.RoleRepository;
import io.com.performance.repository.UserRepository;
import io.com.performance.rowmapper.UserRowMapper;
import io.com.performance.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static io.com.performance.constant.Constants.DATE_FORMAT;
import static io.com.performance.enumeration.RoleType.ROLE_USER;
import static io.com.performance.enumeration.VerificationType.ACCOUNT;
import static io.com.performance.enumeration.VerificationType.PASSWORD;
import static io.com.performance.query.UserQuery.*;
import static java.util.Map.of;
import static java.util.Objects.requireNonNull;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.time.DateFormatUtils.format;
import static org.apache.commons.lang3.time.DateUtils.addDays;

@Repository
@RequiredArgsConstructor
@Slf4j
public class UserRepositoryImpl implements UserRepository<User>, UserDetailsService {

    private final NamedParameterJdbcTemplate jdbc;
    private final RoleRepository<Role> roleRepository;
    private final BCryptPasswordEncoder encoder;

    @Override
    public User create(User user) {
        // Check if the email is unique
        if (getEmailCount(user.getEmail().trim().toLowerCase()) > 0) {
            throw new ApiException("Email already in use. Please use a different email and try again.");
        }

        // Save new user
        try {
            KeyHolder holder = new GeneratedKeyHolder();
            SqlParameterSource parameters = getSqlParametersSource(user);
            jdbc.update(INSERT_USER_QUERY, parameters, holder);
            user.setId(requireNonNull(holder.getKey()).longValue());

            // Add role to the user
            roleRepository.addRoleToUser(user.getId(), ROLE_USER.name());

            // Send verification URL
            String verificationUrl = getVerificationUrl(UUID.randomUUID().toString(), ACCOUNT.getType());

            // Save URL in verification table
            jdbc.update(INSERT_ACCOUNT_VERIFICATION_URL_QUERY, of("userId", user.getId(), "url", verificationUrl));

            // Send email to user with verification URL
            //emailService.sendEmailVerificationUrl(user.getFirstname(), user.getEmail(), verificationUrl, ACCOUNT);
            user.setEnabled(false);
            user.setNotLocked(true);

            // Return the newly created user
            return user;

            // If any errors, throw exception with proper message
        } catch (Exception exception) {
            log.error(exception.getMessage());
            throw new ApiException("An error occurred. Please try again later.");
        }
    }

    @Override
    public Collection<User> list(int page, int pageSize) {
        return List.of();
    }

    @Override
    public User get(Long id) {
        return null;
    }

    @Override
    public User update(User data) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = getUserByEmail(email);
        if (user == null) {
            log.error("User not found with email {}", email);
            throw new UsernameNotFoundException("User not found in database " + email);
        } else {
            log.info("Found user in database with email {}", email);
            return new UserPrincipal(user, roleRepository.getRoleByUserId(user.getId()));
        }
    }
@Override
    public User getUserByEmail(String email) {

        try {
            User user = jdbc.queryForObject(SELECT_USER_BY_EMAIL_QUERY, of("email", email), new UserRowMapper());
            return user;
        } catch (EmptyResultDataAccessException exception){
            throw new ApiException("No user found by email: " + email);
        } catch (Exception exception) {
            log.error(exception.getMessage());
            throw new ApiException("An error occurred. Please try again.");
        }

    }

    @Override
    public void sendVerificationCode(UserDTO user) {
        String expirationDate = format(addDays(new Date(), 1), DATE_FORMAT);
        String verificationCode = randomAlphabetic(8).toUpperCase();
        try {
            jdbc.update(DELETE_VERIFICATION_CODE_BY_USER_ID, of("id", user.getId()));
            jdbc.update(INSERT_VERIFICATION_CODE_QUERY, of("userId", user.getId(), "code", verificationCode, "expirationDate", expirationDate));
            //sendSMS(user.getPhone(), "De: Performance Sports \nUse esse código para autenticar seu acesso\n" + verificationCode);
            log.info("Verification Code: {}", verificationCode);
        } catch (Exception exception) {
            log.error(exception.getMessage());
            throw new ApiException("An error occurred. Please try again.");
        }
    }

    @Override
    public User verifyCode(String email, String code) {
        if(isVerificationCodeExpired(code)) throw new ApiException("Verification code has expired. Please log in again.");
        try {
            User userByCode = jdbc.queryForObject(SELECT_USER_BY_USER_CODE_QUERY, of("code", code), new UserRowMapper());
            User userByEmail = jdbc.queryForObject(SELECT_USER_BY_EMAIL_QUERY, of("email", email), new UserRowMapper());
            if(userByCode.getEmail().equalsIgnoreCase(userByEmail.getEmail())){
                jdbc.update(DELETE_CODE, of("code",  code));
                return userByCode;
            } else {
                throw new ApiException("Code is invalid. Please try again.");
            }
        } catch (EmptyResultDataAccessException exception) {
            throw new ApiException("Could not find any record");
        }catch (Exception exception){
            throw new ApiException("An error occurred, please try again");
        }

    }

    @Override
    public void resetPassword(String email) {
        if(getEmailCount(email.trim().toLowerCase()) <= 0) throw new ApiException("There is no account for this email address.");
        try {
            String expirationDate = format(addDays(new Date(), 1), DATE_FORMAT);
            User user = getUserByEmail(email);
            String verificationUrl = getVerificationUrl(UUID.randomUUID().toString(), PASSWORD.getType());
            jdbc.update(DELETE_PASSWORD_VERIFICATION_BY_USER_ID_QUERY, of("userId",  user.getId()));
            jdbc.update(INSERT_PASSWORD_VERIFICATION_QUERY, of("userId",  user.getId(), "url", verificationUrl, "expirationDate", expirationDate));
            //sendEmail(user.getFirstName(), email, verificationUrl, PASSWORD);
            log.info("Verification URL: {}", verificationUrl);
        } catch (Exception exception) {
            throw new ApiException("An error occurred. Please try again.");
        }
    }

    @Override
    public User verifyPasswordKey(String key) {
        if(isLinkExpired(key, PASSWORD)) throw new ApiException("This link has expired. Please reset your password again.");
        try {
            User user = jdbc.queryForObject(SELECT_USER_BY_PASSWORD_URL_QUERY, of("url", getVerificationUrl(key, PASSWORD.getType())), new UserRowMapper());
            //jdbc.update("DELETE_USER_FROM_PASSWORD_VERIFICATION_QUERY", of("id", user.getId())); //Depends on use case / developer or business
            return user;
        } catch (EmptyResultDataAccessException exception) {
            log.error(exception.getMessage());
            throw new ApiException("This link is not valid. Please reset your password again.");
        } catch (Exception exception) {
            log.error(exception.getMessage());
            throw new ApiException("An error occurred. Please try again.");
        }
    }

    @Override
    public void renewPassword(String key, String password, String confirmPassword) {
        String url = jdbc.query(SELECT_URL_FROM_VERIFFICATIONS, rs -> rs.next() ? rs.getString("url") : null);
        if (!isTokenMatch(url, key)) {
            throw new ApiException("This link has expired. Please reset your password again.");
        }
        if(!password.equals(confirmPassword)) throw new ApiException("Passwords don't match. Please try again.");
        try {
            jdbc.update(UPDATE_USER_PASSWORD_BY_URL_QUERY, of("password", encoder.encode(password), "url", getVerificationUrl(key, PASSWORD.getType())));
            jdbc.update(DELETE_VERIFICATION_BY_URL_QUERY, of("url", getVerificationUrl(key, PASSWORD.getType())));
        } catch (Exception exception) {
            log.error(exception.getMessage());
            throw new ApiException("An error occurred. Please try again.");
        }
    }

    @Override
    public User verifyAccount(String key) {
        try {
            User user = jdbc.queryForObject(SELECT_USER_BY_ACCOUNT_URL_QUERY, of("url", getVerificationUrl(key, ACCOUNT.getType())), new UserRowMapper());
            jdbc.update(UPDATE_USER_ENABLED_QUERY, of("enabled", true, "id", user.getId()));
            return user;
        } catch (EmptyResultDataAccessException exception) {
            throw new ApiException("Account verified");
        } catch (Exception exception) {
            throw new ApiException("An error occurred. Please try again.");
        }
    }

    private boolean isTokenMatch(String url, String tokenToCompare) {
        String prefix = "verifypassword/";
        int index = url.indexOf(prefix);

        if (index != -1) {
            String token = url.substring(index + prefix.length());
            return token.equals(tokenToCompare);
        }

        return false;
    }

    private Boolean isLinkExpired(String key, VerificationType password) {
        try {
            return jdbc.queryForObject(SELECT_EXPIRATION_BY_URL, of("url", getVerificationUrl(key, password.getType())), Boolean.class);
        } catch (EmptyResultDataAccessException exception) {
            log.error(exception.getMessage());
            throw new ApiException("This link is not valid. Please reset your password again.");
        } catch (Exception exception) {
            log.error(exception.getMessage());
            throw new ApiException("An error occurred. Please try again.");
        }
    }

    private Boolean isVerificationCodeExpired(String code) {
        try {
            return jdbc.queryForObject(SELECT_CODE_EXPIRATION_QUERY, of("code", code), Boolean.class);
        } catch (EmptyResultDataAccessException exception) {
            throw new ApiException("This code is no longer valid. Please login again.");
        } catch (Exception exception) {
            throw new ApiException("An error occurred. Please try again.");
        }
    }

    private Integer getEmailCount(String email) {
        return jdbc.queryForObject(COUNT_USER_EMAIL_QUERY, of("email", email), Integer.class);
    }

    private SqlParameterSource getSqlParametersSource(User user) {
        return new MapSqlParameterSource()
                .addValue("firstName", user.getFirstName())
                .addValue("lastName", user.getLastName())
                .addValue("email", user.getEmail())
                .addValue("password", encoder.encode(user.getPassword()));
    }

    private String getVerificationUrl(String key, String type){
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/verify" + type + "/" + key).toUriString();
    }
}