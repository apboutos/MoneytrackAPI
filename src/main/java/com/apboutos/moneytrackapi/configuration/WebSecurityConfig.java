package com.apboutos.moneytrackapi.configuration;

import com.apboutos.moneytrackapi.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private static final String ENTRIES = "/api/v1/entries";
    private static final String CATEGORIES = "/api/v1/categories";
    private static final String USERS = "/api/v1/users";
    private static final String ACTUATOR = "/actuator/**";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
        .authorizeRequests().antMatchers(HttpMethod.POST, USERS).permitAll()
                .antMatchers(ACTUATOR).permitAll()
                .antMatchers(HttpMethod.PATCH, USERS).permitAll()
                .antMatchers(HttpMethod.GET, CATEGORIES).hasAuthority("USER")
                .antMatchers(HttpMethod.POST, CATEGORIES).hasAuthority("USER")
                .antMatchers(HttpMethod.PUT, CATEGORIES).hasAuthority("USER")
                .antMatchers(HttpMethod.GET, ENTRIES).hasAuthority("USER")
                .antMatchers(HttpMethod.POST, ENTRIES).hasAuthority("USER")
                .antMatchers(HttpMethod.PUT, ENTRIES).hasAuthority("USER")
                .antMatchers(HttpMethod.DELETE, ENTRIES).hasAuthority("USER")
        .anyRequest().authenticated().and().httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(userService);
        return provider;
    }
}
