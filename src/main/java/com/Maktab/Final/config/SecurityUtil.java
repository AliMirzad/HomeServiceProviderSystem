package com.Maktab.Final.config;

import com.Maktab.Final.model.entity.baseEntity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//package com.Maktab.Final.config;
//
//import com.Maktab.Final.model.entity.baseEntity.User;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.core.context.SecurityContextHolder;
//
@Configuration
public class SecurityUtil {
    public static User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
@Bean
public PasswordEncoder passwordEncoder() {
	return new BCryptPasswordEncoder(10);
}
}
