package pl.lenda.marcin.wzb.service.mail;

        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.core.io.FileSystemResource;
        import org.springframework.mail.javamail.JavaMailSender;
        import org.springframework.mail.javamail.JavaMailSenderImpl;
        import org.springframework.mail.javamail.MimeMessageHelper;
        import org.springframework.stereotype.Service;
        import pl.lenda.marcin.wzb.base64.ImagesBase64;

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
    @Autowired
    private ImagesBase64 imagesBase64;

    public MimeMessage mailSend(String setTo, String setFrom, String setSubject, String setText) throws MessagingException {
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        helper.setTo(setTo);
        helper.setReplyTo(setTo);
        helper.setFrom(setFrom);
        helper.setSubject(setSubject);
        helper.setText(setText, true);
        FileSystemResource res = new FileSystemResource(new java.io.File("C:\\Users\\Promar\\Desktop\\b5.png"));
        helper.addInline("identifier1234", res);
        javaMailSender.send(mimeMessage);

        return  mimeMessage;
    }

    public MimeMessage mailConfirmAccount(String setTo, String name, String surname, String userName) throws MessagingException {

        String content = "<!DOCTYPE html>\n" +
                "\n" +
                "<html>\n" +
                "<head>\n" +
                "    <link href=\"https://fonts.googleapis.com/css?family=Roboto\" rel=\"stylesheet\">\n" +
                "    <link href=\"mail.css\" rel=\"stylesheet\">\n" +
                "    <style>\n" +
                "        body {\n" +
                "            background-color: #e7e7e7;\n" +
                "            font-family: 'Roboto', sans-serif;\n" +
                "        }\n" +
                "\n" +
                "        table {\n" +
                "            font-family: 'Roboto', sans-serif;\n" +
                "            border-collapse: collapse;\n" +
                "            width: 100%;\n" +
                "\n" +
                "        }\n" +
                "        h1\n" +
                "        {\n" +
                "            margin-top: 70px;\n" +
                "            margin-bottom: 40px;\n" +
                "            font-family: 'Roboto', sans-serif;\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "        p{\n" +
                "            font-size: 23px;\n" +
                "        }\n" +
                "        td, th {\n" +
                "            border: 2px solid #dddddd;\n" +
                "            text-align: left;\n" +
                "            padding: 8px;\n" +
                "        }\n" +
                "\n" +
                "        tr:nth-child(even) {\n" +
                "            background-color: #FFFFFF;\n" +
                "        }\n" +
                "\n" +
                "        #footer {\n" +
                "            width: 100%;\n" +
                "            height: 80px;\n" +
                "            margin-bottom: 80px;\n" +
                "            background-color:rgba(0,0,0,.87);\n" +
                "            margin-top: 50px;\n" +
                "            color: #FFFFFF;\n" +
                "            padding-left: 7px;\n" +
                "\n" +
                "        }\n" +
                "\n" +
                "\n" +
                "\n" +
                "        #footer p {\n" +
                "            font-family: 'Roboto', sans-serif;\n" +
                "            float: left;\n" +
                "            font-size: 17px;\n" +
                "        }\n" +
                "\n" +
                "        #logo {\n" +
                "            float: right;\n" +
                "            margin-right: 25px;\n" +
                "\n" +
                "        }\n" +
                "\n" +
                "\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1>Witaj Marcin!</h1>\n" +
                "<p>W poniższej tabelce jest zawarta lista nieodebranych dokumentów WZ Twoich klientów na dzień:</p>\n" +
                "\n" +
                "<table>\n" +
                "\n" +
                "    <tr style=\"background-color: #3498db; color:white;\">\n" +
                "\n" +
                "        <th style=\"width: 30%;\">Numer WZ</th>\n" +
                "\n" +
                "        <th style=\"width: 10%;\">Podproces</th>\n" +
                "\n" +
                "        <th style=\"width: 30%;\">Nazwa klienta</th>\n" +
                "        <th style=\"width: 15%;\">Numer klienta</th>\n" +
                "\n" +
                "        <th style=\"width: 15%;\">Zwłoka</th>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "\n" +
                "        <th style=\"width: 30%;\">Numer WZ</th>\n" +
                "\n" +
                "        <th style=\"width: 10%;\">Podproces</th>\n" +
                "\n" +
                "        <th style=\"width: 30%;\">Nazwa klienta</th>\n" +
                "        <th style=\"width: 15%;\">Numer klienta</th>\n" +
                "\n" +
                "        <th style=\"width: 15%;\">Zwłoka</th>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "\n" +
                "        <th style=\"width: 30%;\">Numer WZ</th>\n" +
                "\n" +
                "        <th style=\"width: 10%;\">Podproces</th>\n" +
                "\n" +
                "        <th style=\"width: 30%;\">Nazwa klienta</th>\n" +
                "        <th style=\"width: 15%;\">Numer klienta</th>\n" +
                "\n" +
                "        <th style=\"width: 15%;\">Zwłoka</th>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "\n" +
                "        <th style=\"width: 30%;\">Numer WZ</th>\n" +
                "\n" +
                "        <th style=\"width: 10%;\">Podproces</th>\n" +
                "\n" +
                "        <th style=\"width: 30%;\">Nazwa klienta</th>\n" +
                "        <th style=\"width: 15%;\">Numer klienta</th>\n" +
                "\n" +
                "        <th style=\"width: 15%;\">Zwłoka</th>\n" +
                "    </tr>\n" +
                "</table>\n" +
                "\n" +
                "<div id=\"footer\">\n" +
                "    <p>Odwiedź <a href=\"52.39.52.69:8080\">www.wzb.pl</a> Mail został wygenerowany automatycznie. Wszelkie prawa zastrzeżone.</p>\n" +
                "    <div id=\"logo\">\n" +
                "    <img  src="+imagesBase64.getLogo()+">\n" +
                "    </div>\n" +
                "</div>\n" +
                "\n" +
                "</body>\n" +
                "</html>";

//        String allContent = content + line1 + line2 + line3 + endContent;


        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        helper.setTo(setTo);
        helper.setReplyTo(setTo);
        helper.setFrom("wzbims@gmail.com");
        helper.setSubject("Aktywacja konta: "+userName);
        helper.setText(content, true);

        javaMailSender.send(mimeMessage);
        return mimeMessage;
    }
}