package com.tallerMongo.app.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tallerMongo.app.helpers.ObjectIdSerializer;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.validation.annotation.Validated;

@Document(collection = "student")
public class StudentModel {
  @MongoId
  @JsonSerialize(using = ObjectIdSerializer.class)
  private ObjectId id;
  private String middleName;
  private String lastSurname;
  private String phone;
  private String registerNumber;
  private Integer saberProScore;
  private Integer saberProScoreLevel;
  private Integer writtenCommunication;
  private Integer writtenCommunicationLevel;
  private Integer quantitativeReasoning;
  private Integer quantitativeReasoningLevel;
  private Integer readingCritical;
  private Integer readingCriticalLevel;
  private Integer citizenshipCompetence;
  private Integer citizenshipCompetenceLeve;
  private Integer english;
  private Integer englishLevel;
  private Integer engineeringProjectFormulation;
  private Integer engineeringProjectFormulationLevel;
  private Integer mathematicsStatistics;
  private Integer mathematicsStatisticsLevel;
  private Integer softwareDesign;
  private Integer softwareDesignLevel;
  private String englishPosition;

  public ObjectId getId() {
    return id;
  }

  public void setId(ObjectId id) {
    this.id = id;
  }

  public String getMiddleName() {
    return middleName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  public String getLastSurname() {
    return lastSurname;
  }

  public void setLastSurname(String lastSurname) {
    this.lastSurname = lastSurname;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getRegisterNumber() {
    return registerNumber;
  }

  public void setRegisterNumber(String registerNumber) {
    this.registerNumber = registerNumber;
  }

  public Integer getSaberProScore() {
    return saberProScore;
  }

  public void setSaberProScore(Integer saberProScore) {
    this.saberProScore = saberProScore;
  }

  public Integer getSaberProScoreLevel() {
    return saberProScoreLevel;
  }

  public void setSaberProScoreLevel(Integer saberProScoreLevel) {
    this.saberProScoreLevel = saberProScoreLevel;
  }

  public Integer getWrittenCommunication() {
    return writtenCommunication;
  }

  public void setWrittenCommunication(Integer writtenCommunication) {
    this.writtenCommunication = writtenCommunication;
  }

  public Integer getWrittenCommunicationLevel() {
    return writtenCommunicationLevel;
  }

  public void setWrittenCommunicationLevel(Integer writtenCommunicationLevel) {
    this.writtenCommunicationLevel = writtenCommunicationLevel;
  }

  public Integer getQuantitativeReasoning() {
    return quantitativeReasoning;
  }

  public void setQuantitativeReasoning(Integer quantitativeReasoning) {
    this.quantitativeReasoning = quantitativeReasoning;
  }

  public Integer getQuantitativeReasoningLevel() {
    return quantitativeReasoningLevel;
  }

  public void setQuantitativeReasoningLevel(Integer quantitativeReasoningLevel) {
    this.quantitativeReasoningLevel = quantitativeReasoningLevel;
  }

  public Integer getReadingCritical() {
    return readingCritical;
  }

  public void setReadingCritical(Integer readingCritical) {
    this.readingCritical = readingCritical;
  }

  public Integer getReadingCriticalLevel() {
    return readingCriticalLevel;
  }

  public void setReadingCriticalLevel(Integer readingCriticalLevel) {
    this.readingCriticalLevel = readingCriticalLevel;
  }

  public Integer getCitizenshipCompetence() {
    return citizenshipCompetence;
  }

  public void setCitizenshipCompetence(Integer citizenshipCompetence) {
    this.citizenshipCompetence = citizenshipCompetence;
  }

  public Integer getCitizenshipCompetenceLeve() {
    return citizenshipCompetenceLeve;
  }

  public void setCitizenshipCompetenceLeve(Integer citizenshipCompetenceLeve) {
    this.citizenshipCompetenceLeve = citizenshipCompetenceLeve;
  }

  public Integer getEnglish() {
    return english;
  }

  public void setEnglish(Integer english) {
    this.english = english;
  }

  public Integer getEnglishLevel() {
    return englishLevel;
  }

  public void setEnglishLevel(Integer englishLevel) {
    this.englishLevel = englishLevel;
  }

  public Integer getEngineeringProjectFormulation() {
    return engineeringProjectFormulation;
  }

  public void setEngineeringProjectFormulation(Integer engineeringProjectFormulation) {
    this.engineeringProjectFormulation = engineeringProjectFormulation;
  }

  public Integer getEngineeringProjectFormulationLevel() {
    return engineeringProjectFormulationLevel;
  }

  public void setEngineeringProjectFormulationLevel(Integer engineeringProjectFormulationLevel) {
    this.engineeringProjectFormulationLevel = engineeringProjectFormulationLevel;
  }

  public Integer getMathematicsStatistics() {
    return mathematicsStatistics;
  }

  public void setMathematicsStatistics(Integer mathematicsStatistics) {
    this.mathematicsStatistics = mathematicsStatistics;
  }

  public Integer getMathematicsStatisticsLevel() {
    return mathematicsStatisticsLevel;
  }

  public void setMathematicsStatisticsLevel(Integer mathematicsStatisticsLevel) {
    this.mathematicsStatisticsLevel = mathematicsStatisticsLevel;
  }

  public Integer getSoftwareDesign() {
    return softwareDesign;
  }

  public void setSoftwareDesign(Integer softwareDesign) {
    this.softwareDesign = softwareDesign;
  }

  public Integer getSoftwareDesignLevel() {
    return softwareDesignLevel;
  }

  public void setSoftwareDesignLevel(Integer softwareDesignLevel) {
    this.softwareDesignLevel = softwareDesignLevel;
  }

  public String getEnglishPosition() {
    return englishPosition;
  }

  public void setEnglishPosition(String englishPosition) {
    this.englishPosition = englishPosition;
  }


  public boolean allDataComplete () {
    return this.registerNumber != null && this.saberProScore != null && this.saberProScoreLevel != null && this.writtenCommunication != null && this.writtenCommunicationLevel != null && this.quantitativeReasoning != null && this.quantitativeReasoningLevel != null && this.readingCritical != null && this.readingCriticalLevel != null && this.citizenshipCompetence != null && this.citizenshipCompetenceLeve != null && this.english != null && this.englishLevel != null && this.engineeringProjectFormulation != null && this.engineeringProjectFormulationLevel != null && this.mathematicsStatistics != null && this.mathematicsStatisticsLevel != null && this.softwareDesign != null && this.softwareDesignLevel != null && this.englishPosition != null;
  }

  @Override
  public String toString() {
    return "StudentModel{" +
            "id='" + id + '\'' +
            ", middleName='" + middleName + '\'' +
            ", lastSurname='" + lastSurname + '\'' +
            ", phone='" + phone + '\'' +
            ", registerNumber='" + registerNumber + '\'' +
            ", saberProScore=" + saberProScore +
            ", saberProScoreLevel=" + saberProScoreLevel +
            ", writtenCommunication=" + writtenCommunication +
            ", writtenCommunicationLevel=" + writtenCommunicationLevel +
            ", quantitativeReasoning=" + quantitativeReasoning +
            ", quantitativeReasoningLevel=" + quantitativeReasoningLevel +
            ", readingCritical=" + readingCritical +
            ", readingCriticalLevel=" + readingCriticalLevel +
            ", citizenshipCompetence=" + citizenshipCompetence +
            ", citizenshipCompetenceLeve=" + citizenshipCompetenceLeve +
            ", english=" + english +
            ", englishLevel=" + englishLevel +
            ", engineeringProjectFormulation=" + engineeringProjectFormulation +
            ", engineeringProjectFormulationLevel=" + engineeringProjectFormulationLevel +
            ", mathematicsStatistics=" + mathematicsStatistics +
            ", mathematicsStatisticsLevel=" + mathematicsStatisticsLevel +
            ", softwareDesign=" + softwareDesign +
            ", softwareDesignLevel=" + softwareDesignLevel +
            ", englishPosition='" + englishPosition + '\'' +
            '}';
  }
}
