package io.com.performance.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Collection;
import java.util.Date;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.*;

@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3, max = 50, message = "O nome deve conter entre 3 e 50 caracteres.")
    private String name;

    @NotEmpty(message = "E-mail não pode estar vazio")
    @Email(message = "E-mail inválido. Por favor reveja e tente novamente.")
    private String email;

    private String type;
    private String status;
    private String address;

    @NotEmpty(message = "Telefone não pode estar vazio")
    @Size(min = 10, max = 12, message = "O telefone deve conter entre 10 e 12 caracteres.")

    private String phone;

    private String imageUrl;
    private Date createdAt;

    @OneToMany(mappedBy = "customer", fetch = EAGER, cascade = ALL)
    private Collection<Invoice> invoices;

}
