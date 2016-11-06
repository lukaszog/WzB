package pl.lenda.marcin.wzb.service.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Created by Promar on 04.11.2016.
 */
@Service
public class MailService {

    private final JavaMailSender javaMailSender;

    @Autowired
    MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public SimpleMailMessage mailSend(String setTo, String setFrom, String setSubject, String setText){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(setTo);
        mailMessage.setReplyTo(setTo);
        mailMessage.setFrom(setFrom);
        mailMessage.setSubject(setSubject);
        mailMessage.setText(setText);
        javaMailSender.send(mailMessage);
        return mailMessage;
    }
}