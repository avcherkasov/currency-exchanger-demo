package io.github.avcherkasov.proxy.nbrb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Proxy Nbrb Application
 *
 * @author Anatoly Cherkasov
 */
@SpringBootApplication(scanBasePackages = {"io.github.avcherkasov.proxy.nbrb"})
public class ProxyNbrbApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(ProxyNbrbApplication.class, args);
    }
}
