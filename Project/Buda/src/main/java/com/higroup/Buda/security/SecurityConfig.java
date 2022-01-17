package com.higroup.Buda.security;

import com.higroup.Buda.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/*
    ensures that only authenticated users can see the secret thing
*/
@Configuration
@EnableWebSecurity // enable Spring Security’s web security support and provide the Spring MVC integration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Autowired
	private UserService jwtUserDetailsService;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// configure AuthenticationManager so that it knows from where to load
		// user for matching credentials
		// Use BCryptPasswordEncoder
		auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

    @Override // defines which URL paths should be secured and which should not
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
		// make sure we use stateless session; session won't be used to
		// store user's state.
		http.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		// user config
		this.UserConfig(http);
		// role config
		this.StaffConfig(http);
		// staff config
		// staff note config
		this.StaffNoteConfig(http);
		// salary log
		this.SalaryLogConfig(http);
		// product config
		this.ProductConfig(http);
		// plan config
		this.PlanConfig(http);
		// all other requests need to be authenticated
		http.authorizeRequests().anyRequest().authenticated();
		// http.authorizeRequests().anyRequest().authen
		// Add a filter to validate the tokens with every request
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

	private void UserConfig(HttpSecurity http) throws Exception{
		// dont authenticate this particular request
		http.authorizeRequests().antMatchers("/api/user/login", "/api/user/register/**").permitAll();
		http.authorizeRequests().antMatchers("/api/user/login/google").permitAll();
		http.authorizeRequests().antMatchers("/api/user/refresh-token").permitAll();
		http.authorizeRequests().antMatchers("/api/user/password/forgot/**").permitAll();
		// require ROLE USER to make get request for user 
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/user/**").hasAnyAuthority("USER");
		// Admin can do something
		http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/user/id/**").hasAnyAuthority("ADMIN");
		
	}
    
	private void PlanConfig(HttpSecurity http) throws Exception{
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/plan/**").permitAll();
	}

	private void StaffConfig(HttpSecurity http) throws Exception{
		// dont authenticate this particular request
		http.authorizeRequests().antMatchers("/api/staff/login").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/staff/id/**").hasAnyAuthority("ADMIN", "USER");
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/staff/userID/all").hasAnyAuthority("USER");
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/staff/register").hasAnyAuthority("USER");
		http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/staff/id/**").hasAnyAuthority("USER");
		
	}

	private void StaffNoteConfig(HttpSecurity http) throws Exception{
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/staff-note/register").hasAnyAuthority("USER", "STAFF");
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/staff-note/userID/{userID}/all", 
															 "/api/staff-note/staffID/{staffID}/all").hasAnyAuthority("USER", "STAFF");
		http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/staff-note/noteID/**").hasAnyAuthority("USER", "STAFF");
		http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/staff-note/noteID/**").hasAnyAuthority("USER", "STAFF");
	}

	private void SalaryLogConfig(HttpSecurity http) throws Exception{
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/salary-log/userID/**", "/api/salary-log/staffID/**").hasAnyAuthority("USER");
	}

	private void ProductConfig(HttpSecurity http) throws Exception{
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/product/productID/**", "/api/product/all", "/hidden/all", 
															 "/api/product//hidden/all", "/api/product//group/**").hasAnyAuthority("USER", "STAFF");
		http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/product/productID/**").hasAnyAuthority("USER");
		http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/product/productID/**").hasAnyAuthority("USER");
	}
}
