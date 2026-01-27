package com.egineeringdigest.journalApp.Repository;

import com.egineeringdigest.journalApp.entity.ConfigJournalAppEntity;
import com.egineeringdigest.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigJournalAppRepository extends MongoRepository<ConfigJournalAppEntity, ObjectId> {

}
