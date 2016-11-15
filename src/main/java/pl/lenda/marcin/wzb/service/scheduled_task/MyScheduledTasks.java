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
        String endContent =  "</table>\n" +
                "\n" +
                "<div id=\"photo\">\n" +
                "    <p><b>Mail został wygenerowany automatycznie. Wszelkie prawa zastrzeżone.</b></p>\n" +
                "    <img src='cid:identifier1234'> </div>"
                +
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
                    "<h1>Cześć "+traderAccounts.get(i).getName()+" oto lista Twoich dokumentów.</h1>"+
                    "<table>\n" +
                    " <tr style=\"background-color: #3258A6; color:white;\">\n" +
                    "        <th style=\"width: 30%;\">Numer WZ</th>\n" +
                    "        <th style=\"width: 10%;\">Podproces</th>\n" +
                    "        <th style=\"width: 30%;\">Nazwa klienta</th>\n" +
                    "        <th style=\"width: 15%;\">Numer klienta</th>\n" +
                    "        <th style=\"width: 15%;\">Zwłoka</th>" +
                    "    </tr>";

            if(documentsWZ.size() > 0) {
                for (int j = 0; j < documentsWZ.size(); j++) {




                    diff = new Date().getTime() - documentsWZ.get(j).getDate().getTime();
                    diffDays = diff / (24 * 60 * 60 * 1000);

                    if(diffDays>10) {
                        content = content + startTable;
                        line1 = "<th>" + documentsWZ.get(j).getNumberWZ() + "</th>";
                        line2 = "<th>" + documentsWZ.get(j).getSubProcess() + "</th>";
                        line3 = "<th>" + documentsWZ.get(j).getClient() + "</th>";
                        line5 = "<th>" + documentsWZ.get(j).getClientNumber() + "</th>";
                        line4 = "<th style=\"color: red;\">" + diffDays + " dni" + "</th>";
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
                    System.out.println(to);
                    mail.setFrom("wzbims@gmail.com");
                    mailService.mailSend("agnieszka7l@wp.pl", mail.getFrom(), mail.getSubject(), mail.getContent());
                }
                documentsWZ.clear();
                content = "";
                send = false;
                howManyDocumentsToSend = 0;

            }
        }


    }
}
