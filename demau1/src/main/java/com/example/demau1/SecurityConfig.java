package com.example.demau1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    // chua lam
    @Bean
    public BCryptPasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public UserDetailsService userDetailsService(
//            AuthenticationManagerBuilder auth
    ) {
//        auth.inMemoryAuthentication().withUser();
        PasswordEncoder pe = getPasswordEncoder();
//        List<UserDetails> users = new ArrayList<>();

//        UserDetails user = User.withDefaultPasswordEncoder()
        UserDetails user = User.builder()
                .username("user")
                .password(pe.encode("abc"))
                .roles("USER")
                .build();
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(pe.encode("admin"))
//                .roles("ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(user,admin);
        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests(req -> req
                        .requestMatchers("/**").hasRole("USER")
                        .anyRequest()
                        .permitAll()
                )
                .httpBasic(Customizer.withDefaults());
//                .formLogin(login -> {
//                            try {
//                                login.init(httpSecurity);
//                            } catch (Exception e) {
//                                throw new RuntimeException(e);
//                            }
//                        }
//                );
//                .authorizeHttpRequests(req -> req
//                        .requestMatchers("/admin/**").hasRole("ADMIN")
//                        .anyRequest()
//                        .permitAll()
//                )
//                .formLogin(login -> login
////                        .loginPage("")
//                                .loginProcessingUrl("/login")
//                );
        return httpSecurity.build();
    }
}
