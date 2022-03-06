package com.api.oson.config;

import com.api.oson.services.CustomOAuth2UserService;
import com.api.oson.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomOAuth2UserService customOAuth2UserService;

    @Autowired
    private UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeExchange(exchangeSpec -> exchangeSpec.anyExchange().authenticated())
//                .oauth2ResourceServer(oAuth2 -> oAuth2.jwt(jwt -> jwt.jwtDecoder(jwtDecoder())));

        http.authorizeRequests()
                .antMatchers("/", "/oauth2/**", "/home", "/login**","/callback/", "/webjars/**", "/error**", "/oauth2/authorization/**").permitAll()
                .anyRequest().authenticated()
                .and().formLogin().permitAll()
                .and().oauth2Login()
                .userInfoEndpoint().userService(customOAuth2UserService)
                .and().successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        CustomOAuth2User customOAuth2User = (CustomOAuth2User) authentication.getPrincipal();
                        userService.processOAuthPostLogin(customOAuth2User);
//                        response.addHeader("token", authentication);
                        response.sendRedirect("/home");
                    }
                });
    }


}
