package com.memsource.memsourceapp.security;

import com.memsource.memsourceapp.http_client.ProjectsHolderClient;
import com.memsource.memsourceapp.mapper.UserMapper;
import com.memsource.memsourceapp.security.filter.CustomAuthenticationFilter;
import com.memsource.memsourceapp.security.filter.ExceptionHandlerFilter;
import com.memsource.memsourceapp.security.filter.MemsourceAuthenticationFilter;
import com.memsource.memsourceapp.util.JsonWebTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final ProjectsHolderClient projectsHolderClient;

    private final ApplicationEventPublisher applicationEventPublisher;

    private final JsonWebTokenUtils jsonWebTokenUtils;

    private final UserMapper userMapper;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().anyRequest().permitAll();
        http.addFilterBefore(new ExceptionHandlerFilter(), CustomAuthenticationFilter.class)
                .addFilterBefore(
                        new MemsourceAuthenticationFilter(projectsHolderClient,
                                applicationEventPublisher,
                                userMapper),
                        CustomAuthenticationFilter.class)
                .addFilterAfter(new CustomAuthenticationFilter(authenticationManagerBean(),
                                jsonWebTokenUtils,
                                userMapper),
                        MemsourceAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }


}
