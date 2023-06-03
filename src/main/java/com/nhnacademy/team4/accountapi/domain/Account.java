package com.nhnacademy.team4.accountapi.domain;


import lombok.*;
import org.springframework.cglib.core.Local;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="account_id")
    private Long accountId;

    private String email;

    @Column(name="login_id")
    private String loginId;

    private String password;

    @Column(name="create_date")
    private LocalDate createDate;

    @Enumerated(value=EnumType.STRING)
    private AccountStatus status;

    @Column(name="last_login_date")
    private LocalDate lastLoginDate;

    private String role;


}
