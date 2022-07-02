package com.mosken.rodrigo.letscode.challenge.authorizationapi.adapters.mysql.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "User")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBean {

    @Id
    private String username;

    private String password;

    private String email;

    private int xp;

    @OneToOne
    @JoinColumn(name = "role_name", referencedColumnName = "name")
    private RoleBean role;


}