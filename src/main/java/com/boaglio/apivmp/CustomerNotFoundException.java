package com.boaglio.apivmp;

public class CustomerNotFoundException extends RuntimeException {
    private Long id;
    public CustomerNotFoundException(Long id) {
        super("Customer "+id+" not found!");
        this.id = id;
    }
    public Long getId() {
        return id;
    }
}