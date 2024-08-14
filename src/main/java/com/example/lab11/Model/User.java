package com.example.lab11.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @NotEmpty
    @Column(columnDefinition = "varchar(15) not null unique")
    private String username;

    @NotEmpty(message = "Password must not be empty")
    @Size(min = 8, message = "Password must be more than 8 characters")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z]).{8,}$", message = "Password must contain letter and number")

    @Column(columnDefinition = "varchar (15) not null")
    @Check(constraints = "LENGTH(password) >= 8")
    @Check(constraints = "password ~ '^(?=.*[0-9])(?=.*[a-zA-Z]).{6,}$'")
    private String password;

    @NotEmpty(message = "Email must not be empty")
    @Email(message = "Email should be contain '@' ")

    @Column(columnDefinition = "varchar(40) not null unique ")
    @Check(constraints = "email LIKE '%@%'")
    private String email;

    private LocalDate registrationDate;
}
