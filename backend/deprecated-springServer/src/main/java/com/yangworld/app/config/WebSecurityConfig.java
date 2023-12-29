package com.yangworld.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import com.yangworld.app.config.auth.PrincipalDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@SuppressWarnings("deprecation")
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PrincipalDetailsService principalService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 정적파일은 인증 통과
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().mvcMatchers("/resources/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/index.jsp", "/oauth/**", "/member/memberCreate.do").permitAll()
                .antMatchers( "/member/checkIdDuplicate.do",
                        "/member/checkNicknameDuplicate.do", "/member/checkPhoneDuplicate.do",
                        "/member/checkEmail.do", "/member/checkEmailSearch.do",
                            "/member/resetPassword.do","/profile/profileCreate","/profile/defaultcreate.do").anonymous()
                .antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                .antMatchers("/story/storyMain", "/feedDetails/commentUpdate").permitAll()
                .antMatchers("/stomp").permitAll()
                .anyRequest().authenticated();


        http.formLogin()
                .loginPage("/member/memberLogin.do")
                .loginProcessingUrl("/member/memberLogin.do").permitAll()
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/member/userPage")
                .permitAll();

        http.logout()
                .logoutUrl("/member/memberLogout.do")
                .logoutSuccessUrl("/")
                .permitAll();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(principalService).passwordEncoder(passwordEncoder());

    }
}