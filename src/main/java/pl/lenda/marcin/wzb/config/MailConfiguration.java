//package pl.lenda.marcin.wzb.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
//
//import java.util.Properties;
//
///**
// * Created by Promar on 04.11.2016.
// */
//@Configuration
//@PropertySource("classpath:mail.properties")
//public class MailConfiguration {
//
//    @Value("${mail.protocol}")
//    private String protocol;
//    @Value("${mail.host}")
//    private String host;
//    @Value("${mail.port}")
//    private int port;
//    @Value("${mail.smtp.auth}")
//    private boolean auth;
//    @Value("${mail.smtp.starttls.enable}")
//    private boolean starttls;
//    @Value("${mail.from}")
//    private String from;
//    @Value("${mail.username}")
//    private String username;
//    @Value("${mail.password}")
//    private String password;
//
//    @Bean
//    public JavaMailSender javaMailSender() {
//        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//        Properties mailProperties = new Properties();
//        mailProperties.put("mail.transport.protocol", protocol);
//        mailProperties.put("mail.smtp.auth", auth);
//        mailProperties.put("mail.smtp.starttls.enable", starttls);
//        mailProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//        mailSender.setJavaMailProperties(mailProperties);
//        mailSender.setHost(host);
//        mailSender.setPort(port);
//        mailSender.setUsername(username);
//        mailSender.setPassword(password);
//        return mailSender;
//    }
//}
