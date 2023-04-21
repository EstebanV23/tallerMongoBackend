package com.tallerMongo.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tallerMongo.app.middleware.AuthenticationAdmin;
import com.tallerMongo.app.model.ErrorReponseModel;
import com.tallerMongo.app.model.StudentModel;
import com.tallerMongo.app.service.StudentService;
import com.tallerMongo.app.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE
        , RequestMethod.PATCH})
public class StudentController {
  private final StudentService studentService;
  private final UserService userService;
  private final AuthenticationAdmin authenticationAdmin;

  public StudentController(StudentService studentService, UserService userService) {
    this.studentService = studentService;
    this.userService = userService;
    this.authenticationAdmin = new AuthenticationAdmin(userService);
  }

  @GetMapping("/")
  public ResponseEntity<List<StudentModel>> getAllStudents () {
    List<StudentModel> students = studentService.getAllStudents();
    return ResponseEntity.status(HttpStatus.OK).body(students);
  }

  @PostMapping("/add/{userId}/{userDoId}")
  public ResponseEntity saveStudent (@RequestBody Map<String, Object> body, @PathVariable ObjectId userId, @PathVariable ObjectId userDoId) {
    ObjectMapper mapper = new ObjectMapper();
    StudentModel studentRequest = mapper.convertValue(body, StudentModel.class);

    ResponseEntity auth = authenticationAdmin.isAdmin(userDoId);
    if (auth != null) {
      return auth;
    }

    boolean dataComplete = studentRequest.allDataComplete();
    if (!dataComplete) {
      ErrorReponseModel error = new ErrorReponseModel("Data incomplete");
      return ErrorReponseModel.BAD_REQUEST(error);
    }

    boolean userHaveStudent = userService.getUserWithStudent(userId);
    if (userHaveStudent) {
      ErrorReponseModel error = new ErrorReponseModel("User already have a student, or this action is not " +
              "possible for this user");
      return ErrorReponseModel.BAD_REQUEST(error);
    }

    StudentModel student = studentService.saveStudent(studentRequest);
    userService.updateStudent(userId, student);

    return ResponseEntity.status(HttpStatus.CREATED).body(student);
  }

  @GetMapping("/{id}")
  public ResponseEntity<StudentModel> getStudentById (@PathVariable ObjectId id) {
    StudentModel student = studentService.getStudentById(id);
    return ResponseEntity.status(HttpStatus.OK).body(student);
  }

  @PatchMapping("/update/{id}/{userDoId}")
  public ResponseEntity updateUser (@PathVariable ObjectId id, @RequestBody Map<String, Object> body,
                                    @PathVariable ObjectId userDoId) {
    ResponseEntity auth = authenticationAdmin.isAdmin(userDoId);
    if (auth != null) {
      return auth;
    }
    ObjectMapper objectMapper = new ObjectMapper();
    StudentModel studentRequest = objectMapper.convertValue(body, StudentModel.class);
    StudentModel user = studentService.updateStudent(id, studentRequest);
    if (user == null) {
      ErrorReponseModel error = new ErrorReponseModel("Data incomplete");
      return ErrorReponseModel.BAD_REQUEST(error);
    }
    return ResponseEntity.status(HttpStatus.OK).body(user);
  }

  @DeleteMapping("/delete/{id}/{userId}/{userDoId}")
  public ResponseEntity deleteStudentId (@PathVariable ObjectId id, @PathVariable ObjectId userId, @PathVariable ObjectId userDoId) {
    ResponseEntity auth = authenticationAdmin.isAdmin(userDoId);
    if (auth != null) {
      return auth;
    }
    StudentModel student = studentService.deleteStudentId(id);
    userService.updateStudent(userId, null);
    return ResponseEntity.status(HttpStatus.OK).body(student);
  }
}
