package com.tallerMongo.app.repository;

import com.tallerMongo.app.model.StudentModel;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface StudentRepository extends MongoRepository<StudentModel, ObjectId> {
}
