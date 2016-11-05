//package pl.lenda.marcin.wzb.service.mail;
//
//import org.springframework.stereotype.Service;
//
//import javax.mail.*;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//import java.util.Properties;
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
////    @Autowired
////    private JavaMailSender javaMailSender;
//
//
////    public SimpleMailMessage mailSend(String setTo, String setFrom, String setSubject, String setText){
////        SimpleMailMessage mailMessage = new SimpleMailMessage();
////        mailMessage.setTo(setTo);
////        mailMessage.setReplyTo(setTo);
////        mailMessage.setFrom(setFrom);
////        mailMessage.setSubject(setSubject);
////        mailMessage.setText(setText);
////        javaMailSender.send(mailMessage);
////        return mailMessage;
////    }
//
//    public void sendMail() {
//
//        Properties props = new Properties();
//        props.put("mail.smtp.host", "smtp.gmail.com");
//        props.put("mail.smtp.socketFactory.port", "465");
//        props.put("mail.smtp.socketFactory.class",
//                "javax.net.ssl.SSLSocketFactory");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.port", "465");
//
//        Session session = Session.getDefaultInstance(props,
//                new javax.mail.Authenticator() {
//                    protected PasswordAuthentication getPasswordAuthentication() {
//                        return new PasswordAuthentication(username, password);
//                    }
//                });
//        try{
//            Message message = new MimeMessage(session);
//            message.setFrom(new InternetAddress("wzbims@gmail.com"));
//            message.setRecipients(Message.RecipientType.TO,
//                    InternetAddress.parse("777marcinlenda777@gmail.com"));
//
//            message.setSubject("test");
//            message.setText("asdasdasdasdasdasdasdasdasd");
//
//            Transport.send(message);
//
//            System.out.println("done");
//        }catch (MessagingException e){
//            throw new RuntimeException(e);
//        }
//    }
//}
