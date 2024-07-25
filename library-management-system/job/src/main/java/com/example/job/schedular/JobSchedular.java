package com.example.job.schedular;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
public class JobSchedular {

    //everyday at 08:30
    @Scheduled(cron = "0 55 21 * * ?")
    public void sendReminderEmails() {
        LocalDate today = LocalDate.now();
        System.out.println("Today is: "+today);

        //List<Agreement>agreements=agreementService.getAllAgreements();
        /*
        for (Agreement agreement:agreements){
            System.out.println(agreement.getReturnDate());
            if(agreement.getReturnDate().toString().equals(today.toString())){
                System.out.println("HATIRLATMA Mail gönderildi");
                emailService.sendMail(agreement.getUser());
            }
        }
*/

    }
}