package dsd.microservices.market_gateway.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@ConfigurationProperties(prefix = "white")
@Getter
@Setter
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private String[] paths;

    @Bean
    public SecurityFilterChain configureSecurity(HttpSecurity http, SecurityConfig config) throws Exception{
        return http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(config.getPaths()).permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
                .build();
    }

}
