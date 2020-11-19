package hh.swd20.harjoitustyo.saliapp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import hh.swd20.harjoitustyo.saliapp.webcontroller.UserDetailServiceImpl;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailServiceImpl userDetailsService;
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
             // h2-console näkyviin spring securityn ollessa päällä. EI TUOTANTOVERSIOON!
			.authorizeRequests()
				.antMatchers("/h2-console/**").permitAll()
				.antMatchers("/css/**").permitAll()
				.antMatchers("/delete/**").hasAuthority("ADMIN")
				.and()
				.csrf().ignoringAntMatchers("/h2-console/**")
				.and()
				.headers().frameOptions().sameOrigin()
				.and()
				.authorizeRequests() 
            	.anyRequest().authenticated()
                .and()
				
            .formLogin()
                // .loginPage() remove .loginPage("/login") and delete login.html to use spring's default login page view
                .defaultSuccessUrl("/exerciselist", true)
                .permitAll()
                .and()
            .logout()
                .permitAll();
    }

  
    /* creation of in-memory users
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        List<UserDetails> users = new ArrayList();
    	UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("user")
                .roles("USER")
                .build();

    	users.add(user);
    	
    	user = User.withDefaultPasswordEncoder()
                   .username("admin")
                   .password("admin")
                   .roles("USER", "ADMIN")
                   .build();
    	
    	users.add(user);
    	
        return new InMemoryUserDetailsManager(users);
    }
    */
    
    // adding users to database
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
    
}