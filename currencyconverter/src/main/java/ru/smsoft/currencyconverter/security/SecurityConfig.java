package ru.smsoft.currencyconverter.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

import static ru.smsoft.currencyconverter.security.Roles.ADMIN;
import static ru.smsoft.currencyconverter.security.Roles.USER;

@Configuration
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

    private PasswordEncoder passwordEncoder;
    private UserDetailsService detailsService;
    private DataSource dataSource;

    public SecurityConfig(){}

    @Autowired
    public SecurityConfig(@Qualifier("userDetailsServiceImpl") UserDetailsService detailsService, PasswordEncoder passwordEncode, DataSource dataSource){
        this.detailsService = detailsService;
        this.passwordEncoder = passwordEncode;
        this.dataSource = dataSource;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//                .csrf().disable()
                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .and()
                .authorizeRequests()
                    .antMatchers("/admin/**").hasRole(ADMIN.name())
                    .antMatchers("/user/**").hasRole(USER.name())
                    .antMatchers("/registration").permitAll()
                    .antMatchers("/converter").hasAnyRole(ADMIN.name(), USER.name())
                    .antMatchers("/home","/").hasAnyRole(ADMIN.name(), USER.name())
                    .antMatchers("/resources/**").permitAll()
                    .anyRequest() .authenticated()
                .and()
                .formLogin()
                    .loginPage("/login").permitAll()
                .defaultSuccessUrl("/home",true)
                    .and()
                .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout","POST"))
                    .clearAuthentication(true)
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID","remember-me")
                    .logoutSuccessUrl("/login")
                .and()
                .rememberMe()
                    .userDetailsService(detailsService)
                    .tokenRepository(tokenRepository());


    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        daoAuthenticationProvider.setUserDetailsService(detailsService);
        return daoAuthenticationProvider;
    }

    @Bean
    PersistentTokenRepository tokenRepository(){
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }
}
