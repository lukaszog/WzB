package pl.lenda.marcin.wzb.service.scheduled_task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.lenda.marcin.wzb.entity.DocumentWz;
import pl.lenda.marcin.wzb.entity.Mail;
import pl.lenda.marcin.wzb.entity.TraderAccount;
import pl.lenda.marcin.wzb.entity.UserAccount;
import pl.lenda.marcin.wzb.service.document_wz.DocumentWzService;
import pl.lenda.marcin.wzb.service.mail.MailService;
import pl.lenda.marcin.wzb.service.trader.TraderService;
import pl.lenda.marcin.wzb.service.user_account.UserAccountService;

import javax.mail.MessagingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Promar on 04.11.2016.
 */
@Component
public class MyScheduledTasks {

    private static final SimpleDateFormat dateFormat =
            new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    private final String setTo = "acymanowski@bimsplus.com.pl";
    private Mail mail = new Mail();
    private Date date;


    @Autowired
    public MailService mailService;
    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private TraderService traderService;
    @Autowired
    private DocumentWzService documentWzService;

    public MyScheduledTasks(MailService mailService) {
        this.mailService = mailService;
    }

    @Scheduled(fixedRate = 1000000)
    public void sendMailToCustomers() throws MessagingException {

        List<UserAccount> userAccounts = userAccountService.findAllAccount();
        List<TraderAccount> traderAccounts = new ArrayList<>();
        for (UserAccount user : userAccounts) {


            if (traderService.findByTraderSurnameAndNumber(user.getSurname(), user.getNumberUser()) != null) {
                traderAccounts.add(traderService.findByTraderSurnameAndNumber(user.getSurname(), user.getNumberUser()));
                System.out.println("Znalazłem handlowca: "+traderService.findByTraderSurnameAndNumber(user.getSurname(), user.getNumberUser()));
            }
        }
        System.out.println(traderAccounts.toString());
        List<DocumentWz> documentsWZ = new ArrayList<>();
        boolean send = false;
        Integer howManyDocumentsToSend = 0;
        String startTable = "<tr>";
        String endTable = "</tr>";
        String line1 = "";
        String line2 = "";
        String line3 = "";
        String line4 = "";
        String line5 = "";
        String line6 = "";
        long counter = 0;
        String endContent =  " </table>\n" +
                "    <p style=\"font-size: 17px;\">Gdybyś miał zamiar zrobić korektę do któregoś z dokumentów poinformuj nas o tym na: <a\n" +
                "            href=\"http://52.39.52.69:8080/#/login\">\n" +
                "        <input style=\"width:150px; margin-top: 30px;\" type=\"button\" value=\"WzB\">\n" +
                "    </a></p>\n" +
                "\n" +
                "\n" +
                "    <div id=\"footer\">\n" +
                "        <p> Mail został wygenerowany automatycznie. All rights reserved &copy;</p>\n" +
                "        <div id=\"logo\">\n" +
                "            <img src='data:image/png;base64,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'\n" +
                "                 alt=\"WzB\">\n" +
                "        </div>\n" +
                "    </div>\n" +
                "\n" +
                "</div>\n" +
                "\n" +
                "\n" +
                "</body>\n" +
                "</html>";
        //Variable to calculate date and time difference
        long diff = 0;
        long diffDays = 0;


        //HH converts hour in 24 hours format (0-23), day calculation
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

        for (int i = 0; i < traderAccounts.size(); i++) {

            documentsWZ = documentWzService.findByNameTrader(traderAccounts.get(i).getSurname());
            //Create format HTML mail
            String content = "<!DOCTYPE html>\n" +
                    "\n" +
                    "<html>\n" +
                    "<head>\n" +
                    "    <!--<link href=\"https://fonts.googleapis.com/css?family=Roboto\" rel=\"stylesheet\">-->\n" +
                    "    <meta charset=\"utf-8\">\n" +
                    "    <style>\n" +
                    "\n" +
                    "        body {\n" +
                    "            background-color: #dddddd;\n" +
                    "            font-family: 'Roboto', sans-serif;\n" +
                    "\n" +
                    "        }\n" +
                    "\n" +
                    "        table {\n" +
                    "            font-family: 'Roboto', sans-serif;\n" +
                    "            border-collapse: collapse;\n" +
                    "        }\n" +
                    "\n" +
                    "        h1 {\n" +
                    "            margin-top: 28px;\n" +
                    "            margin-bottom: 25px;\n" +
                    "            /*font-family: 'Roboto', sans-serif;*/\n" +
                    "            text-align: center;\n" +
                    "            font-size: 50px;\n" +
                    "        }\n" +
                    "\n" +
                    "        p {\n" +
                    "            font-size: 25px;\n" +
                    "        }\n" +
                    "\n" +
                    "        td, th {\n" +
                    "            border: 2px solid #FFFFFF;\n" +
                    "            text-align: center;\n" +
                    "            padding: 8px;\n" +
                    "            font-size: 20px;\n" +
                    "        }\n" +
                    "\n" +
                    "        tr:nth-child(even) {\n" +
                    "            background-color: #dddddd;\n" +
                    "        }\n" +
                    "\n" +
                    "        #content {\n" +
                    "            width: 80%;\n" +
                    "            margin: 0 auto;\n" +
                    "            background-color: #FFFFFF;\n" +
                    "            padding-top: 15px;\n" +
                    "            padding-bottom: 15px;\n" +
                    "            padding-left: 3px;\n" +
                    "            padding-right: 4px;\n" +
                    "\n" +
                    "        }\n" +
                    "\n" +
                    "        #footer {\n" +
                    "            width: 100%;\n" +
                    "            height: 80px;\n" +
                    "            background-color: rgba(0, 0, 0, .87);\n" +
                    "            color: #FFFFFF;\n" +
                    "            padding-left: 1px;\n" +
                    "            margin-top: 30px;\n" +
                    "\n" +
                    "        }\n" +
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
                    "        input {\n" +
                    "            font-family: \"Open Sans\", Sans-serif;\n" +
                    "            font-size: 25px;\n" +
                    "            width: 470px;\n" +
                    "            height: 50px;\n" +
                    "            display: inline-block;\n" +
                    "            margin: 0 auto;\n" +
                    "            background: #23AD21;\n" +
                    "            color: white;\n" +
                    "            border: solid 1px #b3aca7;\n" +
                    "        }\n" +
                    "\n" +
                    "        input:hover {\n" +
                    "            background: #fec503;\n" +
                    "            cursor: pointer;\n" +
                    "\n" +
                    "        }\n" +
                    "\n" +
                    "\n" +
                    "    </style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "\n" +
                    "<div id=\"content\">\n" +
                    "    <h1>Witaj "+traderAccounts.get(i).getName()+"!</h1>\n" +
                    "    <p>Oto lista nieodebranych dokumentów WZ przez Twoich klientów.</p>\n" +
                    "\n" +
                    "    <table style=\"margin: 0 auto\">\n" +
                    "\n" +
                    "        <tr style=\"background-color: #23AD21; color:white;\">\n" +
                    "            <th style=\"width: 30%; font-size: 16px;\">#</th>\n" +
                    "            <th style=\"width: 30%; font-size: 16px;\">Numer WZ</th>\n" +
                    "            <th style=\"width: 10%; font-size: 16px;\">Pod proces</th>\n" +
                    "            <th style=\"width: 30%; font-size: 16px;\">Klient</th>\n" +
                    "            <th style=\"width: 15%; font-size: 16px;\">Numer klienta</th>\n" +
                    "            <th style=\"width: 15%; font-size: 16px;\">Zwłoka</th>\n" +
                    "        </tr>";

            if(documentsWZ.size() > 0) {
                for (int j = 0; j < documentsWZ.size(); j++) {


                    ++counter;

                    diff = new Date().getTime() - documentsWZ.get(j).getDate().getTime();
                    diffDays = diff / (24 * 60 * 60 * 1000);

                    if(diffDays>10) {
                        content = content + startTable;
                        line6 = "<th style=' font-size: 16px;'>" + counter + "</th>";
                        line1 = "<th style=' font-size: 16px;'>" + documentsWZ.get(j).getNumberWZ() + "</th>";
                        line2 = "<th style=' font-size: 16px;'>" + documentsWZ.get(j).getSubProcess() + "</th>";
                        line3 = "<th style=' font-size: 16px;'>" + documentsWZ.get(j).getClient() + "</th>";
                        line5 = "<th style=' font-size: 16px;'>" + documentsWZ.get(j).getClientNumber() + "</th>";
                        line4 = "<th style=\"font-size: 16px; color: red;\">" + diffDays + " dni" + "</th>";
                        content = content + line1 + line2 + line3 + line5 + line4 + endTable;
                        howManyDocumentsToSend ++;
                        send = true;
                    }

                    diff = 0;
                    diffDays = 0;
                }
                if(send) {
                    content = content + endContent;
                    mail.setSubject("Nieodebrane dokumenty WZ:"+howManyDocumentsToSend + " " +traderAccounts.get(i).getName() +" "
                            + traderAccounts.get(i).getSurname().toString());
                    mail.setContent(content);
                    String to = userAccountService.findByNameAndSurname(traderAccounts.get(i).getName(),
                            traderAccounts.get(i).getSurname()).getUsername();
                    System.out.println("Wyslac do"+to);
                    mail.setFrom("wzbims@gmail.com");
                    mailService.mailSend("mlenda@bimsplus.com.pl", mail.getFrom(), mail.getSubject(), mail.getContent());
                }
                documentsWZ.clear();
                content = "";
                send = false;
                howManyDocumentsToSend = 0;
                counter = 0;

            }
        }


    }
}
