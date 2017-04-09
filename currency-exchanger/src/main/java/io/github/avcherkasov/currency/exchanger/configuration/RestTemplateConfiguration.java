package io.github.avcherkasov.currency.exchanger.configuration;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

/**
 * This is the configuration class for configuring the {@link RestTemplate RestTemplate}
 *
 * @author Anatoly Cherkasov
 * @see SSLContext
 * @see CloseableHttpClient
 * @see HttpComponentsClientHttpRequestFactory
 * @see RestTemplate
 */
@Configuration
public class RestTemplateConfiguration {

    private SSLContext sslContext() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        return new SSLContextBuilder()
                .loadTrustMaterial((x509Certificates, s) -> true)
                .build();
    }

    private CloseableHttpClient httpClient() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        return HttpClients.custom()
                .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                .setSSLContext(sslContext())
                .build();
    }

    private HttpComponentsClientHttpRequestFactory requestFactory() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient());
        return requestFactory;
    }

    @Bean
    RestTemplate restTemplate() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        return new RestTemplate(requestFactory());
    }

}
