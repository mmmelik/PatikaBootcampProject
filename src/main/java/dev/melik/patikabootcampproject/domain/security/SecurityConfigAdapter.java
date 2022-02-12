package dev.melik.patikabootcampproject.domain.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfigAdapter{

    /**
     * Security config for REST API
     */
    @Order(1)
    @Configuration
    @RequiredArgsConstructor
    private class RestSecurityConfigAdapter extends WebSecurityConfigurerAdapter{

        private final UserDetailsService userDetailsService;


        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable().antMatcher("/api/**").authorizeRequests()
                    .antMatchers("/api/login","/api/register").permitAll()
                    .and().formLogin().disable()
                    .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                    .addFilter(new JwtAuthorizationFilter(authenticationManager()))
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        }
    }

    /**
     * Security Config for request from Web Application
     */
    @Configuration
    @RequiredArgsConstructor
    private class WebSecurityConfigAdapter extends WebSecurityConfigurerAdapter{

        private final UserDetailsService userDetailsService;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable().antMatcher("/**").authorizeRequests()
                    .antMatchers("/","/login","/register").permitAll()
                    .anyRequest().authenticated()
                    .and().formLogin().loginPage("/login").defaultSuccessUrl("/home",true);

        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        }

    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
