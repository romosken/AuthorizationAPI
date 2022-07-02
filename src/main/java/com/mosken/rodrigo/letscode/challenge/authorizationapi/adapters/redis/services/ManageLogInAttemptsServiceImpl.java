//package com.mosken.rodrigo.letscode.challenge.authorizationapi.adapters.redis.services;
//
//import com.mosken.rodrigo.letscode.challenge.authorizationapi.adapters.exceptions.LogInException;
//import com.mosken.rodrigo.letscode.challenge.authorizationapi.adapters.redis.repositories.UserLogInAttemptsRepository;
//import com.mosken.rodrigo.letscode.challenge.authorizationapi.usecases.loginuser.port.FailedLogInAttempts;
//import com.mosken.rodrigo.letscode.challenge.authorizationapi.usecases.loginuser.port.IManageLogInAttemptsService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
//import org.springframework.stereotype.Service;
//
//
//@Service
//@RequiredArgsConstructor
//public class ManageLogInAttemptsServiceImpl implements IManageLogInAttemptsService {
//
//    private final UserLogInAttemptsRepository attemptsRepository;
//
//    private static final String LOG_IN_FAILED = "Username/email or password incorrect!";
//
//    @Override
//    public void insertFailedLogIn(String username) {
//        var userAttempts = attemptsRepository.findById(username).orElseThrow(() -> new LogInException(LOG_IN_FAILED));
//        userAttempts.setAttempts(Integer.sum(userAttempts.getAttempts(), 1));
//        attemptsRepository.save(userAttempts);
//    }
//
//    @Override
//    public FailedLogInAttempts getFailedLogInAttempts(String username) {
//        var userAttempts = attemptsRepository.findById(username).orElseThrow(() -> new LogInException(LOG_IN_FAILED));
//        return FailedLogInAttempts.builder()
//                .username(username)
//                .attempts(userAttempts.getAttempts())
//                .build();
//    }
//
//    @Override
//    public void deleteFailedAttempts(String username) {
//        try {
//            attemptsRepository.deleteById(username);
//        } catch (Exception e) {
//            throw new LogInException(LOG_IN_FAILED);
//        }
//    }
//}
