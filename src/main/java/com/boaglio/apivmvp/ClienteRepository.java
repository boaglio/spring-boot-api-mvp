package com.boaglio.apivmvp;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository  extends JpaRepository<Cliente,Long> {
}
