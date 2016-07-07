package com.GH.king;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import java.util.Properties;


/**
 * Created by alex on 2016/3/8.
 */
@Configuration
@PropertySource(value= "classpath:Email.properties")

public class GahEmailConfig {

    @Value("${GaH.stmp.host}")
    private String host;
    @Value("${GaH.stmp.account}")
    private String account;
    @Value("${GaH.stmp.password}")
    private String password;

    @Value("${mail.smtp.auth}")
    private String isAuth;
    @Value("${mail.smtp.timeout}")
    private String outTime;

    @Bean(name = "EmailConfig")
    public JavaMailSenderImpl getSender(){
        JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();

        javaMailSenderImpl.setUsername(account);
        javaMailSenderImpl.setPassword(password);
        javaMailSenderImpl.setHost(host);

        Properties properties=new Properties();
        properties.put("mail.smtp.auth", isAuth);
        properties.put("mail.smtp.timeout", outTime);
        javaMailSenderImpl.setJavaMailProperties(properties);
        return javaMailSenderImpl;
    }


}
