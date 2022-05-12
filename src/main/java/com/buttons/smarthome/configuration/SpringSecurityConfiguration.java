package com.buttons.smarthome.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/admin/**").permitAll()
                .and()
                .formLogin()
                .and()
                .httpBasic()
                .and()
                .cors()
                .and()
                .csrf()
                .disable();
    }


    @Bean
    public PasswordEncoder encoder()
    {
        return new BCryptPasswordEncoder();
    }


    @Override
    @Bean
    public UserDetailsService userDetailsService() {

        User.UserBuilder users = User.builder();

        var admin = users.username("admin")
                .password(encoder().encode("admin"))
                .roles("ADMIN")
                .build();

        var support = users.username("support")
                .password(encoder().encode("support"))
                .roles("SUPPORT")
                .build();
        return new InMemoryUserDetailsManager(admin, support);
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.userDetailsService(userDetailsService());
    }

}
