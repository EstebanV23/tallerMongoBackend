package com.tallerMongo.app.repository;

import com.tallerMongo.app.model.RoleModel;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends MongoRepository<RoleModel, ObjectId> {
}
