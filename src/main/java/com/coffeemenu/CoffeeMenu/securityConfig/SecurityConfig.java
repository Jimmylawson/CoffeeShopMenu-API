package com.coffeemenu.CoffeeMenu.securityConfig;


import com.coffeemenu.CoffeeMenu.filter.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;


@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private CoffeeMenuDetailService coffeeMenuDetailService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthFilter jwtAuthFilter) throws Exception {
            http
                    .csrf(c->c.disable())
                    .authorizeHttpRequests(request->request
                            .requestMatchers(HttpMethod.GET, "/api/v1/menu-items/**", "/api/v1/users/**"
                                    ).permitAll()
                            .requestMatchers(HttpMethod.POST, "/api/v1/user","/api/v1/login").permitAll()
                            .requestMatchers("/api/v1/menu-items/**","/api/v1/user-admin"
                            ).hasAuthority("ADMIN")
                            .anyRequest().authenticated()

                    )
                    .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                    /// To tell spring not to generate the JSESSIONID
                    .sessionManagement(sessionConfig->sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .formLogin(Customizer.withDefaults())
                    .httpBasic(Customizer.withDefaults());
            return http.build();
    }


////    @Bean//    public CompromisedPasswordChecker compromisedPasswordChecker(){
//    ////
// return new HaveIBeenPwnedRestApiPasswordChecker();
////    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /// Manually initiate the authentication process
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{

        return authenticationConfiguration.getAuthenticationManager();

    }

}
