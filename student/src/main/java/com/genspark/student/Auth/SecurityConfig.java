package com.genspark.student.Auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    MyUserDetailsService myUserDetailsService;
    @Autowired
    private JwtTokenAuthorizationOncePerRequestFilter TokenAuthorizationOncePerRequestFilter;

    @Override
   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // auth.inMemoryAuthentication()
        //         .withUser("A").password("pass1").roles("STUDENT")
        //         .and()
        //         .withUser("B").password("pass2").roles("ADMIN")
        //         .and()
        //         .withUser("C").password("pass2").roles("INSTRUCTOR")
        //         .and()
        //         .passwordEncoder(NoOpPasswordEncoder.getInstance());
        auth.userDetailsService(myUserDetailsService).passwordEncoder(NoOpPasswordEncoder.getInstance());
   }
   
   @Override
   protected void configure(HttpSecurity http) throws Exception {
       http
               .authorizeRequests()
               .antMatchers("/authenticate").permitAll().antMatchers("/student/**").hasRole("STUDENT")
               .antMatchers("/instructor/**").hasRole("INSTRUCTOR").antMatchers("/admin/**").hasRole("ADMIN")
               .anyRequest().authenticated().and().cors().and()
               .csrf()
               .disable();
        http.addFilterBefore(TokenAuthorizationOncePerRequestFilter, UsernamePasswordAuthenticationFilter.class);
       http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
   }

   @Bean
   @Override
   public AuthenticationManager authenticationManagerBean() throws Exception {
       return super.authenticationManagerBean();
   }
}
