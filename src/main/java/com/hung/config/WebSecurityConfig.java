package com.hung.config;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.social.security.SpringSocialConfigurer;

import com.hung.service.MyDBAuthenticationService;

/**
 * クラスタイトル(ピリオド削除厳禁).
 *
 * <pre>
 * 内容, 使用例など
 * </pre>
 *
 * @author Mitsui Zosen Systems Research Inc.
 * @version X.X
 * @since TIME-3 X.X
 */
@Configuration
// @EnableWebSecurity = @EnableWebMVCSecurity + Extra features
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    /** Logger(不要な場合は削除). */
    private static final Logger LOGGER = LoggerFactory.getLogger(WebSecurityConfig.class);
    @Autowired
    MyDBAuthenticationService myDBAauthenticationService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        // Các User trong bộ nhớ (MEMORY).

        auth.inMemoryAuthentication().withUser("user1").password("12345").roles("USER");
        auth.inMemoryAuthentication().withUser("admin1").password("12345").roles("USER, ADMIN");

        // Các User trong Database
        auth.userDetailsService(myDBAauthenticationService);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf();

        // Các trang không yêu cầu login
        http.authorizeRequests().antMatchers("/", "/welcome", "/login", "/logout", "/signup").permitAll();

        // Trang /userInfo yêu cầu phải login với vai trò USER hoặc ADMIN.
        // Nếu chưa login, nó sẽ redirect tới trang /login.
        http.authorizeRequests().antMatchers("/guest").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')");

        // For ADMIN only.
        // Trang chỉ dành cho ADMIN
        http.authorizeRequests().antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')");

        // Khi người dùng đã login, với vai trò XX.
        // Nhưng truy cập vào trang yêu cầu vai trò YY,
        // Ngoại lệ AccessDeniedException sẽ ném ra.
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");

        // Cấu hình cho Login Form.
        http.formLogin()

                .successHandler(savedRequestAwareAuthenticationSuccessHandler())
                // Submit URL của trang login
                .loginProcessingUrl("/auth")
                .loginPage("/login")
                // "LoginID" FieldName in LoginForm
                .usernameParameter("userName")
                // "Password" FieldName in LoginForm
                .passwordParameter("password")
                .defaultSuccessUrl("/admin")
                .failureUrl("/login?error=true")
                .permitAll();

        http.logout().logoutUrl("/logout").logoutSuccessUrl("/logoutSuccessful").permitAll();

        // Remember me 2 weeks
        http.rememberMe().tokenRepository(persistentTokenRepository()).tokenValiditySeconds(1209600);

        // Spring Social Config.
        http.apply(new SpringSocialConfigurer())
                //
                .signupUrl("/signup");
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
        db.setDataSource(dataSource);
        return db;
    }

    @Bean
    public SavedRequestAwareAuthenticationSuccessHandler savedRequestAwareAuthenticationSuccessHandler() {

        SavedRequestAwareAuthenticationSuccessHandler auth = new SavedRequestAwareAuthenticationSuccessHandler();
        auth.setTargetUrlParameter("targetUrl");
        return auth;
    }

    /**
     * This bean is load the user specific data when form login is used.
     * 
     * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#userDetailsService()
     * @return
     */
    @Override
    public UserDetailsService userDetailsService() {
        return myDBAauthenticationService;
    }
}
