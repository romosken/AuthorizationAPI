package com.mosken.rodrigo.letscode.challenge.authorizationapi.adapters.mysql.domain;


import com.mosken.rodrigo.letscode.challenge.authorizationapi.domain.enums.ERole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Role")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoleBean {

    @Id
    @Enumerated(EnumType.STRING)
    private ERole name;

    @Override
    public String toString(){
        return name.toString();
    }
}