package io.com.performance.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Logradouro não pode estar vazio")
    private String street;

    private int number;

    @NotEmpty(message = "Cidade não pode estar vazio")
    private String city;

    private String complement;

    @NotEmpty(message = "Estado não pode estar vazio")
    private String state;

    @NotEmpty(message = "CEP não pode estar vazio")
    private String zip;

    @NotEmpty(message = "País não pode estar vazio")
    private String country;

    // Address.java
    @ManyToOne
    @JoinColumn(name = "customere_id")  // 👈 corrigir para bater com o banco
    @JsonIgnore
    private Customer customer;


}

