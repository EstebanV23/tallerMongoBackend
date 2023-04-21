package com.tallerMongo.app.repository;

import com.tallerMongo.app.model.UserModel;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<UserModel, ObjectId> {
  @Query("{'email': ?0, 'password': ?1}")
  UserModel loginUser (String email, String password);

  @Query("{'email': ?0}")
  UserModel findByEmail (String email);

  @Query("{'documentId': ?0}")
  UserModel findByDocumentId (String documentId);
}
