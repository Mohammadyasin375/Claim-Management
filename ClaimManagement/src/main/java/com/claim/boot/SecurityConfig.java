package com.claim.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.claim.boot.service.MyUserDetailsService;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	 
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.authenticationProvider(customProvider());
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		        .authorizeRequests()
				.antMatchers(HttpMethod.GET, "/api/member/get").hasAuthority("MEMBER")
				.antMatchers(HttpMethod.POST, "/api/member/add/{id}").permitAll()
				.antMatchers(HttpMethod.PUT, "/api/member/update").hasAuthority("MEMBER")
				.antMatchers(HttpMethod.GET, "/api/member/{id}").hasAuthority("ADMIN")
				.antMatchers(HttpMethod.PUT, "/api/member/changePlan/{oldPanId}/{newPlanId}").hasAuthority("MEMBER")
				.antMatchers(HttpMethod.GET, "/api/member/getAll").authenticated()
				
				.antMatchers(HttpMethod.GET, "/api/claim/add/{id}").hasAuthority("MEMBER")
				.antMatchers(HttpMethod.POST, "/api/claim/delete/{id}").hasAuthority("MEMBER")
				.antMatchers(HttpMethod.PUT, "/api/claim/update/{id}").hasAuthority("MEMBER")
				.antMatchers(HttpMethod.GET, "/api/claim/getAllByUsername").hasAuthority("MEMBER")
				.antMatchers(HttpMethod.GET, "/api/claim/getAll").authenticated()
				.antMatchers(HttpMethod.GET, "/api/claim/{id}").hasAuthority("ADMIN")
				
				.antMatchers(HttpMethod.GET, "/api/plan/{id}").authenticated()
				.antMatchers(HttpMethod.GET, "/api/plan/getAll").authenticated()
				.antMatchers(HttpMethod.GET, "/api/plan/add").hasAuthority("ADMIN")
				.antMatchers(HttpMethod.GET, "/api/plan/delete/{id}").hasAuthority("ADMIN")
				
				.antMatchers(HttpMethod.GET, "/api/admin/add").permitAll()
				.antMatchers(HttpMethod.GET, "/api/approval/{status}/{id}").hasAuthority("ADMIN")
		
				.antMatchers(HttpMethod.GET, "/api/user/login").authenticated()
				.anyRequest().permitAll()
				.and()
			    .httpBasic()
				.and()
				.csrf().disable();
				
	}

	@Bean 
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	DaoAuthenticationProvider customProvider(){
		DaoAuthenticationProvider dao = new DaoAuthenticationProvider(); 
		//password Encoder info 
		dao.setPasswordEncoder(getPasswordEncoder());
		//give DB info
		dao.setUserDetailsService(myUserDetailsService);
		
		
		return dao;
	}
}
