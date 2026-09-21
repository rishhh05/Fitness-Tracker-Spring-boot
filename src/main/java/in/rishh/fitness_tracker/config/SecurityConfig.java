package in.rishh.fitness_tracker.config;

import in.rishh.fitness_tracker.Services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder BCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(
        CustomUserDetailsService customUserDetailsService,
        PasswordEncoder passwordEncoder
    ){
        DaoAuthenticationProvider provider= new DaoAuthenticationProvider(customUserDetailsService);

        provider.setPasswordEncoder(passwordEncoder);

        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager (
            DaoAuthenticationProvider daoAuthenticationProvider // injecting our custom daoAuthenticationProvider bean
    ){
        return new ProviderManager(daoAuthenticationProvider); // set our custom DaoAuthenticationProvider in ProviderManager which will then handle username and password authentication type
    }

    public SecurityFilterChain securityFilterChain(
            HttpSecurity httpSec,
            JwtAuthenticationConverter jwtAuthenticationConverter
    ){

        httpSec.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth ->
                auth.requestMatchers("/auth/register","/auth/login").permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // for implementing jwt
                )
                .oauth2ResourceServer(oauth2 ->
                        oauth2.jwt(jwt ->
                                jwt.jwtAuthenticationConverter(jwtAuthenticationConverter)
                        )
                );
        return httpSec.build();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter (){
        JwtGrantedAuthoritiesConverter authoritiesConverter = new JwtGrantedAuthoritiesConverter();
        authoritiesConverter.setAuthoritiesClaimName("roles"); // or authorities as per our requirement
        authoritiesConverter.setAuthorityPrefix(""); // to avoid condtn such as SCOPE_ROLE_ADMIN

        JwtAuthenticationConverter authenticationConverter = new JwtAuthenticationConverter();
        authenticationConverter.setJwtGrantedAuthoritiesConverter(
                authoritiesConverter
        );

        return authenticationConverter;
    }

    @Bean
    public SecretKey secretKey(@Value("${jwt.secret}") String secret){
        byte [] decodedKey = Base64.getDecoder().decode(secret);

        return new SecretKeySpec(decodedKey, "HmacSHA256");
    }
    @Bean
    public JwtEncoder jwtEncoder(SecretKey secretKey){
        return NimbusJwtEncoder
                .withSecretKey(secretKey)
                .algorithm(MacAlgorithm.HS256)
                .build();
    }

    @Bean
    public JwtDecoder jwtDecoder(
            SecretKey secretKey,
            @Value("${jwt.issuer}") String issuer
    ){
      NimbusJwtDecoder decoder = NimbusJwtDecoder
              .withSecretKey(secretKey)
              .macAlgorithm(MacAlgorithm.HS256)
              .build();

      decoder.setJwtValidator(
              JwtValidators.createDefaultWithIssuer(issuer)
      );

      return decoder;
    }
}
