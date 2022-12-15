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
		http.authorizeRequests()

				.antMatchers(HttpMethod.GET, "/api/member/details").hasAuthority("MEMBER")

				.antMatchers(HttpMethod.POST, "/api/claim/add/{pId}").hasAuthority("MEMBER")
				.antMatchers(HttpMethod.POST, "/api/claim/upload").hasAuthority("MEMBER")

				.antMatchers(HttpMethod.GET, "/api/claim/getAllByUsername").hasAuthority("MEMBER")

				.antMatchers(HttpMethod.POST, "/api/plan/add").hasAuthority("MEMBER")
				.antMatchers(HttpMethod.GET, "/api/plan/all").hasAuthority("MEMBER")
				

				.antMatchers(HttpMethod.PUT, "/api/admin/approval/{status}/{cId}").hasAuthority("ADMIN")
				.antMatchers(HttpMethod.GET, "/api/member/all").hasAuthority("ADMIN")

				.antMatchers(HttpMethod.GET, "/api/user/login").authenticated().anyRequest().permitAll().and()
				.httpBasic().and().csrf().disable();

	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	DaoAuthenticationProvider customProvider() {
		DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
		// password Encoder info
		dao.setPasswordEncoder(getPasswordEncoder());
		// give DB info
		dao.setUserDetailsService(myUserDetailsService);

		return dao;
	}
}
