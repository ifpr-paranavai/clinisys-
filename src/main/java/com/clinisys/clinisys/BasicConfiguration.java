//package com.clinisys.clinisys;
//
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//@Configuration
//@EnableWebSecurity
//public class BasicConfiguration extends WebSecurityConfigurerAdapter{
//
//	@Autowired
//	private DataSource dataSource;
//	
//	@Bean
//	public BCryptPasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//	
////	@Override
////	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////		auth.jdbcAuthentication().dataSource(dataSource)
////			.usersByUsernameQuery("select email as username, senha as password, 1 as enable from funcionario where email=?")
////			.passwordEncoder(new BCryptPasswordEncoder());
////	}
////	
////
////	protected void configure(HttpSecurity http) throw Exception {
////		http.csrf().disable().authorizeRequests()
////		
////	}
//}
