package io.github.avcherkasov.currency.exchanger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Currency Exchanger Application
 *
 * @author Anatoly Cherkasov
 */
@SpringBootApplication(scanBasePackages = "io.github.avcherkasov.currency.exchanger")
public class CurrencyExchangerApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(CurrencyExchangerApplication.class, args);
    }
}
