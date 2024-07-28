package com.example.job.schedular;

import com.staj.demo.model.Agreement;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;


@Slf4j
@AllArgsConstructor
@Component
public class JobSchedular {

    private WebClient webClient;



    //everyday at 08:30
    @Scheduled(cron = "0 30 08 * * ?")
    public void sendReminderEmails() {
        LocalDate today = LocalDate.now();
        log.info("Today: "+today);
        List<Agreement> agreements=webClient.get()
                .uri("http://localhost:8081/api/agreements/all")
                .retrieve()
                .bodyToFlux(Agreement.class)
                .collectList()
                .block();

        for (Agreement agreement:agreements){
            LocalDate returnDate= agreement.getReturnDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            System.out.println("Return Date: "+returnDate);
            if(returnDate.toString().equals(today.toString())){
                log.info("Sending email to: "+agreement.getUser().getEmail());
                String receiver=agreement.getUser().getEmail();
                if(!agreement.getStatus().equals("Returned")){
                    webClient.post()
                            .uri("http://localhost:8083/email/send?receiver="+receiver)
                            .retrieve()
                            .bodyToMono(String.class)
                            .block();
                }

           }

    }
}}