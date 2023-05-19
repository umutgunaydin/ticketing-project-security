package com.company.config;

import com.company.service.SecurityService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {

    private final SecurityService securityService;

    public SecurityConfig(SecurityService securityService) {
        this.securityService = securityService;
    }

// manual user creation ***
//    @Bean
//    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
//
//        List<UserDetails> userList = new ArrayList<>();
//
//        userList.add(new User("mike", encoder.encode("password"), Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"))));
//        userList.add(new User("ozzy", encoder.encode("password"), Arrays.asList(new SimpleGrantedAuthority("ROLE_MANAGER"))));
//
//        return new InMemoryUserDetailsManager(userList);
//
//    }
//

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeRequests()
//                .antMatchers("/user/**").hasRole("ADMIN") //means ROLE_ADMIN -- uses prefix of ROLE_
                .antMatchers("/user/**").hasAuthority("Admin")
                .antMatchers("/project/**").hasAuthority("Manager")
                .antMatchers("/task/employee/**").hasAuthority("Employee")
                .antMatchers("/task/**").hasAuthority("Manager")
//                .antMatchers("/task/**").hasAuthority("ROLE_EMPLOYEE")
                .antMatchers(
                        "/",
                        "/login",
                        "/fragments/**",
                        "/assets/**",
                        "/images/**"
                ).permitAll()
                .anyRequest().authenticated()
                .and()
//                .httpBasic(
                .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/welcome")
                    .failureUrl("/login?error=true")
                    .permitAll()
                .and()
                .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/login")
                .and()
                .rememberMe()
                    .tokenValiditySeconds(120)
                    .key("company")
                    .userDetailsService(securityService)
                .and()
                .build();
    }

}
