package com.tallerMongo.app.service;

import com.tallerMongo.app.exception.NotFoundException;
import com.tallerMongo.app.helpers.ChangesUpdates;
import com.tallerMongo.app.model.StudentModel;
import com.tallerMongo.app.repository.StudentRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

  private final StudentRepository studentRepository;
  private final static Integer[] VALUES = {125, 155, 190, 300};

  public StudentService(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  public List<StudentModel> getAllStudents() {
    return studentRepository.findAll();
  }

  public StudentModel saveStudent(StudentModel student) {
    int saberProScore = student.getSaberProScore();
    int writtenCommunication = student.getWrittenCommunication();
    int quantitativeReasoning = student.getQuantitativeReasoning();
    int readingCritical = student.getReadingCritical();
    int citizenshipCompetence = student.getCitizenshipCompetence();
    int english = student.getEnglish();
    int engineeringProjectFormulation = student.getEngineeringProjectFormulation();
    int mathematicsStatistics = student.getMathematicsStatistics();
    int softwareDesign = student.getSoftwareDesign();

    int saberProLevel = this.getDataLevel(saberProScore);
    int writtenCommunicationLevel = this.getDataLevel(writtenCommunication);
    int quantitativeReasoningLevel = this.getDataLevel(quantitativeReasoning);
    int readingCriticalLevel = this.getDataLevel(readingCritical);
    int citizenshipCompetenceLevel = this.getDataLevel(citizenshipCompetence);
    int englishLevel = this.getDataLevel(english);
    int engineeringProjectFormulationLevel = this.getDataLevel(engineeringProjectFormulation);
    int mathematicsStatisticsLevel = this.getDataLevel(mathematicsStatistics);
    int softwareDesignLevel = this.getDataLevel(softwareDesign);
    String englishPosition = this.getPositionEnglish(english);

    student.setSaberProScoreLevel(saberProLevel);
    student.setWrittenCommunicationLevel(writtenCommunicationLevel);
    student.setQuantitativeReasoningLevel(quantitativeReasoningLevel);
    student.setReadingCriticalLevel(readingCriticalLevel);
    student.setCitizenshipCompetenceLeve(citizenshipCompetenceLevel);
    student.setEnglishLevel(englishLevel);
    student.setEngineeringProjectFormulationLevel(engineeringProjectFormulationLevel);
    student.setMathematicsStatisticsLevel(mathematicsStatisticsLevel);
    student.setSoftwareDesignLevel(softwareDesignLevel);
    student.setEnglishPosition(englishPosition);

    return studentRepository.save(student);
  }

  public int getDataLevel (int item) {
    int cont = 0;
    for (Integer VALUE: VALUES) {
      cont++;
      if (item <= VALUE) {
        return cont;
      }
    }
    return 0;
  }

  public String getPositionEnglish (int scoreEnglish) {
    String position = "A0";
    if (scoreEnglish <= 50) {
      return position;
    }
    if (scoreEnglish <= 100) {
      return "A1";
    }
    if (scoreEnglish <= 150) {
      return "A2";
    }
    if (scoreEnglish <= 200) {
      return "B1";
    }
    if (scoreEnglish <= 250) {
      return "B2";
    }
    if (scoreEnglish <= 300) {
      return "C1";
    }
    return "Error";
  }

  public StudentModel getStudentById(ObjectId id) {
    return studentRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
  }

  public StudentModel updateStudent (ObjectId id, StudentModel student) {
    StudentModel savedStudent = this.getStudentById(id);
    boolean allData = savedStudent.allDataComplete();
    if (!allData) {
      return null;
    }

    student.setMiddleName(savedStudent.getMiddleName());
    student.setLastSurname(savedStudent.getLastSurname());
    student.setPhone(savedStudent.getPhone());
    student.setId(savedStudent.getId());
    student.setRegisterNumber(savedStudent.getRegisterNumber());

    return this.saveStudent(student);
  }

  public StudentModel updateDenied (ObjectId id, StudentModel student) {
    StudentModel savedStudent = this.getStudentById(id);
    if (savedStudent == null) {
      return null;
    }
    student.setId(savedStudent.getId());
    student.setRegisterNumber(savedStudent.getRegisterNumber());
    student.setMiddleName(savedStudent.getMiddleName());
    student.setLastSurname(savedStudent.getLastSurname());
    student.setPhone(savedStudent.getPhone());

    return studentRepository.save(student);
  }

  public StudentModel deleteStudentId(ObjectId id) {
    StudentModel student = this.getStudentById(id);
    studentRepository.deleteById(id);
    return student;
  }

  public StudentModel updatePersonalInfo(ObjectId id, StudentModel student) {
    StudentModel savedStudent = this.getStudentById(id);
    boolean personalData = savedStudent.personalInfoComplete();
    if (!personalData) {
      return null;
    }
    String newMiddleName = ChangesUpdates.changeData(student.getMiddleName(), savedStudent.getMiddleName());
    String newLastSurname = ChangesUpdates.changeData(student.getLastSurname(), savedStudent.getLastSurname());
    String newPhone = ChangesUpdates.changeData(student.getPhone(), savedStudent.getPhone());

    savedStudent.setMiddleName(newMiddleName);
    savedStudent.setLastSurname(newLastSurname);
    savedStudent.setPhone(newPhone);

    return studentRepository.save(savedStudent);
  }


}
