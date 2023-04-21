package com.tallerMongo.app.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tallerMongo.app.helpers.ObjectIdSerializer;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "role")
public class RoleModel {
  @MongoId
  @JsonSerialize(using = ObjectIdSerializer.class)
  private ObjectId id;
  private String nameId;
  private String name;

  public String getNameId() {
    return nameId;
  }

  public void setNameId(String nameId) {
    this.nameId = nameId;
  }

  public ObjectId getId() {
    return id;
  }

  public void setId(ObjectId id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "RoleModel{" +
            "id=" + id +
            ", nameId='" + nameId + '\'' +
            ", name='" + name + '\'' +
            '}';
  }
}
