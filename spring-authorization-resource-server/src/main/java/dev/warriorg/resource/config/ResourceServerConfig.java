package dev.warriorg.resource.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class ResourceServerConfig {

   public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       http.authorizeHttpRequests(authorizeRequests -> authorizeRequests.anyRequest().authenticated())
               .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));
       return  http.build();
   }
}
