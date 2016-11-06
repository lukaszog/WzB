package pl.lenda.marcin.wzb.service.scheduled_task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.lenda.marcin.wzb.entity.Mail;
import pl.lenda.marcin.wzb.service.mail.MailService;

import java.text.SimpleDateFormat;

/**
 * Created by Promar on 04.11.2016.
 */
@Component
public class MyScheduledTasks {

    private static final SimpleDateFormat dateFormat =
            new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    private final String setTo = "777marcinlenda777@gmail.com";
    private Mail mail = new Mail();


    @Autowired
    public MailService mailService;

    public MyScheduledTasks(MailService mailService) {
        this.mailService = mailService;
    }

    @Scheduled(fixedRate = 100000)
    public void sendMailToCustomers() {
    mail.setSubject("Zwyciestwo wojny");
    mail.setContent("ad","asd");
    mail.setFrom("wzbims@gmail.com");
    mailService.mailSend("777marcinlenda777@gmail.com", mail.getFrom(), mail.getSubject(), mail.getContent());
    }
}
