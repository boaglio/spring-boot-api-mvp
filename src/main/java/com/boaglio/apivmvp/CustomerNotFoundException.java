package com.boaglio.apivmvp;

public class CustomerNotFoundException extends RuntimeException {
    private final Long id;
    public CustomerNotFoundException(Long id) {
        super("Customer "+id+" not found!");
        this.id = id;
    }
    public Long getId() {
        return id;
    }
}
