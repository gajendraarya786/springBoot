package com.egineeringdigest.journalApp.service;

import com.egineeringdigest.journalApp.Repository.JournalEntryRepository;
import com.egineeringdigest.journalApp.entity.JournalEntry;
import com.egineeringdigest.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {


    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    //making transactional to achieve acid property that if a line fails whole function fails
    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName){
        try{
            User user = userService.findByUsername(userName);
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(saved);
            userService.saveUser(user);
        }catch(Exception e){
            System.out.println(e);
            throw new RuntimeException("An error occured while saving the entry.", e);
        }
    }

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }



    public List<JournalEntry> getAllEntries(){
        return journalEntryRepository.findAll();
    }
    @Transactional
    public void deleteById(ObjectId id, String userName) {
        try {
            User user = userService.findByUsername(userName);
            boolean removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if (removed) {
                userService.saveUser(user);
                journalEntryRepository.deleteById(id);
            }
        }catch (Exception e){
            System.out.println(e);
            throw  new RuntimeException("An error occured while deleting the entry", e);
        }
    }

    public Optional <JournalEntry> findById(ObjectId myId){
        return journalEntryRepository.findById((myId));
    }
}

