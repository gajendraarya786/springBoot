package com.egineeringdigest.journalApp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "journal_entries")
@Data
@NoArgsConstructor(force = true)
public class JournalEntry {

//    To make the id primary key we use @Id
    @Id
    private ObjectId id;

    @NonNull
    private String title;

    private String content;

    public ObjectId getId() {
        return id;
    }
}
