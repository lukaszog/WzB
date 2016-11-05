package pl.lenda.marcin.wzb.service.scheduled_task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

/**
 * Created by Promar on 04.11.2016.
 */
@Component
public class MyScheduledTasks {

    private static final SimpleDateFormat dateFormat =
            new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");



    @Scheduled(fixedRate = 100000)
    public void sendMailToCustomers() {




    }
}
