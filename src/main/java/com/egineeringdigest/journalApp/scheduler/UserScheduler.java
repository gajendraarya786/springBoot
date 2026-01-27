package com.egineeringdigest.journalApp.scheduler;

import com.egineeringdigest.journalApp.Repository.UserRepositoryImpl;
import com.egineeringdigest.journalApp.entity.JournalEntry;
import com.egineeringdigest.journalApp.entity.User;
import com.egineeringdigest.journalApp.service.EmailService;
import com.egineeringdigest.journalApp.service.SentimentAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class UserScheduler {

    @Autowired
    private EmailService emailService;

    @Autowired
    private SentimentAnalysisService sentimentAnalysisService;
    @Autowired
    private UserRepositoryImpl userRepository;

    @Scheduled(cron = "0 0 9 * * SUN")
    public void fetchUsersAndSendSaMail(){
        List<User> users = userRepository.getUserForSA();
        for(User user: users){
             List<JournalEntry> journalEntries = user.getJournalEntries();

            String entryText = journalEntries.stream()
                    .map(JournalEntry::getContent)
                    .filter(Objects::nonNull)
                    .filter(content -> !content.isBlank())
                    .collect(Collectors.joining(" "));

            if (entryText.isBlank()) {
                continue;
            }

            String sentiment = sentimentAnalysisService.getSentiment(entryText);

            emailService.sendEmail(
                    user.getEmail(),
                    "Your Journal Sentiment",
                    sentiment
            );
        }
    }
}
