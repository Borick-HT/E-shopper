package poly.thong.entity;

import java.util.NoSuchElementException;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import poly.thong.service.AccountService;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
	@Autowired
	AccountService accountService;
	@Autowired
	BCryptPasswordEncoder pe;
		
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(username->{
			try {
				Account user=accountService.findById(username);
				String password=pe.encode(user.getPassword());
				String[] roles=user.getAuthorities().stream()
						.map(er->er.getRole().getId())
						.collect(Collectors.toList()).toArray(new String[0]);
				return User.withUsername(username).password(password).roles(roles).build();
				
				
			} catch (NoSuchElementException e) {
				throw new UsernameNotFoundException(username+"not found");
			}
		});
		// TODO Auto-generated method stub
	}
	
	 @Override
	    protected void configure(HttpSecurity http) throws Exception
	    {
		 
	     http.httpBasic().disable();
	     http.csrf().disable();
         http.authorizeRequests()
         .antMatchers("/order/**").authenticated()
         .antMatchers("/admin/**").hasAnyRole("STAF","DIRE")
         .antMatchers("/rest/authorities").hasRole("DIRE")
         .anyRequest().permitAll();
         
         http.formLogin()
         .loginPage("/sercurity/login/form")
         .loginProcessingUrl("/sercurity/login")
         .defaultSuccessUrl("/sercurity/login/success",false)
         .failureUrl("/sercurity/login/error");
         
         http.rememberMe()
         .tokenValiditySeconds(86400);
         
         http.exceptionHandling()
         .accessDeniedPage("/sercurity/unauthoried");
         
         http.logout()
         .logoutUrl("/sercurity/logoff")
         .logoutSuccessUrl("/sercurity/logoff/success");
	    }
	 @Bean
	  public BCryptPasswordEncoder getPasswordEncoder() {
		 return new BCryptPasswordEncoder();
	 }
	 
	@Override
	public void configure(org.springframework.security.config.annotation.web.builders.WebSecurity web)
			throws Exception {
		web.ignoring().antMatchers(HttpMethod.OPTIONS,"/**");
	}
}