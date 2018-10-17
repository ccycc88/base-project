package com.base.project.biz.security.form.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 依据用户名查询用户等信息
 */
@Service("appUserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        if(s.equals("admin")){

            //查询到的用户名进行解密，解密后的用户名再使用特定算法加密

            String password = passwordEncoder.encode("admin");
            User user = new User("admin", password, true, true, true, true, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
            return user;
        }
        return null;
    }
}
