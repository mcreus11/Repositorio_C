package com.mx.CelularApp.SecurityConfig;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

   
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity
			    .csrf(csrf -> csrf.disable())
			    .authorizeHttpRequests(auth -> auth
			        // rutas publicas
			        .requestMatchers("/api/Cel/listar").permitAll()
			        // rutas privadas acceso un rol especifico
			        .requestMatchers("/api/Cel/guardar").hasRole("ADMIN")
			        // rutas provadas acceso con permiso en especifico
			        .requestMatchers("/api/Cel/buscar").hasAuthority("READ")
			        // configuracion para que las rutas que no esten definidas en el filter,
			        // por defecto solo accede un usuario authenticated
			        .anyRequest().authenticated()
			    )
			    .httpBasic(Customizer.withDefaults())
			    .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			    .build();
	}
    
	@SuppressWarnings("unchecked")
	@Bean 
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

	@Bean
	public UserDetailsService userDetailsService(PasswordEncoder pEncoder) {
		List<UserDetails> userList = new ArrayList<>();

		userList.add(User.withUsername("Aline")
		    .password(pEncoder.encode("1234"))
		    .authorities("ROLE_ADMIN", "READ", "CREATE")
		    .build());
		userList.add(User.withUsername("Maria")
		    .password(pEncoder.encode("2345"))
		    .authorities("ROLE_USER", "READ")
		    .build());
		return new InMemoryUserDetailsManager(userList);  
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
}