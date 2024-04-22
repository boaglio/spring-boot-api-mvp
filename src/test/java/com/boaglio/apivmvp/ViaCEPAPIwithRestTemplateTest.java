package com.boaglio.apivmvp;

import org.junit.jupiter.api.Test;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class ViaCEPAPIwithRestTemplateTest {

    private static final String CEP_PRACA_3_PODERES = "70100-000";
    private static final int TIMEOUT_EM_MS = 10_000;

    @Test
    public void testGetCEP() {

        var restTemplate = new RestTemplate();
        var cepURL = "https://viacep.com.br/ws/"+CEP_PRACA_3_PODERES+"/json";
        var response = restTemplate.getForEntity(cepURL,String.class);
        System.out.println(response.getBody());
        assertTrue(Objects.requireNonNull(response.getBody()).contains("Poderes") );
        assertTrue(response.getBody().contains("DF") );

    }

    @Test
    public void testGetCEPwithTimeout() {

        var clientHttpRequestFactory  = new HttpComponentsClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(TIMEOUT_EM_MS); // tempo maximo para se conectar ao servidor
        clientHttpRequestFactory.setConnectionRequestTimeout(TIMEOUT_EM_MS); // tempo maximo para pegar uma nova conexao do pool

        var cepURL = "https://viacep.com.br/ws/"+CEP_PRACA_3_PODERES+"/json";

        try {
            var restTemplate = new RestTemplate(clientHttpRequestFactory);
            var response = restTemplate.getForEntity(cepURL,String.class);

            System.out.println(response.getBody());
            assertTrue(Objects.requireNonNull(response.getBody()).contains("Poderes"));
            assertTrue(response.getBody().contains("DF"));

        } catch (IllegalArgumentException iae) {
            System.out.println("Erro na chamada: "+iae.getMessage());
            fail();
        }

    }

}
