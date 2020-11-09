package pl.book.it.api.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.book.it.api.services.user.CustomUserDetailsService;

import javax.sql.DataSource;


@RequiredArgsConstructor
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomUserDetailsService customUserDetailsService;

    @Override
    protected UserDetailsService userDetailsService() {
        return customUserDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/h2/**").authenticated().and()
                .formLogin()
                .and()
                .httpBasic()
                .and()
                .logout()
                .and()
                .csrf().ignoringAntMatchers("/h2/**")
                .and()
                .headers().frameOptions().disable();
    }




}
