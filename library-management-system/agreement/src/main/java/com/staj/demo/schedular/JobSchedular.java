package com.staj.demo.schedular;

import com.staj.demo.model.Agreement;
import com.staj.demo.service.AgreementService;
import com.staj.demo.service.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Service
public class JobSchedular {

    private EmailService emailService;
    private AgreementService agreementService;
    //everyday at 08:30
    @Scheduled(cron = "0 30 8 * * ?")
    public void sendReminderEmails() {
        LocalDate today = LocalDate.now();
        System.out.println("Today is: "+today);
        List<Agreement>agreements=agreementService.getAllAgreements();
        for (Agreement agreement:agreements){
            System.out.println(agreement.getReturnDate());
            if(agreement.getReturnDate().toString().equals(today.toString())){
                System.out.println("HATIRLATMA Mail gönderildi");
                emailService.sendMail(agreement.getUser());
            }
        }

    }
}
