package pl.lenda.marcin.wzb.config;

/**
 * Created by Promar on 22.11.2016.
 */

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailTest {

    static final String FROM = "777marcinlenda777@gmail.com";
    static final String TO = "lukasz.ogan@gmail.com";

    static final String BODY = "This email was sent through the Amazon SES SMTP interface by using Java.";
    static final String SUBJECT = "Amazon SES test (SMTP interface accessed using Java)";

    static final String SMTP_USERNAME = "AKIAIEINGDQY7VLCZZNA";
    static final String SMTP_PASSWORD = "Am1bjsuYrqVcTcSlGBA1/wxpN/snIhZqlTyBadwrZu73";

    static final String HOST = "email-smtp.us-west-2.amazonaws.com";

    static final int PORT = 25;

    public static void main(String[] args) throws Exception {

        Properties props = System.getProperties();
        props.put("mail.transport.protocol", "smtps");
        props.put("mail.smtp.port", PORT);

        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.starttls.required", "true");

        Session session = Session.getDefaultInstance(props);

        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(FROM));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(TO));
        msg.setSubject(SUBJECT);
        msg.setContent(BODY,"text/plain");

        Transport transport = session.getTransport();
        try
        {
            System.out.println("Attempting to send an email through the Amazon SES SMTP interface...");
            transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);

            transport.sendMessage(msg, msg.getAllRecipients());
            System.out.println("Email sent!");
        }
        catch (Exception ex) {
            System.out.println("The email was not sent.");
            System.out.println("Error message: " + ex.getMessage());
        }
        finally
        {
            transport.close();
        }
    }
}

