package com.base.project.app.configurer;

import com.base.project.biz.security.form.configure.FormHttpSecurityConfigure;
import com.base.project.biz.security.sms.configure.SmsHttpSecurityConfigure;
import com.base.project.biz.security.validate.configure.ValidateCodeSecurityConfigure;
import com.base.project.commcon.constant.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
public class AppSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Autowired
    private FormHttpSecurityConfigure formHttpSecurityConfigure;
    @Autowired
    private ValidateCodeSecurityConfigure validateCodeSecurityConfigure;

    @Autowired
    private SmsHttpSecurityConfigure smsHttpSecurityConfigure;

    @Autowired
    @Qualifier("appUserDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    @Qualifier("appDataSource")
    private DataSource dataSource;

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/webapp/**",
                "/swagger-ui.html*","/webjars/**",
                "/swagger-resources/**","/v2/api-docs");
    }

    /**
     * 记住我 token 存储
     * @return
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository(){

        PersistentTokenRepository repository = new JdbcTokenRepositoryImpl();
        ((JdbcTokenRepositoryImpl) repository).setDataSource(dataSource);
        //((JdbcTokenRepositoryImpl) repository).setCreateTableOnStartup(true);
        return repository;
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //formHttpSecurityConfigure.configure(http);
        http
//                .apply(validateCodeSecurityConfigure)
//                .and()
//                .apply(smsHttpSecurityConfigure)
//                .and()
//                .rememberMe()
//                .tokenRepository(persistentTokenRepository())
//                .tokenValiditySeconds(60*5)
//                .userDetailsService(userDetailsService)
//                .and()
//                .authorizeRequests()
//                .antMatchers(Constant.DEFAULT_UNAUTHENTICATION_URL, "/code/image")
//                .permitAll()
//                .anyRequest()
//                .authenticated()
//                .and()
                .csrf()
                .disable();

    }
}
