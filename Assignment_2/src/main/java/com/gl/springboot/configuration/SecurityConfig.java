package com.gl.springboot.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Autowired
//    private UserDetailsService userDetailsService;
    
    @Autowired
	DataSource dataSource;

    @Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource).withDefaultSchema()
				.withUser(User.withUsername("admin").password(getpasswordEncoder().encode("password")).roles("ADMIN"))
				.withUser(User.withUsername("user").password(getpasswordEncoder().encode("pwd")).roles("USER"));

	}
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests()
            .antMatchers("/api/roles/**").hasRole("ADMIN")
            .antMatchers("/api/users/**").hasRole("ADMIN")
            .antMatchers("/api/employees/**").hasRole("ADMIN")
            .and().httpBasic();
    }

    

    @Bean
    public PasswordEncoder getpasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
