package com.mosken.rodrigo.letscode.challenge.authorizationapi.adapters.rest.errorhandler.json;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ApiErrorResponse {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyy hh:mm:ss")
    private final Date timestamp;
    private int code;
    private String status;
    private String message;
    private String stackTrace;

    public ApiErrorResponse() {
        timestamp = new Date();
    }

    public ApiErrorResponse(HttpStatus httpStatus, String message) {
        this();
        this.code = httpStatus.value();
        this.status = httpStatus.name();
        this.message = message;
    }

}
