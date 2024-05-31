package com.tfm.tfmcore.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConflictException.class)
    public ErrorResponseException handleConflictException(ConflictException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, ex.getMessage());
        return new ErrorResponseException(HttpStatus.CONFLICT, problemDetail, null);
    }

    @ExceptionHandler(NotFoundException.class)
    public ErrorResponseException handleNotFoundException(NotFoundException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        return new ErrorResponseException(HttpStatus.NOT_FOUND, problemDetail, null);
    }

    @ExceptionHandler(BadRequestException.class)
    public ErrorResponseException handleBadRequestException(BadRequestException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        return new ErrorResponseException(HttpStatus.BAD_REQUEST, problemDetail, null);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ErrorResponseException handleUnauthorizedException(UnauthorizedException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, ex.getMessage());
        return new ErrorResponseException(HttpStatus.UNAUTHORIZED, problemDetail, null);
    }
}
