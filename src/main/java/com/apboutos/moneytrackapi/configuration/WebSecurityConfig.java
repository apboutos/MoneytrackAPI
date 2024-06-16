package com.apboutos.moneytrackapi.configuration;

import com.apboutos.moneytrackapi.security.BasicAuthenticationFilter;
import com.apboutos.moneytrackapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final BasicAuthenticationFilter basicAuthenticationFilter;

    private static final String ENTRIES = "/api/v1/entries";
    private static final String CATEGORIES = "/api/v1/categories";
    private static final String USERS = "/api/v1/users";
    private static final String ACTUATOR = "/actuator/**";


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);

        http.authorizeHttpRequests(request -> {
            request.requestMatchers(ACTUATOR).permitAll();
            request.requestMatchers(HttpMethod.POST, USERS).permitAll();
            request.requestMatchers(HttpMethod.PATCH, USERS).permitAll();
            request.requestMatchers(HttpMethod.GET, CATEGORIES).hasAuthority("USER");
            request.requestMatchers(HttpMethod.POST, CATEGORIES).hasAuthority("USER");
            request.requestMatchers(HttpMethod.PUT, CATEGORIES).hasAuthority("USER");
            request.requestMatchers(HttpMethod.GET, ENTRIES).hasAuthority("USER");
            request.requestMatchers(HttpMethod.POST, ENTRIES).hasAuthority("USER");
            request.requestMatchers(HttpMethod.PUT, ENTRIES).hasAuthority("USER");
            request.requestMatchers(HttpMethod.DELETE, ENTRIES).hasAuthority("USER");
            request.anyRequest().authenticated();
        });
        http.sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(daoAuthenticationProvider())
                .addFilterBefore(basicAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(userService);
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }


}
