package com.tallerMongo.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tallerMongo.app.middleware.AuthenticationAdmin;
import com.tallerMongo.app.model.ErrorReponseModel;
import com.tallerMongo.app.model.UserModel;
import com.tallerMongo.app.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/login")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE
        , RequestMethod.PATCH})
public class UserController {

  private final UserService userService;
  private final AuthenticationAdmin authenticationAdmin;

  public UserController(UserService userService) {
    this.userService = userService;
    this.authenticationAdmin = new AuthenticationAdmin(userService);

  }

  @GetMapping("/")
  public ResponseEntity<List<UserModel>> getAllUsers () {
    List<UserModel> users = userService.getAllUsers();
    return ResponseEntity.status(HttpStatus.OK).body(users);
  }

  @PostMapping("/add")
  public ResponseEntity saveUser (@RequestBody Map<String, Object> body) {
    ObjectMapper mapper = new ObjectMapper();
    UserModel userRequest = mapper.convertValue(body, UserModel.class);

    boolean dataComplete = userRequest.allDataComplete();
    if (!dataComplete) {
      ErrorReponseModel error = new ErrorReponseModel("Data incomplete");
      return ErrorReponseModel.BAD_REQUEST(error);
    }

    ObjectId userId = userService.verifyExistPassword(userRequest);
    userRequest.setId(userId);

    boolean uniqueUser = userService.uniqueUser(userRequest);
    if (!uniqueUser) {
      ErrorReponseModel error = new ErrorReponseModel("User already exist");
      return ErrorReponseModel.BAD_REQUEST(error);
    }

    UserModel user = userService.saveUser(userRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(user);
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserModel> getOneUserById (@PathVariable ObjectId id) {
    UserModel user = userService.getOneUserById(id);
    return ResponseEntity.status(HttpStatus.OK).body(user);
  }

  @PatchMapping("/update/{id}")
  public ResponseEntity<UserModel> updateUser (@PathVariable ObjectId id, @RequestBody Map<String, Object> body) {
    ObjectMapper objectMapper = new ObjectMapper();
    UserModel userRequest = objectMapper.convertValue(body, UserModel.class);
    UserModel user = userService.updateUser(id, userRequest);
    return ResponseEntity.status(HttpStatus.OK).body(user);
  }

  @DeleteMapping("/delete/{id}/{userDoId}")
  public ResponseEntity deleteUserById (@PathVariable ObjectId id, @PathVariable ObjectId userDoId) {
    ResponseEntity auth = authenticationAdmin.isAdmin(userDoId);
    if (auth != null) {
      return auth;
    }
    UserModel user = userService.deleteUserById(id);
    return ResponseEntity.status(HttpStatus.OK).body(user);
  }

  @PostMapping("/")
  public ResponseEntity loginUser(@RequestBody Map<String, Object> body) {
    ObjectMapper mapper = new ObjectMapper();
    UserModel userRequest = mapper.convertValue(body, UserModel.class);
    String email = userRequest.getEmail();
    String password = userRequest.getPassword();
    UserModel user = userService.loginUser(email, password);
    if (user == null) {
      ErrorReponseModel error = new ErrorReponseModel("Email or password incorrect");
      return ErrorReponseModel.NOT_FOUND(error);
    }
    return ResponseEntity.status(HttpStatus.OK).body(user);
  }

  @PostMapping("/getEmail")
  public ResponseEntity getUserByEmail(@RequestBody Map<String, Object> body) {
    ObjectMapper objMapper = new ObjectMapper();
    UserModel userRequest = objMapper.convertValue(body, UserModel.class);
    String email = userRequest.getEmail();
    UserModel user = userService.getUserByEmail(email);
    if (user == null) {
      ErrorReponseModel error = new ErrorReponseModel("User not found");
      return ErrorReponseModel.NOT_FOUND(error);
    }
    return ResponseEntity.status(HttpStatus.OK).body(user);
  }

  @PostMapping("/getDocument")
  public ResponseEntity getUserByDocumentId(@RequestBody Map<String, Object> body) {
    ObjectMapper objMapper = new ObjectMapper();
    UserModel userRequest = objMapper.convertValue(body, UserModel.class);
    String documentId = userRequest.getDocumentId();
    UserModel user = userService.getUserByDocumentId(documentId);
    if (user == null) {
      ErrorReponseModel error = new ErrorReponseModel("User not found");
      return ErrorReponseModel.NOT_FOUND(error);
    }
    return ResponseEntity.status(HttpStatus.OK).body(user);
  }

  @GetMapping("/availables/{userDoId}")
  public ResponseEntity<List<UserModel>> getAllAvailables (@PathVariable ObjectId userDoId) {
    ResponseEntity auth = authenticationAdmin.isAdmin(userDoId);
    if (auth != null) {
      return auth;
    }
    List<UserModel> users = userService.getUserStudentsAvailables();
    return ResponseEntity.status(HttpStatus.OK).body(users);
  }

  @GetMapping("/allStudents/{userDoId}")
  public ResponseEntity getUserStudents (@PathVariable ObjectId userDoId) {
    ResponseEntity auth = authenticationAdmin.isAdmin(userDoId);
    if (auth != null) {
      return auth;
    }
    List<UserModel> users = userService.getUserStudents();
    return ResponseEntity.status(HttpStatus.OK).body(users);
  }
}
