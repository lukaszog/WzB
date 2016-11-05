//package pl.lenda.marcin.wzb.service.mail;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Service;
//
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//
///**
// * Created by Promar on 04.11.2016.
// */
//@Service
//public class SendMailTLS {
//
//    final String username = "wzbbims@gmail.com";
//    final String password = "bimsplus20";
//
//    @Autowired
//    private JavaMailSender javaMailSender;
//
//
//    public SimpleMailMessage mailSend(String setTo, String setFrom, String setSubject, String setText){
//        SimpleMailMessage mailMessage = new SimpleMailMessage();
//        mailMessage.setTo(setTo);
//        mailMessage.setReplyTo(setTo);
//        mailMessage.setFrom(setFrom);
//        mailMessage.setSubject(setSubject);
//        mailMessage.setText(setText);
//        javaMailSender.send(mailMessage);
//        return mailMessage;
//    }
//
//
//    public void sendMail() {
//
//        try{
//            MimeMessage message = javaMailSender.createMimeMessage();
//            message.setFrom(new InternetAddress("wzbims@gmail.com"));
//            message.setRecipients(Message.RecipientType.TO,
//                    InternetAddress.parse("777marcinlenda777@gmail.com"));
//
//            message.setSubject("test");
//            message.setText("asdasdasdasdasdasdasdasdasd");
//
//            javaMailSender.send(message);
//
//            System.out.println("done");
//        }catch (MessagingException e){
//            throw new RuntimeException(e);
//        }
//    }
//}
