package tw.lab.Spring09.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/*
Authentication : 驗證
Authorization : 授權
*/
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) {
        httpSecurity.authorizeHttpRequests(
                auth -> auth.requestMatchers("/login", "/js/**", "/css/**")
                        .permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/main/**").hasAllRoles("ADMIN", "USER")
                        .anyRequest().authenticated())
                .formLogin(form -> form.loginPage("/login")
                        .usernameParameter("email")
                        .passwordParameter("passwd")
                        .loginProcessingUrl("/doLogin")
                        .defaultSuccessUrl("/main")
                        .failureUrl("/login")
                        .permitAll())
                .logout(logout -> logout.logoutUrl("/login")
                        .logoutSuccessUrl("/login")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID"))
                .exceptionHandling(e -> e.accessDeniedPage("/Page403"));

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) {
        return config.getAuthenticationManager();

    }
}
