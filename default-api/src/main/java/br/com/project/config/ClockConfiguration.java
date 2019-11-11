package br.com.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.ClockProvider;
import java.time.Clock;

@Configuration
public class ClockConfiguration {

    @Bean
    public ClockProvider clockProvider() {
        return Clock::systemDefaultZone;
    }
}
