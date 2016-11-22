package pl.lenda.marcin.wzb.service.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Created by Promar on 04.11.2016.
 */
@Service
public class MailService {

    private final JavaMailSender javaMailSender;
    JavaMailSenderImpl sender = new JavaMailSenderImpl();
    MimeMessage mimeMessage = sender.createMimeMessage();


    @Autowired
    MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public MimeMessage mailSend(String setTo, String setFrom, String setSubject, String setText) throws MessagingException {
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        helper.setTo(setTo);
        helper.setReplyTo(setTo);
        helper.setFrom(setFrom);
        helper.setSubject(setSubject);
        helper.setText(setText, true);
//        FileSystemResource res = new FileSystemResource(new java.io.File("C:\\Users\\Promar\\Desktop\\b5.png"));
//        helper.addInline("identifier1234", res);
        javaMailSender.send(mimeMessage);

        return  mimeMessage;
    }

    public MimeMessage mailConfirmAccount(String setTo, String name, String surname, String userName) throws MessagingException {

        String content = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<style>\n" +
                "table {\n" +
                "    font-family: arial, sans-serif;\n" +
                "    border-collapse: collapse;\n" +
                "    width: 100%;\n" +
                "}\n" +
                "\n" +
                "td, th {\n" +
                "    border: 2px solid #dddddd;\n" +
                "    text-align: left;\n" +
                "    padding: 8px;\n" +
                "}\n" +
                "\n" +
                "tr:nth-child(even) {\n" +
                "    background-color: #dddddd;\n" +
                "}\n" +
                "#photo\n" +
                "        {\n" +
                "            width: 100%;\n" +
                "            height: 62px;\n" +
                "            margin-bottom: 80px;\n"+
                "            background-color: #dddddd;\n" +
                "        }\n" +
                "        #photo img\n" +
                "        {\n" +
                "            float: left;\n" +
                "            margin-right: 100px;\n" +
                "        }\n" +
                "        #photo p\n" +
                "        {\n" +
                "            font-family: arial, sans-serif;\n" +
                "            float: right;\n" +
                "            position: absolute;\n" +
                "            margin-right: 130px;\n" +
                "\n" +
                "        }"+
                "</style>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<h1>Witaj Twoje konto zostało aktywowane</h1>"+
                "<table>\n" +
                " <tr style=\"background-color: #3258A6; color:white;\">\n" +
                "        <th style=\"width: 33%;\">Imię</th>\n" +
                "        <th style=\"width: 33%;\">Nazwisko</th>\n" +
                "        <th style=\"width: 33%;\">E-mail</th>\n" +
                "    </tr>";
            String line1 = "<th>" + name + "</th>";
            String line2 = "<th>" + surname + "</th>";
            String line3 = "<th>" + userName + "</th>";


        String endContent =  "</table>\n" +
                "\n" +
                "<div id=\"photo\">\n" +
                "    <p><b>Mail został wygenerowany automatycznie. Wszelkie prawa zastrzeżone.</b></p>\n" +
                "    <img > </div>"
                +
                "</body>\n" +
                "</html>";

        String allContent = content + line1 + line2 + line3 + endContent;


        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        helper.setTo(setTo);
        helper.setReplyTo(setTo);
        helper.setFrom("wzbims@gmail.com");
        helper.setSubject("Aktywacja konta: "+userName);
        helper.setText(allContent, true);
        FileSystemResource res = new FileSystemResource(new java.io.File("C:\\Users\\Promar\\Desktop\\b5.png"));
        helper.addInline("identifier1234", res);
        javaMailSender.send(mimeMessage);
        return mimeMessage;
    }
}