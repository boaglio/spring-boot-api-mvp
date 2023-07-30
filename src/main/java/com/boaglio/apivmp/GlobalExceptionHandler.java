package com.boaglio.apivmp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    ProblemDetail   handleCustomerNotFoundException(CustomerNotFoundException ex) {

        var problemDetails = ProblemDetail .forStatusAndDetail(HttpStatus.NOT_FOUND,ex.getLocalizedMessage());

        problemDetails.setType(URI.create( "http://localhost:8080/errors/customer-not-found"));
        problemDetails.setTitle("Customer Not Found");
        // Adding non-standard property
        problemDetails.setProperty("customerId", ex.getId());
        return problemDetails;
    }


    // Handle any other exception too.
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    RestErrorResponse handleException(Exception ex) {
        return new RestErrorResponse( 
            HttpStatus.BAD_REQUEST.value(), 
            ex.getMessage(), 
            LocalDateTime.now());
    }

}
