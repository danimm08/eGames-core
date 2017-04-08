package es.eGames.security.oauth;

import es.eGames.security.aux.CustomAuthenticationEntryPoint;
import es.eGames.security.aux.CustomLogoutSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * Created by daniel on 2/04/17.
 */
@Configuration
public class ResourceServer {

    @Configuration
    @EnableResourceServer
    protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

        @Autowired
        private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

        @Autowired
        private CustomLogoutSuccessHandler customLogoutSuccessHandler;

        @Override
        public void configure(HttpSecurity http) throws Exception {

            http
                    .exceptionHandling()
                    .authenticationEntryPoint(customAuthenticationEntryPoint)
                    .and()
                    .logout()
                    .logoutUrl("/oauth/logout")
                    .logoutSuccessHandler(customLogoutSuccessHandler)
                    .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .authorizeRequests()
                    .antMatchers("/exchange/**").authenticated()
                    .antMatchers("/game/**").authenticated()
                    .antMatchers("/image/**").authenticated()
                    .antMatchers("/message/**").authenticated()
                    .antMatchers("/personalgame/**").authenticated()
                    .antMatchers("/qualification/**").authenticated()
                    .antMatchers("/useraccount/**").authenticated()
                    .antMatchers("/user/**").authenticated();

        }

    }


}
