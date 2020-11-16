package pl.book.it.api.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import pl.book.it.api.services.user.CustomUserDetailsService;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomUserDetailsService customUserDetailsService;

    @Override
    protected UserDetailsService userDetailsService() {
        return customUserDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //temporary
                .antMatchers("/bia/admin/**").permitAll()
                .antMatchers("/h2/**").authenticated().and()
                .formLogin()
                .and()
                .httpBasic()
                .and()
                .logout()
//     .and()
//     .csrf().ignoringAntMatchers("/h2/**")
                .and()
//     temporary
                .csrf().disable()
                .headers().frameOptions().disable();
    }
}
