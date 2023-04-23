package com.tallerMongo.app.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tallerMongo.app.helpers.ObjectIdSerializer;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "user")
public class UserModel {
  @MongoId
  @JsonSerialize(using = ObjectIdSerializer.class)
  private ObjectId id;
  @Indexed(unique = true)
  private String email;
  private String password;
  private String typeDocument;
  private String firstName;
  private String firstSurname;
  @Indexed(unique = true)
  private String documentId;
  @DocumentReference
  private StudentModel student;
  @DocumentReference
  private RoleModel role;

  public String getTypeDocument() {
    return typeDocument;
  }

  public StudentModel getStudent() {
    return student;
  }

  public void setStudent(StudentModel student) {
    this.student = student;
  }

  public void setTypeDocument(String typeDocument) {
    this.typeDocument = typeDocument;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getFirstSurname() {
    return firstSurname;
  }

  public void setFirstSurname(String firstSurname) {
    this.firstSurname = firstSurname;
  }

  public String getDocumentId() {
    return documentId;
  }

  public void setDocumentId(String documentId) {
    this.documentId = documentId;
  }

  public ObjectId getId() {
    return id;
  }

  public void setId(ObjectId id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String passwordUser() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public RoleModel getRole() {
    return role;
  }

  public void setRole(RoleModel role) {
    this.role = role;
  }

  public boolean allDataComplete () {
    return this.email != null && this.firstName != null && this.documentId != null && this.role != null && this.typeDocument != null && this.firstSurname != null;
  }

  @Override
  public String toString() {
    return "UserModel{" +
            "id='" + id + '\'' +
            ", email='" + email + '\'' +
            ", password='" + password + '\'' +
            ", typeDocument='" + typeDocument + '\'' +
            ", firstName='" + firstName + '\'' +
            ", firstSurname='" + firstSurname + '\'' +
            ", documentId='" + documentId + '\'' +
            ", role=" + role +
            ", student=" + student +
            '}';
  }
}
