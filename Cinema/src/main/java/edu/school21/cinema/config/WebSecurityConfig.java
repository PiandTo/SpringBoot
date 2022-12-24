package edu.school21.cinema.config;

import edu.school21.cinema.filters.CustomFailureHandler;
import edu.school21.cinema.filters.CustomSuccessHandler;
import edu.school21.cinema.filters.CustomeActivateAccount;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@AllArgsConstructor
public class WebSecurityConfig{
	private final CustomSuccessHandler customSuccessHandler;
	private final CustomFailureHandler customFailureHandler;
	private final CustomeActivateAccount customeActivateAccount;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.authorizeRequests()
				.mvcMatchers("/", "/signUp", "/signIn", "/logout", "/confirm/**", "/ws").permitAll()
				.mvcMatchers(HttpMethod.POST, "/profile").permitAll()
				.mvcMatchers(HttpMethod.GET, "/profile").hasAnyRole("USER", "ADMIN")
				.mvcMatchers("/session", "/user", "/films", "/uploadAvatar", "/avatars").hasAnyRole("USER", "ADMIN")
				.mvcMatchers("/admin/**", "/admin").hasRole("ADMIN")
				.anyRequest().authenticated();
		httpSecurity.formLogin()
				.loginPage("/login").permitAll()
//				.loginProcessingUrl("/auth")
//				.defaultSuccessUrl("/user", false)
				.usernameParameter("email")
				.passwordParameter("password")
				.failureHandler(customFailureHandler)
				.successHandler(customSuccessHandler);
		httpSecurity.logout()
				.logoutSuccessUrl("/")
				.invalidateHttpSession(true);
		httpSecurity.rememberMe();
		httpSecurity.headers().contentTypeOptions().disable();
		httpSecurity.addFilterBefore(customeActivateAccount, UsernamePasswordAuthenticationFilter.class);
		httpSecurity.exceptionHandling().accessDeniedPage("/");
		return httpSecurity.build();
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().antMatchers("/images/**","/favicon.ico","/error", "/styles.css","/js/**");
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
