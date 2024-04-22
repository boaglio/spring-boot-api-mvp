package com.boaglio.apivmvp;

import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class ViaCEPAPIwithWebClientTest {

    private static final String CEP_PRACA_3_PODERES = "70100-000";
    private static final int TIMEOUT_EM_MS = 10_000;

    @Test
    public void testGetCEP() {

        var webClient = WebClient.create();
        var cepURL = "https://viacep.com.br/ws/"+CEP_PRACA_3_PODERES+"/json";
        var response = webClient
                .get()
                .uri(cepURL)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        System.out.println(response);
        assertTrue(Objects.requireNonNull(response).contains("Poderes") );
        assertTrue(response.contains("DF") );

    }

    @Test
    public void testGetCEPwithTimeout() {

        var cepURL = "https://viacep.com.br/ws/"+CEP_PRACA_3_PODERES+"/json";
        var webClient = WebClient.create();
        try {
            var response = webClient
                    .get()
                    .uri(cepURL)
                    .retrieve()
                    .bodyToMono(String.class)
                    .timeout(Duration.ofMillis(TIMEOUT_EM_MS))
                    .block();

            System.out.println(response);
            assertTrue(Objects.requireNonNull(response).contains("Poderes") );
            assertTrue(response.contains("DF") );

        } catch (IllegalArgumentException iae) {
            System.out.println("Erro na chamada: "+iae.getMessage());
            fail();
        }

    }

}
