package io.github.avcherkasov.currency.exchanger.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * This is the configuration class for configuring the {@link ObjectMapper ObjectMapper}
 *
 * @author Anatoly Cherkasov
 * @see ObjectMapper
 */
@Configuration
public class ObjectMapperConfiguration {

    @Bean
    ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

}
