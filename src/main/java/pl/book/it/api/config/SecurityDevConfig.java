package pl.book.it.api.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Primary
@Profile("dev")
@Configuration
@RequiredArgsConstructor
public class SecurityDevConfig extends WebSecurityConfigurerAdapter {

    private final ApiUserConfig apiUserConfig;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser(apiUserConfig.getUserName()).password(apiUserConfig.getPassword()).roles();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/h2/**").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable()
                .httpBasic()
        .and().headers().frameOptions().disable();
    }
}
