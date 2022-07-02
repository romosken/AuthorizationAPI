package com.mosken.rodrigo.letscode.challenge.authorizationapi.adapters.mysql.repositories;

import com.mosken.rodrigo.letscode.challenge.authorizationapi.adapters.mysql.domain.UserBean;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserBean, String> {
    Optional<UserBean> findByEmailAndPassword(String email, String password);
    Optional<UserBean> findByUsernameAndPassword(String username, String password);

}
