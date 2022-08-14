package com.frankmoley.lil.adminweb.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//                .authorizeRequests()
////                .antMatchers("/", "/home").permitAll()
//                .antMatchers("/", "/home").permitAll()
//                .antMatchers("/customers/**").hasRole("USER")
//                .antMatchers("/orders").hasRole("ADMIN")
//                .anyRequest().authenticated()
//                .and()
//                .httpBasic();
              
        	  .authorizeRequests()
              .antMatchers("/", "/home").permitAll()
              .antMatchers("/customers/**").hasRole("USER")
              .antMatchers("/orders").hasRole("ADMIN")
              .anyRequest().authenticated()
              .and()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login?error")
                .and()
                .logout()
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/login?logout")
                .permitAll();
    }

    @Bean
    public GrantedAuthoritiesMapper authoritiesMapper() { 
    	SimpleAuthorityMapper authorityMapper = new SimpleAuthorityMapper();
    	authorityMapper.setConvertToUpperCase(true);
    	return authorityMapper;
    }
//    @Bean
//    @Override
//    public UserDetailsService userDetailsService() {
//        UserDetails user =
//                User.withDefaultPasswordEncoder()
//                        .username("user")
//                        .password("password")
//                        .roles("USER")
//                        .build();
//        return new InMemoryUserDetailsManager(user);
//    }
    
    @Bean
    public UserDetailsService users(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

//    @Bean 
//    public static PasswordEncoder getPasswordEncoder() { 
//    	return NoOpPasswordEncoder.getInstance();
//    	return 
//    }
}
