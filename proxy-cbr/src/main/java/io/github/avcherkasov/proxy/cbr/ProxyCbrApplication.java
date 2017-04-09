package io.github.avcherkasov.proxy.cbr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Proxy Cbr Application
 *
 * @author Anatoly Cherkasov
 */
@SpringBootApplication(scanBasePackages = {"io.github.avcherkasov.proxy.cbr"})
public class ProxyCbrApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(ProxyCbrApplication.class, args);
    }
}
