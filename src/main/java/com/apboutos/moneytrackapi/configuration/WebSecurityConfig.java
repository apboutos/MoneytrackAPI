package com.apboutos.moneytrackapi.configuration;

import com.apboutos.moneytrackapi.security.BasicAuthenticationFilter;
import com.apboutos.moneytrackapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
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
        http.csrf().disable()
        .authorizeHttpRequests()
                .requestMatchers(ACTUATOR).permitAll()
                .requestMatchers(HttpMethod.POST, USERS).permitAll()
                .requestMatchers(HttpMethod.PATCH, USERS).permitAll()
                .requestMatchers(HttpMethod.GET, CATEGORIES).hasAuthority("USER")
                .requestMatchers(HttpMethod.POST, CATEGORIES).hasAuthority("USER")
                .requestMatchers(HttpMethod.PUT, CATEGORIES).hasAuthority("USER")
                .requestMatchers(HttpMethod.GET, ENTRIES).hasAuthority("USER")
                .requestMatchers(HttpMethod.POST, ENTRIES).hasAuthority("USER")
                .requestMatchers(HttpMethod.PUT, ENTRIES).hasAuthority("USER")
                .requestMatchers(HttpMethod.DELETE, ENTRIES).hasAuthority("USER")
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(daoAuthenticationProvider())
                .addFilterBefore(basicAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .httpBasic();

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
