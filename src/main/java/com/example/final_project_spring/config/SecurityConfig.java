package com.example.final_project_spring.config;

import com.example.final_project_spring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserService userService;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable()
            .authorizeRequests()
            .antMatchers( HttpMethod.POST,"/api/v1/user/register/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .logout()
            .logoutRequestMatcher(new RegexRequestMatcher("/api/v1/user/log-out","POST"))
            .deleteCookies("JSESSIONID")
            .invalidateHttpSession(true)
            .and()
            .httpBasic();
    }
}

