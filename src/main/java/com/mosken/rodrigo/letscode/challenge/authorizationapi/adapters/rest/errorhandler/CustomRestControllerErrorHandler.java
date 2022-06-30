package com.mosken.rodrigo.letscode.challenge.authorizationapi.adapters.rest.errorhandler;

import com.mosken.rodrigo.letscode.challenge.authorizationapi.adapters.exceptions.InvalidResourceException;
import com.mosken.rodrigo.letscode.challenge.authorizationapi.adapters.exceptions.LogInException;
import com.mosken.rodrigo.letscode.challenge.authorizationapi.adapters.rest.errorhandler.json.ApiErrorResponse;
import com.mosken.rodrigo.letscode.challenge.authorizationapi.entities.exceptions.EntityException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

@ResponseBody
@ControllerAdvice
@RequiredArgsConstructor
public class CustomRestControllerErrorHandler {


    @ExceptionHandler(value = {LogInException.class})
    protected ResponseEntity<ApiErrorResponse> handleNotFoundException(Exception e) {
        var status = HttpStatus.FORBIDDEN;
        var msg = retrieveMessage(e);
        return buildResponseEntity(status, msg);
    }

    private String retrieveMessage(Exception e) {
        return Objects.isNull(e.getCause()) ? e.getLocalizedMessage() : e.getCause().getMessage();
    }

    private ResponseEntity<ApiErrorResponse> buildResponseEntity(HttpStatus status, String msg) {
        return new ResponseEntity<>(new ApiErrorResponse(status, msg), status);
    }


}
