package it.ktpm.keva.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class JpaConfiguration {
    @Bean
    public AuditorAware<String> auditorAware(){
        return new AutitorAwareImpl();
    }

    static class AutitorAwareImpl implements AuditorAware<String> {

        @Override
        public Optional<String> getCurrentAuditor() {
            return Optional.of("Nexius358");
        }
    }
}
