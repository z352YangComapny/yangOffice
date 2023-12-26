package com.yangworld.app.config;

import com.yangworld.app.common.jwt.JwtAuthenticationFilter;
import com.yangworld.app.common.jwt.JwtAuthorizationFilter;
import com.yangworld.app.common.redis.service.RedisService;
import com.yangworld.app.common.redis.service.RefreshTokenService;
import com.yangworld.app.domain.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private RedisService redisService;


	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf((csrf) -> csrf.disable());
        http.cors().configurationSource(corsConfigurationSource());
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.formLogin().disable()
				.httpBasic().disable()
				.apply(new MyHttpConfigure())
				.and()
				.authorizeHttpRequests((auth)->{
					auth.antMatchers("/user").hasAnyRole("USER","ADMIN")
							.antMatchers("/admin").hasAnyRole("ADMIN")
							.anyRequest().permitAll();
				});

        return http.build();
    }
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.addAllowedMethod("*");
		corsConfiguration.addAllowedOrigin("http://localhost:3000");
		corsConfiguration.addAllowedOrigin("http://localhost:7070");
		corsConfiguration.addAllowedHeader("*");
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.addExposedHeader("Authorization");
		source.registerCorsConfiguration("/**", corsConfiguration);
		return source;
	}

	public class MyHttpConfigure extends AbstractHttpConfigurer<MyHttpConfigure , HttpSecurity> {
		@Override
		public void configure(HttpSecurity builder) throws Exception {
			AuthenticationManager authenticationManager =
					builder.getSharedObject(AuthenticationManager.class);
			builder
					.addFilter(new JwtAuthenticationFilter(authenticationManager, redisService , memberRepository , passwordEncoder()))
					.addFilter(new JwtAuthorizationFilter(authenticationManager, memberRepository, redisService));
			super.configure(builder);
		}
	}
}
