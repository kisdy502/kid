package com.sdt.kid.auth;

import com.sdt.kid.auth.AuthError;
import com.sdt.kid.auth.JwtAuthenticationProvider;
import com.sdt.kid.auth.JwtTokenFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;

import static org.springframework.http.HttpMethod.POST;

@Configuration
public class AppConfiguration extends WebSecurityConfigurerAdapter {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private JwtTokenFilter jwtTokenFilter;

    @Resource
    private JwtAuthenticationProvider jwtAuthenticationProvider;

    @Resource
    private AuthError authError;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
          http.cors()
                .and()
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers(POST, "/*/*") // 不拦截
                .permitAll().antMatchers("/token/*") // 允许拦截
                .authenticated()
                .and()
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(authError)
                .accessDeniedHandler(authError);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        logger.error("configure auth");
        auth.authenticationProvider(jwtAuthenticationProvider);
    }


    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry
                        .addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("GET", "POST", "PUT", "OPTIONS", "DELETE")
                        .allowedHeaders("*");
            }
        };
    }


}
