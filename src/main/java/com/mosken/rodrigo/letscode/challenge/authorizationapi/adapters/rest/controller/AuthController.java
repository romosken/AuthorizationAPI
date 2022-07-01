package com.mosken.rodrigo.letscode.challenge.authorizationapi.adapters.rest.controller;


import com.mosken.rodrigo.letscode.challenge.authorizationapi.usecases.loginuser.ILogIn;
import com.mosken.rodrigo.letscode.challenge.authorizationapi.usecases.loginuser.LogInRequest;
import com.mosken.rodrigo.letscode.challenge.authorizationapi.usecases.loginuser.LogInResponse;
import com.mosken.rodrigo.letscode.challenge.authorizationapi.usecases.parsetoken.IParseToken;
import com.mosken.rodrigo.letscode.challenge.authorizationapi.usecases.parsetoken.ParseTokenRequest;
import com.mosken.rodrigo.letscode.challenge.authorizationapi.usecases.parsetoken.ParseTokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authorization/v1")
@RequiredArgsConstructor
public class AuthController {

    private final ILogIn iLogIn;
    private final IParseToken iParseToken;

    @PostMapping("/login")
    public ResponseEntity<LogInResponse> logInUser(
            @RequestHeader(required = false) String username,
            @RequestHeader(required = false) String email,
            @RequestHeader String password
    ) {
        var request = LogInRequest.builder()
                .username(username)
                .email(email)
                .password(password)
                .build();
        return ResponseEntity.ok(iLogIn.logIn(request));
    }

    @GetMapping("/token/parse")
    public ResponseEntity<ParseTokenResponse> parseToken(
            @RequestHeader String token
    ) {
        var request = ParseTokenRequest.builder().token(token).build();
        return ResponseEntity.ok(iParseToken.parseToken(request));
    }

}


